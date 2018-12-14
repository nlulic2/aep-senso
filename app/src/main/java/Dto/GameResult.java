package Dto;

public class GameResult {

    private String username;
    private String name;
    private String guid;
    private String score;

    public GameResult(String name, String username, String score, String guid) {
        this.username = username;
        this.score = score;
        this.name = name;
        this.guid = guid;
    }

    public String Username() {
        return this.username;
    }

    public String Score() {
        return this.score;
    }

    public String Name() {
        return this.name;
    }

}
