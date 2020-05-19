package com.example.a26083.penalty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PlayerSelection extends AppCompatActivity {

    // declare pick button
    private Button btnPick;

    private LinearLayout player_selection;

    // declare layout
    private LinearLayout checklist;

    // number of checkbox, selections
    int count, selection = 0;

    private int cost = 0;

    // save selected checkbox
    private StringBuffer stringBuffer = new StringBuffer();

    // store player names
    private List<String> players = new LinkedList<String>(){{
       add("Ronaldo");
       add("Zidane");
       add("Henry");
       add("Raul");
       add("Ronaldinho");
       add("Cristiano Ronaldo");
       add("Messi");
       add("Mbappe");
       add("Hazard");
       add("De Bruyne");
       add("Lewandowski");
       add("Neymar");
       add("Son");
       add("Salah");
       add("Modric");
       add("Zaza");
       add("Lingard");
       add("Wei");
       add("Kong");
       add("Balotelli");
    }};

    private ArrayList<Integer> selected_players = new ArrayList<Integer>();

    // convert bool value to readable words
    protected String boolConvert(boolean ischecked)
    {
        if(ischecked) return "picked";
        return "dropped";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_selection);

        player_selection = findViewById(R.id.player_selection);
        player_selection.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGreen));

        final TextView caution = findViewById(R.id.caution);

        /* 1. monitor each checkbox */
        checklist = findViewById(R.id.checklist);
        count = checklist.getChildCount();

        for(int i = 0; i < count; i++)
        {
            CheckBox box = (CheckBox) checklist.getChildAt(i);
            final int finalI = i;
            box.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    caution.setText("");
//                    Toast.makeText(getApplicationContext(),players.get(finalI)+" "+boolConvert(isChecked), Toast.LENGTH_SHORT).show();
                }

            });
        }

        /* 2. monitor textview */
        TextView textView = findViewById(R.id.textview);
        textView.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View v) {
                stringBuffer.append("Your selections:");
                for(int i = 0; i < count; i++)
                {
                    CheckBox box=(CheckBox)checklist.getChildAt(i);
                    if(box.isChecked()){
                        stringBuffer.append(" " + players.get(i));
                    }
                }
                Toast.makeText(getApplicationContext(),stringBuffer,Toast.LENGTH_SHORT).show();

                // clear content, reset to initial state
                stringBuffer=new StringBuffer();
            }
        });

        /* 3. monitor checklist */
        btnPick = findViewById(R.id.btnPick);
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i < count; i++)
                {
                    CheckBox box=(CheckBox)checklist.getChildAt(i);
                    if(box.isChecked()){
                        selection += 1;
                        selected_players.add(i);

                        if(i <= 4)
                        {
                            cost += 5;
                        }
                        else if(i <= 9)
                        {
                            cost += 4;
                        }
                        else if(i <= 14)
                        {
                            cost += 3;
                        }
                        else
                        {
                            cost += 1;
                        }
                    }
                }

                if (selection != 5)
                {
                    caution.setText("Selections must be 5!");
                    selected_players.clear();
                    cost = 0;
                }
//                else if (cost > 14)
//                {
//                    caution.setText("Cost exceeds limit!");
//                    selected_players.clear();
//                    cost = 0;
//                }
                else
                {
                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList("selected_players",selected_players);

                    Intent intent = new Intent(PlayerSelection.this, Substitution.class);
                    intent.putExtra("box", bundle);
                    startActivity(intent);
                }
                selection = 0;

            }
        });
    }

}
