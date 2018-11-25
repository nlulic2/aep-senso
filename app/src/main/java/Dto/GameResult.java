package Dto;

public class GameResult {

    private String PlayerOne;
    private String PlayerTwo;
    private String Winner;

    public GameResult(String playerOne, String playerTwo, String winner) {

        this.PlayerOne = playerOne;
        this.PlayerTwo = playerTwo;
        this.Winner = winner;
    }

    public String getPlayerOne() {
        return this.PlayerOne;
    }

    public String getPlayerTwo() {
        return this.PlayerTwo;
    }

    public String getWinner() {
        return this.Winner;
    }
}
