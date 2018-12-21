package com.nlulic.senso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import Dto.User;
import business.Session;

public class OfflineGameSelection extends AppCompatActivity {

    private User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game_selection);

        this.loggedInUser = Session.getMain();

        if(this.loggedInUser == null || this.loggedInUser.IsEmpty())
            redirectToLogin();

        this.renderGreeting();
        this.handleClicks();
    }

    private void handleClicks() {

        Button singlePlayer = findViewById(R.id.btnSingleplayer),
               multiPlayer = findViewById(R.id.btnMultiplayer),
               highscore = findViewById(R.id.btnHighscore),
               logout = findViewById(R.id.btnLogout);

        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingleGameActivity();
            }
        });

        multiPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultiplayerGameActivity();
            }
        });

        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOfflineHighscoreActivity();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.Reset();
                redirectToLogin();
            }
        });

    }

    private void renderGreeting() {

        TextView view = (TextView)findViewById(R.id.tvGreeting);
        view.setText(String.format("Hallo, %s!", this.loggedInUser.DisplayName()));
    }

    private void redirectToLogin() {

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void openSingleGameActivity() {
        Intent intent = new Intent(this, SingleGame.class);
        startActivity(intent);
    }

    private void openMultiplayerGameActivity() {

        Intent intent = new Intent(this, Login.class);
        intent.putExtra("nextActivity", MultiplayerGame.class + "");
        startActivity(intent);
    }

    private void openOfflineHighscoreActivity() {
        Intent intent = new Intent(this, OfflineGameHighscore.class);
        startActivity(intent);
    }
}
