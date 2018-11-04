package com.nlulic.senso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OfflineGameSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game_selection);

        this.handleClicks();
    }

    private void handleClicks() {

        Button singlePlayer = findViewById(R.id.btnSingleplayer),
               multiPlayer = findViewById(R.id.btnMultiplayer),
               highscore = findViewById(R.id.btnHighscore);

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

    }

    private void openSingleGameActivity() {
        Intent intent = new Intent(this, SingleGame.class);
        startActivity(intent);
    }

    private void openMultiplayerGameActivity() {
        Intent intent = new Intent(this, PlayerNameSelection.class);
        startActivity(intent);
    }

    private void openOfflineHighscoreActivity() {
        Intent intent = new Intent(this, OfflineGameHighscore.class);
        startActivity(intent);
    }
}
