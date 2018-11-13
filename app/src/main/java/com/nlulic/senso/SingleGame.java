package com.nlulic.senso;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;
import business.SensoGame;
import business.SensoSound;
import business.SensoValue;

public class SingleGame extends AppCompatActivity {

    private SensoGame game = new SensoGame();
    private Handler handler = new Handler();
    private SensoSound tone = new SensoSound();

    private boolean isClicking = false;
    private boolean isIterating = false;

    private final int ITERATION_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);
        Button startGame = (Button)findViewById(R.id.startSingleGame);

        this.setButtonListeners();
        startGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame();
            }
        });
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

                    if(!game.hasGameStarted() || isClicking || isIterating)
                        return;

                    game.AddUserPattern(SensoValueByButton(button));
                    assertUserAndGamePattern();
                    handleClick(button);
                }
            });
        }
    }

    private void startGame() {

        stopRepeating();

        game.Reset();
        renderRounds();
        game.Run();

        iterateGamePattern();
    }

    private void iterateGamePattern() {

        handler.postDelayed(patternIterator, 1000);
    }

    private Runnable patternIterator = new Runnable() {
        @Override
        public void run() {

            isIterating = true;
            SensoValue value = game.NextGamePattern();

            if(value == null) {
                stopRepeating();
                return;
            }

            handleClick(ButtonBySensoValue(value));
            handler.postDelayed(this, ITERATION_DELAY);
        }
    };

    private void stopRepeating() {
        isIterating = false;
        handler.removeCallbacks(patternIterator);
    }

    private void assertUserAndGamePattern() {

        game.ComparePatterns();

        if(game.isGameOver()) {
            Toast.makeText(getApplicationContext(), "Game Over, played " + game.getRounds() + " rounds.", Toast.LENGTH_LONG).show();
            game.Reset();
        }

        if(game.getUserPattern().size() == game.getGamePattern().size()) {

            this.game.NextRound();
            renderRounds();
            iterateGamePattern();
        }
    }

    private void renderRounds() {

        Button roundButton = (Button)findViewById(R.id.btnRoundsPlayed);
        roundButton.setText(game.getRounds() + "");
    }



    private SensoValue SensoValueByButton(Button button) {

        switch (button.getId()) {
            case R.id.btnYellow:
                return SensoValue.Yellow;
            case R.id.btnGreen:
                return SensoValue.Green;
            case R.id.btnBlue:
                return SensoValue.Blue;
            case R.id.btnRed:
                return SensoValue.Red;
            default:
                return null;
        }
    }

    private Button ButtonBySensoValue(SensoValue value) {

        switch (value) {
            case Yellow:
                return (Button)findViewById(R.id.btnYellow);
            case Red:
                return (Button)findViewById(R.id.btnRed);
            case Green:
                return (Button)findViewById(R.id.btnGreen);
            case Blue:
                return (Button)findViewById(R.id.btnBlue);
            default:
                return null;
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
        }, ITERATION_DELAY / 2);

    }
}
