package com.nlulic.senso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MultiplayerGame extends AppCompatActivity {

    private String playerOne, playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_game);
        this.getPlayers();
        this.showPlayers();
    }

    private void getPlayers() {
        Bundle extras = getIntent().getExtras();

        Intent intent = getIntent();

        this.playerOne = intent.getStringExtra("PlayerOne");
        this.playerTwo = intent.getStringExtra("PlayerTwo");
    }

    private void showPlayers() {
        Toast.makeText(getApplicationContext(), this.playerOne + " vs "+ this.playerTwo, Toast.LENGTH_LONG).show();
    }
}
