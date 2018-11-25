package data;

import android.content.Context;
import android.widget.Toast;

public class SaveGameResultService {

    public static boolean Process(String playerOne, String playerTwo, String winner, Context ctxt) {

        MySqliteOpenHelper db = new MySqliteOpenHelper(ctxt);

        return db.InsertGameResult(playerOne, playerTwo, winner);
    }
}
