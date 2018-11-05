package business;

public class RoundCounter {

    private int rounds = 0;

    public void Add() {
        this.rounds++;
    }

    public void Restart() {
        this.rounds = 0;
    }

    public int getRounds() {
        return this.rounds;
    }

}
