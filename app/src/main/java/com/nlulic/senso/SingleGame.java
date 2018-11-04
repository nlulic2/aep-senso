package com.nlulic.senso;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import business.SensoGame;
import business.SensoValue;

public class SingleGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);

        this.setClickListeners();
    }

    private SensoGame game;
    private List<SensoValue> userPattern;

    private void startGame() {

        this.userPattern = new ArrayList<SensoValue>();

        game = new SensoGame();
        game.Run();

        iterateGamePattern();
    }

    private Handler mHandler = new Handler();

    private void iterateGamePattern() {

        mHandler.postDelayed(mToastRunnable, 1000);

    }

    private void stopRepeating() {
        mHandler.removeCallbacks(mToastRunnable);
    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {

            SensoValue value = game.Next();

            if(value == null) {
                stopRepeating();
                return;
            }

            Button button = null;

            switch (value) {
                case Yellow:
                    button = (Button)findViewById(R.id.btnYellow);
                    break;
                case Red:
                    button = (Button)findViewById(R.id.btnRed);
                    break;
                case Green:
                    button = (Button)findViewById(R.id.btnGreen);
                    break;
                case Blue:
                    button = (Button)findViewById(R.id.btnBlue);
                    break;
            }

            handleClick(button);
            mHandler.postDelayed(this, 1000);
        }
    };

    private void setClickListeners() {

        final Button startGame = findViewById(R.id.startSingleGame),
                yellowButton = findViewById(R.id.btnYellow),
                greenButton = findViewById(R.id.btnGreen),
                blueButton = findViewById(R.id.btnBlue),
                redButton = findViewById(R.id.btnRed);

        startGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame();
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userPattern.add(SensoValue.Yellow);
                assertUserAndGamePattern();
                handleClick(yellowButton);
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userPattern.add(SensoValue.Green);
                assertUserAndGamePattern();
                handleClick(greenButton);
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userPattern.add(SensoValue.Red);
                assertUserAndGamePattern();
                handleClick(redButton);
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userPattern.add(SensoValue.Blue);
                assertUserAndGamePattern();
                handleClick(blueButton);
            }
        });
    }

    private void assertUserAndGamePattern() {

        game.Assert(userPattern);

        if(game.isGameOver()) {
            Toast.makeText(getApplicationContext(), "Game Over, played " + game.getRounds() + " rounds.", Toast.LENGTH_LONG).show();
            game.Reset();
        }

        if(userPattern.size() == game.getPattern().size()) {
            this.game.Append();
            this.userPattern = new ArrayList<SensoValue>();
            iterateGamePattern();
        }

    }

    private boolean isClicking = false;
    private void handleClick(Button button) {

        if(isClicking)
            return;

        final Button clickedButton = button;
        isClicking = true;

        int activeColor = 0, colorTemp = 0;

        switch (button.getId()) {
            case R.id.btnYellow:
                activeColor = R.color.yellowActive;
                colorTemp = R.color.yellow;
                break;
            case R.id.btnRed:
                activeColor = R.color.redActive;
                colorTemp = R.color.red;
                break;
            case R.id.btnGreen:
                activeColor = R.color.greenActive;
                colorTemp = R.color.green;
                break;
            case R.id.btnBlue:
                activeColor = R.color.blueActive;
                colorTemp = R.color.blue;
                break;
        }

        final int color = colorTemp;

        clickedButton.setBackgroundColor(getResources().getColor(activeColor));

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clickedButton.setBackgroundColor(getResources().getColor(color));
                isClicking = false;
            }
        }, 500);

    }
}
