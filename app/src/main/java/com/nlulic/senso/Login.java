package com.nlulic.senso;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import Dto.User;
import business.Session;
import data.UserService;

public class Login extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.intent = getIntent();

        this.handleClicks();
        this.render();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(intent.getStringExtra("nextActivity") == null)
            Session.Reset();
    }

    private void render() {

        EditText username = findViewById(R.id.etLoginUsername),
                 password = findViewById(R.id.etLoginPassword);

        username.setText(intent.getStringExtra("username"));
        password.setText(intent.getStringExtra("password"));

        if(intent.getStringExtra("nextActivity") != null) {

            String nextActivity = intent.getStringExtra("nextActivity");

            if(nextActivity.equals(MultiplayerGame.class + "")) {
                // show hint of player two login
                RelativeLayout loginForm = findViewById(R.id.rlLoginForm);
                RelativeLayout.LayoutParams v = (RelativeLayout.LayoutParams) loginForm.getLayoutParams();
                v.setMargins(0, 150,0,0);
                loginForm.setLayoutParams(v);
                RelativeLayout hintLaoyut = findViewById(R.id.rlPlayerTwoTitle);
                hintLaoyut.setVisibility(View.VISIBLE);
            }
        }
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

        EditText usernameEdit = findViewById(R.id.etLoginUsername),
                 passwordEdit = findViewById(R.id.etLoginPassword);

        String username = usernameEdit.getText().toString(),
               password = passwordEdit.getText().toString();

        if(username.trim().length() < 1 || password.trim().length() < 1) {

            String alertMessage = username.trim().length() < 1
                ? "Bitte geben Sie einen Benutzernamen an."
                : "Bitte geben Sie ein Passwort an.";

            Toast.makeText(getApplicationContext(), alertMessage, Toast.LENGTH_LONG).show();
            return;
        }

        UserService userService = new UserService(this);
        User user = userService.Login(username, password);

        if(user.IsEmpty()) {
            Toast.makeText(getApplicationContext(), "Falsche Benutzername oder Passwort, bitte versuchen Sie es erneut", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = getIntent();
        String nextActivity = intent.getStringExtra("nextActivity");
        if(nextActivity != null && nextActivity.equals(MultiplayerGame.class + "")) {
            openMultiplayerGame();
        } else {
            openGameSelection();
        }
    }

    private void  openGameSelection() {
        Intent intent = new Intent(this, OfflineGameSelection.class);
        startActivity(intent);
    }

    private void openMultiplayerGame() {
        Intent intent = new Intent(this, MultiplayerGame.class);
        startActivity(intent);
    }
}
