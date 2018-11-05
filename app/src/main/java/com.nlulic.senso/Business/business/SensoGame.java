package business;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class SensoGame {

    private GamePattern gamePattern;
    private SensoPattern userPattern;
    private RoundCounter rounds;
    boolean gameOver;

    public SensoGame() {

        this.rounds = new RoundCounter();
        this.gameOver = false;
    }

    public SensoGame(GamePattern gamePattern, SensoPattern userPattern) {
        this();

        this.gamePattern = gamePattern;
        this.userPattern = userPattern;
    }

    public void Run() {

        this.gamePattern.Append();
    }

    public void ComparePatterns() {

        if(!this.gamePattern.Assert(this.userPattern)) {
            gameOver = true;
        }
    }

    public SensoValue NextGamePattern() {
        return this.gamePattern.Next();
    }

    public void NextRound() {

        this.userPattern = new SensoPattern();
        this.gamePattern.Append();
        this.rounds.Add();
    }

    public void AddUserPattern(SensoValue value) {
        this.userPattern.Add(value);
    }

    public void Reset() {
        this.gamePattern = new GamePattern();
        this.userPattern = new SensoPattern();
        this.rounds.Restart();
        this.gameOver = false;
    }

    public boolean isGameOver() {

        return this.gameOver;
    }

    public boolean hasGameStarted() {

        return this.gamePattern.getPattern().size() > 0 && !this.gameOver;
    }

    public List<SensoValue> getGamePattern() {
        return this.gamePattern.getPattern();
    }

    public List<SensoValue> getUserPattern() {
        return this.userPattern.getPattern();
    }

    public int getRounds() {
        return this.rounds.getRounds();
    }

}
