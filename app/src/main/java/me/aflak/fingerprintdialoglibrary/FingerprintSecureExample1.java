package me.aflak.fingerprintdialoglibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import me.aflak.libraries.callback.FingerprintDialogSecureCallback;
import me.aflak.libraries.dialog.FingerprintDialog;
import me.aflak.libraries.callback.FingerprintSecureCallback;
import me.aflak.libraries.dialog.FingerprintToken;
import me.aflak.libraries.callback.PasswordCallback;
import me.aflak.libraries.dialog.PasswordDialog;

public class FingerprintSecureExample1 extends AppCompatActivity implements View.OnClickListener, FingerprintDialogSecureCallback, PasswordCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(FingerprintDialog.isAvailable(this)) {
            FingerprintDialog.initialize(this)
                    .title(R.string.fingerprint_title)
                    .message(R.string.fingerprint_message)
                    .callback(this, "KeyName1")
                    .show();
        }
    }

    @Override
    public void onAuthenticationSucceeded() {
        // Fingerprint recognized
    }

    @Override
    public void onAuthenticationCancel() {
        // User pressed cancel button
    }

    @Override
    public void onNewFingerprintEnrolled(FingerprintToken token) {
        // /!\ A new fingerprint was added /!\
        //
        // Prompt a password to verify identity, then :
        // if (password.correct()) {
        //      token.continueAuthentication();
        // }
        //
        // OR
        //
        // Use PasswordDialog to simplify the process

        PasswordDialog.initialize(this, token)
                .title(R.string.password_title)
                .message(R.string.password_message)
                .callback(this)
                .passwordType(PasswordDialog.PASSWORD_TYPE_TEXT)
                .show();
    }

    @Override
    public boolean onPasswordCheck(String password) {
        return password.equals("password");
    }

    @Override
    public void onPasswordCancel() {
        // User pressed cancel button
    }

    @Override
    public void onPasswordSucceeded() {
        // Password was correct and new keys have been generated
    }
}