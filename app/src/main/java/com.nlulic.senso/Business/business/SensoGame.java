package business;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import business.SensoValue;

public class SensoGame {


    List<SensoValue> pattern;
    boolean gameOver;
    int rounds;

    public SensoGame() {
        Reset();
    }

    public void Run() {

        pattern.add(SensoValue.Yellow);
        pattern.add(SensoValue.Red);
        pattern.add(SensoValue.Green);
        pattern.add(SensoValue.Blue);
    }

    public void Assert(List<SensoValue> userPattern) {

        List<SensoValue> equalLengthPattern = this.pattern.subList(0, userPattern.size());

        if(userPattern.equals(equalLengthPattern)) {
            rounds++;
        } else {
            gameOver = true;
        }
    }

    private int latestIndex = 0;
    public SensoValue Next() {

        if(latestIndex > this.pattern.size()-1) {
            this.latestIndex = 0;
            return null;
        }

        SensoValue next = this.pattern.get(latestIndex);
        latestIndex++;



        return next;
    }


    public void Append() {

        this.pattern.add(randomValue());
    }

    public void Reset() {

        pattern = new ArrayList<SensoValue>();
        gameOver = false;
        latestIndex = 0;
        rounds = 0;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public List<SensoValue> getPattern() {
        return this.pattern;
    }

    private SensoValue randomValue() {
        return SensoValue.values()[new Random().nextInt(SensoValue.values().length)];
    }

}
