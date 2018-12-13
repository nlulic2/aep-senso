package Dto;

public class User {

    private String displayName, username, guid;

    public User() {

    }

    public User(String displayName, String username) {
        this.displayName = displayName;
        this.username = username;
    }

    public User(String displayName, String username, String guid) {
        this(displayName, username);
        this.guid = guid;
    }

    public boolean IsEmpty() {
        return this.guid == null;
    }

    public String Guid() {
        return this.guid;
    }

    public String DisplayName() {
        return this.displayName;
    }

    public String Username() {
        return this.username;
    }




}
