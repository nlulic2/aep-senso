package com.nlulic.senso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.handleClicks();
    }

    private void handleClicks() {

        final Button register = findViewById(R.id.bRegisterUser);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name = findViewById(R.id.etRegisterName),
                         username = findViewById(R.id.etRegisterUsername),
                         password = findViewById(R.id.etRegisterPassword);

                register(name.getText().toString(), username.getText().toString(), password.getText().toString());
            }
        });
    }

    private void register(String name, String username, String password) {

        if(name.trim().length() < 1 || username.trim().length() < 1 || password.trim().length() < 1) {

            String missingField = "";

            if(name.trim().length() < 1)
                missingField = "Namen";
            else if(username.trim().length() < 1)
                missingField = "Benutzernamen";
            else if(password.trim().length() < 1)
                missingField = "Passwort";

            Toast.makeText(getApplicationContext(), "Bitte geben Sie ein "  + missingField + " an.", Toast.LENGTH_LONG).show();
        }

        //do the register




    }
}
