package data;

import android.content.Context;

import Dto.User;

public class RegisterUserService {

    public static boolean Process(String displayName, String username, String password, Context ctxt) {

        MySqliteOpenHelper db = new MySqliteOpenHelper(ctxt);

        User user = new User(displayName, username);


        return db.RegisterUser(user, password);
    }

}
