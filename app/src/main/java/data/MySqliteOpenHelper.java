package data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Dto.GameResult;

public class MySqliteOpenHelper extends SQLiteOpenHelper {

    private static final String database_name = "senso.db";
    private static final int database_version = 1;

    private String table_name = "game_result",
        column_player_one = "player_one",
        column_player_two = "player_two",
        column_winner = "winner",
        column_id = "id";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // changes in the database
        /*
        db.execSQL("DROP TABLE IF EXITS " + table_name);
        onCreate(db);
        */
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
