package data;

import android.content.Context;

import Dto.User;
import business.Session;

public class UserService {

    private  MySqliteOpenHelper db;

    public UserService(Context ctxt) {
        this.db = new MySqliteOpenHelper(ctxt);
    }

    public User Register(String displayName, String username, String password) {

        User user = new User(displayName, username);

        return db.RegisterUser(user, password);
    }

    public User Login(String username, String password) {
        User user = db.Login(username, password);

        if(!user.IsEmpty()) {
            Session.Store(user);
        }

        return user;
    }

    public boolean Exists(String username) {

        return db.CheckIfUserExists(username);
    }
}
