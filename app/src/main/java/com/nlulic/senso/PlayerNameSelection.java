package com.nlulic.senso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerNameSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name_selection);

        Button play = findViewById(R.id.btnStartMultiplayerGame);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMultiplayerActivity();
            }
        });

    }

    private void startMultiplayerActivity() {

        TextView playerOne = (TextView)findViewById(R.id.playerOneName),
                 playerTwo = (TextView)findViewById(R.id.playerTwoName);

        String playerOneName = playerOne.getText().toString(),
               playerTwoName = playerTwo.getText().toString();

        if(playerOneName.length() < 1 || playerTwoName.length() < 1) {

            Toast.makeText(getApplicationContext(), "Bitte geben Sie einen Namen für beide Spieler an." , Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, MultiplayerGame.class);
        intent.putExtra("PlayerOne", playerOneName);
        intent.putExtra("PlayerTwo", playerTwoName);

        startActivity(intent);
    }


}
