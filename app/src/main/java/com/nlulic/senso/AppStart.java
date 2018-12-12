package com.nlulic.senso;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import data.MySqliteOpenHelper;

public class AppStart extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start);

        MySqliteOpenHelper databaseHanlder = new MySqliteOpenHelper(this);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openLoginActivity();
            }
        }, 5000);

        Toast.makeText(getApplicationContext(), "heyy", Toast.LENGTH_LONG).show();

        this.handleClicks();
    }

    private void handleClicks() {

        Button login = findViewById(R.id.btnLogin),
               register = findViewById(R.id.btnRegister),
               playOfflie = findViewById(R.id.btnPlayOffline);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Not implemented yet", Toast.LENGTH_LONG).show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Not implemented yet", Toast.LENGTH_LONG).show();
            }
        });

        playOfflie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOfflineGameSelectionActivity();
            }
        });
    }

    private void openOfflineGameSelectionActivity() {
        Intent intent = new Intent(this, OfflineGameSelection.class);
        startActivity(intent);
    }


    private void openLoginActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}
