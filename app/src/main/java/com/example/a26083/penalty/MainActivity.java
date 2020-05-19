package com.example.a26083.penalty;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout main_activity;
    private Button btnSubmit, btnPlayer, btnTut;
    private TextView coinView;
    public static int coins = 1000;

    public static MediaPlayer time_bomb;

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int coins) {
        MainActivity.coins = coins;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_activity = findViewById(R.id.main_activity);
        btnSubmit = findViewById(R.id.btnOne);
        btnPlayer = findViewById(R.id.btnDel);
        btnTut = findViewById(R.id.btnTut);

        main_activity.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGreen));

        time_bomb = MediaPlayer.create(MainActivity.this, R.raw.time_bomb);

        if(!time_bomb.isPlaying())
        {
            time_bomb.start();
        }

        SharedPreferences sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        coins = sharedPreferences.getInt("coins", 1000);
//
        coinView = findViewById(R.id.coin_view);
        coinView.setText(coins + " EP");

        addListenerOnButton();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("coins", coins);
        editor.commit();
    }


    // get the selected dropdown list value
    public void addListenerOnButton() {

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,
                        "Coming soon :)",
                        Toast.LENGTH_SHORT).show();
            }

        });

        btnPlayer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MainActivity.this, PlayerSelection.class);

                startActivity(intent1);

            }

        });

        btnTut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Coming soon :)",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
