package com.nlulic.senso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.handleClicks();
    }

    private void handleClicks() {
        Button login = findViewById(R.id.bLogin);
        TextView register = findViewById(R.id.tvRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    private void Login() {

        EditText username = findViewById(R.id.etLoginUsername),
                 password = findViewById(R.id.etLoginPassword);

        if(username.getText().toString().trim().length() < 1 || password.getText().toString().trim().length() < 1) {

            String alertMessage = username.getText().toString().trim().length() < 1
                ? "Bitte geben Sie einen Benutzernamen an."
                : "Bitte geben Sie ein Passwort an.";

            Toast.makeText(getApplicationContext(), alertMessage, Toast.LENGTH_LONG).show();
            return;
        }

        //do the login logic and redirect to mainActivity

    }
}
