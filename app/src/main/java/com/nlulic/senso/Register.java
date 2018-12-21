package com.nlulic.senso;

import Dto.User;
import business.Dialog;
import data.MySqliteOpenHelper;
import data.UserService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    MySqliteOpenHelper databaseManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        databaseManger = new MySqliteOpenHelper(this);



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

            Dialog.Show(String.format("Bitte geben Sie ein %s an.", missingField), this);
            return;
        }

        UserService userService = new UserService(this);

        if(userService.Exists(username)) {
            Dialog.Show(String.format("Benutzer %s existiert bereits!", username), this);
            return;
        }

        User user = userService.Register(name, username, password);

        if(!user.IsEmpty()) {
            Toast.makeText(getApplicationContext(), String.format("Der Benutzer %s wurde erstellt!", user.Username()), Toast.LENGTH_LONG).show();
            openLoginActivity(user.Username(), password);
        } else {
            Dialog.Show("Fehler beim erstellen des Benutzers!",this);
        }
    }

    private void openLoginActivity(String username, String password) {

        Intent currentIntent = getIntent();

        String nextActivity = currentIntent.getStringExtra("nextActivity");

        Intent intent = new Intent(this, Login.class);

        if(nextActivity != null) {
            intent.putExtra("nextActivity", MultiplayerGame.class + "");
        }

        intent.putExtra("username", username);
        intent.putExtra("password", password);

        startActivity(intent);
    }
}
