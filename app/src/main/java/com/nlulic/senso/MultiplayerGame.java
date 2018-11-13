package com.nlulic.senso;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

import business.SensoSound;

public class MultiplayerGame extends AppCompatActivity {

    private String playerOne, playerTwo;
    private SensoSound tone = new SensoSound();

    private boolean isClicking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_game);
        this.setup();
    }

    private void setup() {
        this.getPlayers();
        this.renderPlayers(this.playerOne, this.playerTwo);
        this.setButtonListeners();
    }

    private void getPlayers() {
        Bundle extras = getIntent().getExtras();

        Intent intent = getIntent();

        this.playerOne = intent.getStringExtra("PlayerOne");
        this.playerTwo = intent.getStringExtra("PlayerTwo");
    }

    private void renderPlayers(String firstPlayer, String secondPlayer) {

        TextView playerDisplay = findViewById(R.id.playerDisplay);
        playerDisplay.setText(firstPlayer + " vs. " +  secondPlayer);
    }

    private void setButtonListeners() {

        final List<Button> buttons = Arrays.asList(
            (Button)findViewById(R.id.btnYellow),
            (Button)findViewById(R.id.btnGreen),
            (Button)findViewById(R.id.btnBlue),
            (Button)findViewById(R.id.btnRed)
        );

        for(final Button button :  buttons) {

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "CLICK", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    private void handleClick(final Button button) {

        Handler handler = new Handler();

        isClicking = true;

        int activeColor = 0, colorTemp = 0;

        switch (button.getId()) {
            case R.id.btnYellow:
                this.tone.Yellow();
                activeColor = R.color.yellowActive;
                colorTemp = R.color.yellow;
                break;
            case R.id.btnRed:
                this.tone.Red();
                activeColor = R.color.redActive;
                colorTemp = R.color.red;
                break;
            case R.id.btnGreen:
                this.tone.Green();
                activeColor = R.color.greenActive;
                colorTemp = R.color.green;
                break;
            case R.id.btnBlue:
                this.tone.Blue();
                activeColor = R.color.blueActive;
                colorTemp = R.color.blue;
                break;
        }

        final int color = colorTemp;

        button.setBackgroundColor(getResources().getColor(activeColor));

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setBackgroundColor(getResources().getColor(color));
                isClicking = false;
            }
        }, 1000 / 2);

    }
}
