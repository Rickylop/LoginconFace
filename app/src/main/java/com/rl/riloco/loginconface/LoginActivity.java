package com.rl.riloco.loginconface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    private LoginButton lB;
    private CallbackManager cM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lB = (LoginButton)findViewById(R.id.login_button);
        cM = CallbackManager.Factory.create();


        lB.registerCallback(cM, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(getApplicationContext(), "¡Inicio de sesión exitoso!", Toast.LENGTH_LONG).show();
                iraMain();

            }

            @Override
            public void onCancel() {

                Toast.makeText(getApplicationContext(), "¡Inicio de sesión cancelado!", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(getApplicationContext(), "¡Inicio de sesión NO exitoso!", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void iraMain() {
        Intent inten = new Intent(LoginActivity.this, MainActivity.class);
        finish();
        startActivity(inten);

    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent i){
        super.onActivityResult(reqCode, resCode, i);
        cM.onActivityResult(reqCode, resCode, i);
    }

}
