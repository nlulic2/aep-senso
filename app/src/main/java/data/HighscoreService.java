package data;

import android.content.Context;

import java.util.List;

import Dto.GameResult;
import Dto.User;

public class HighscoreService {

    private  MySqliteOpenHelper db;

    public HighscoreService(Context ctxt) {
        this.db = new MySqliteOpenHelper(ctxt);
    }

    public boolean Save(User user, int score) {

        return this.db.InsertScore(user.Guid(), score);
    }

    public List<GameResult> GetAll() {

        return this.db.GetSinglePlayerGameResults();
    }

}
