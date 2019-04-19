package com.example.a26083.penalty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Player ronaldo = new Player();
        ronaldo.setName("Cristiano Ronaldo");
        ronaldo.setShootPower("95");
        ronaldo.setShootAccuracy("92");
        ronaldo.setMindset("90");

        Player messi = new Player();
        messi.setName("Lionel Messi");
        messi.setShootPower("92");
        messi.setShootAccuracy("94");
        messi.setMindset("87");

        Player neymar = new Player();
        neymar.setName("Neymar");
        neymar.setShootPower("90");
        neymar.setShootAccuracy("88");
        neymar.setMindset("82");

        Player son = new Player();
        son.setName("Heung-Min Son");
        son.setShootPower("88");
        son.setShootAccuracy("86");
        son.setMindset("80");

        Player bale = new Player();
        bale.setName("Gareth Bale");
        bale.setShootPower("90");
        bale.setShootAccuracy("87");
        bale.setMindset("75");

        Player salah = new Player();
        salah.setName("Mohamed Salah");
        salah.setShootPower("92");
        salah.setShootAccuracy("90");
        salah.setMindset("82");

        Player lei = new Player();
        lei.setName("Wu Lei");
        lei.setShootPower("80");
        lei.setShootAccuracy("78");
        lei.setMindset("70");

        Player kong = new Player();
        kong.setName("Kong Qiwen");
        kong.setShootPower("60");
        kong.setShootAccuracy("58");
        kong.setMindset("38");

        Button btnThree = (Button) findViewById(R.id.btnThree);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            ImageView text1 = (ImageView) findViewById(R.id.txt1);
            TextView text2 = (TextView) findViewById(R.id.txt2);


            if(value.equalsIgnoreCase("Cristiano Ronaldo")){
                text1.setImageResource(R.drawable.cr7);
                text2.setText(ronaldo.getProfile());
            }
            else if (value.equalsIgnoreCase("Messi")) {
                text1.setImageResource(R.drawable.mes);
                text2.setText(messi.getProfile());
            }
            else if (value.equalsIgnoreCase("Neymar")) {
                text1.setImageResource(R.drawable.ney);
                text2.setText(neymar.getProfile());
            }
            else if (value.equalsIgnoreCase("Heung-Min Son")) {
                text1.setImageResource(R.drawable.son);
                text2.setText(son.getProfile());
            }
            else if (value.equalsIgnoreCase("Bale")) {
                text1.setImageResource(R.drawable.bale);
                text2.setText(bale.getProfile());
            }
            else if (value.equalsIgnoreCase("Salah")) {
                text1.setImageResource(R.drawable.salah);
                text2.setText(salah.getProfile());
            }
            else if (value.equalsIgnoreCase("Wu Lei")) {
                text1.setImageResource(R.drawable.lei);
                text2.setText(lei.getProfile());
            }
            else if (value.equalsIgnoreCase("Kong")) {
                text1.setImageResource(R.drawable.kong);
                text2.setText(kong.getProfile());
            }

                btnThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
