package com.example.a26083.penalty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    private TextView textView, coin_change, bet_result;
    private Button home;

    private MediaPlayer coin_drop;

    private LinearLayout activity_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        activity_result = findViewById(R.id.activity_result);
        activity_result.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGreen));

        // receive result
        textView = findViewById(R.id.result);
        coin_change = findViewById(R.id.coin_change);
        bet_result = findViewById(R.id.bet_result);

        coin_drop = MediaPlayer.create(Result.this, R.raw.coin_drop);

        coin_drop.start();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("box");

        int p_score = bundle.getInt("p_score");
        int ai_score = bundle.getInt("ai_score");

        textView.setText(p_score + " : " + ai_score);

        if(p_score + ai_score > 7)
        {
            if(Substitution.getBet_type() == 0)
            {
                bet_result.setText("Bet Loss! EP " + "-" + Substitution.getStake_coin());
                MainActivity.setCoins(MainActivity.getCoins() - Substitution.getStake_coin());
            }
            else if(Substitution.getBet_type() == 1)
            {
                bet_result.setText("Bet Win! EP " + "+" + Substitution.getStake_coin());
                MainActivity.setCoins(MainActivity.getCoins() + Substitution.getStake_coin());
            }
        }
        else if(p_score + ai_score < 7)
        {
            if(Substitution.getBet_type() == 1)
            {
                bet_result.setText("Bet Loss! EP " + "-" + Substitution.getStake_coin());
                MainActivity.setCoins(MainActivity.getCoins() - Substitution.getStake_coin());
            }
            else if(Substitution.getBet_type() == 0)
            {
                bet_result.setText("Bet Win! EP " + "+" + Substitution.getStake_coin());
                MainActivity.setCoins(MainActivity.getCoins() + Substitution.getStake_coin());
            }
        }
        else if(Substitution.getBet_type() != -1)
        {
            bet_result.setText("Bet Void! Stake returned");
        }

        // coin change
        if(p_score > ai_score)
        {
            coin_change.setText("Game Win! EP +100");

            SharedPreferences sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("coins", MainActivity.getCoins() + 100);
            editor.commit();
        }
        else
        {
            coin_change.setText("Game Loss! EP -100");

            SharedPreferences sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("coins", MainActivity.getCoins() - 100);
            editor.commit();
        }

        // home
        home = findViewById(R.id.home_page);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Result.this, MainActivity.class);
                startActivity(intent2);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        coin_drop.stop();
        coin_drop.prepareAsync();
    }
}
