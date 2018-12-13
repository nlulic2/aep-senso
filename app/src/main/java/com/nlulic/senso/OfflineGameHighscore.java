package com.nlulic.senso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Dto.GameResult;
import data.MySqliteOpenHelper;

public class OfflineGameHighscore extends AppCompatActivity {

    private MySqliteOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game_highscore);

        db = new MySqliteOpenHelper(this);




        populateListView();
    }

    private void populateListView() {

        List<GameResult> data = db.getAll();


        Toast.makeText(getApplicationContext(), "ayy " + data.size(), Toast.LENGTH_LONG).show();

        ArrayList<String> results = new ArrayList<>();

        for(GameResult result : data) {

            results.add(result.getPlayerOne() + " vs " + result.getPlayerTwo() +":" + result.getWinner());
        }




        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);

        ListView list = (ListView) findViewById(R.id.highscoreList);
        list.setAdapter(adapter);


    }
}
