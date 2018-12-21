package com.nlulic.senso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Dto.GameResult;
import data.HighscoreService;

public class OfflineGameHighscore extends AppCompatActivity {

    private HighscoreService highscoreService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game_highscore);

        this.highscoreService = new HighscoreService(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateListView();
    }

    private void populateListView() {

        List<GameResult> gameResults = this.highscoreService.GetAll();
        ArrayList<String> results = new ArrayList<>();

        for(GameResult result : gameResults) {
            results.add(String.format("%s Runden (%s)", result.Score(), result.Username()));
        }

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results);

        ListView list = (ListView) findViewById(R.id.highscoreList);
        list.setAdapter(adapter);
    }
}
