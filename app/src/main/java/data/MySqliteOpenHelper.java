package data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.UUID;

import Dto.GameResult;
import Dto.User;

public class MySqliteOpenHelper extends SQLiteOpenHelper {

    private static final String database_name = "senso.db";
    private static final int database_version = 3;

    private String table_name = "game_result",
        column_player_one = "player_one",
        column_player_two = "player_two",
        column_winner = "winner",
        column_id = "id";

    private String user_table = "user",
        column_display_name = "display_name",
        column_username = "username",
        column_password = "password",
        column_guid = "guid";

    public MySqliteOpenHelper(Context ctxt) {
        super(ctxt, database_name, null, database_version);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(String.format(
            "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(255), %s VARCHAR(255), %s VARCHAR(255))",
            table_name, column_id, column_player_one, column_player_two, column_winner
        ));

        db.execSQL(String.format(
            "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(255), %s VARCHAR(255) UNIQUE, %s VARCHAR(255), %s VARCHAR(255))",
            user_table, column_id, column_display_name, column_username, column_password, column_guid
        ));

        System.out.print("CREATED TABLEEEEEEEEEEEEEEEEEEES");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // changes in the database

        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + user_table);

        onCreate(db);
    }

    public boolean InsertGameResult(String playerOne, String playerTwo, String winner) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues content = new ContentValues();
        content.put(column_player_one, playerOne);
        content.put(column_player_two, playerTwo);
        content.put(column_winner, winner);

        long ins = db.insert(table_name, null, content);

        return ins != -1;
    }

    public User RegisterUser(User user, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues content = new ContentValues();
        content.put(column_guid, UUID.randomUUID().toString());
        content.put(column_display_name, user.DisplayName());
        content.put(column_username, user.Username());
        content.put(column_password, password);

        long ins = db.insert(user_table, null, content);

        if(ins == -1)
            return null;

        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s';", user_table, column_username, user.Username()), null);

        User newUser = new User();

        while(cursor.moveToNext()) {

            String displayName = cursor.getString(cursor.getColumnIndex(column_display_name)),
                    name = cursor.getString(cursor.getColumnIndex(column_username)),
                    guid = cursor.getString(cursor.getColumnIndex(column_guid));

            newUser = new User(displayName, name, guid);
        }

        return newUser;
    }

    public User Login(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s';", user_table, column_username, username, column_password, password), null);

        User user = new User();

        while(cursor.moveToNext()) {

            String displayName = cursor.getString(cursor.getColumnIndex(column_display_name)),
                    name = cursor.getString(cursor.getColumnIndex(column_username)),
                    guid = cursor.getString(cursor.getColumnIndex(column_guid));

            user = new User(displayName, name, guid);
        }

        return user;
    }

    public boolean CheckIfUserExists(String username) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s';", user_table, column_username, username), null);

        User user = null;

        while(cursor.moveToNext()) {

            String displayName = cursor.getString(cursor.getColumnIndex(column_display_name)),
                   name = cursor.getString(cursor.getColumnIndex(column_guid)),
                   guid = cursor.getString(cursor.getColumnIndex(column_guid));

            user = new User(displayName, name, guid);
        }

        return user != null;
    }

    public List<GameResult> getAll() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + table_name, null);


        List<GameResult> results = new ArrayList<GameResult>();

        while(cursor.moveToNext()) {

            String playerOne = cursor.getString(cursor.getColumnIndex(column_player_one)),
                   playerTwo = cursor.getString(cursor.getColumnIndex(column_player_two)),
                   winner = cursor.getString(cursor.getColumnIndex(column_winner));

            results.add(new GameResult(playerOne, playerTwo, winner));
        }



        return results;
    }
}
