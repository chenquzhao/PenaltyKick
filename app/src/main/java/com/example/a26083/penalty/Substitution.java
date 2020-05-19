package com.example.a26083.penalty;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Substitution extends AppCompatActivity {

    private LinearLayout activity_substitution;

    private Button kick_first, save_first;

    private Button reset, ok;

    private Button goal_under, goal_over;
    private TextView name_one, name_two, name_three, name_four, name_five, substitution, coin_view;

    private int id_one, id_two, id_three, id_four, id_five;
    private String str_one, str_two, str_three, str_four, str_five;

    private int one_selected, two_selected, three_selected, four_selected, five_selected = 0;

    private ArrayList<Integer> selected_players;

    private int flag_under = 0;
    private int flag_over = 0;
    private int flag_kick = 0;
    private int flag_save = 0;

    private EditText stake;

    private TextView hint;

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

    private StringBuffer subs = new StringBuffer("SUB: ");

    private ArrayList<Integer> substituted_players = new ArrayList<Integer>();

    public static int stake_coin = 0;
    public static int bet_type = -1; // 0 - under, 1 - over

    public static int human_first = 1;

    public static int getHuman_first() {
        return human_first;
    }

    public static int getStake_coin() {
        return stake_coin;
    }

    public static int getBet_type() {
        return bet_type;
    }

    public static void setStake_coin(int stake_coin) {
        Substitution.stake_coin = stake_coin;
    }

    public static void setBet_type(int bet_type) {
        Substitution.bet_type = bet_type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substitution);

        activity_substitution = findViewById(R.id.activity_substitution);
        activity_substitution.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGreen));

        kick_first = findViewById(R.id.kick_first);
        save_first = findViewById(R.id.save_first);

        name_one = findViewById(R.id.name_one);
        name_two = findViewById(R.id.name_two);
        name_three = findViewById(R.id.name_three);
        name_four = findViewById(R.id.name_four);
        name_five = findViewById(R.id.name_five);

        substitution = findViewById(R.id.substitution);

        stake = findViewById(R.id.stake);

        reset = findViewById(R.id.reset_sub);
        ok = findViewById(R.id.confirm_sub);

        goal_under = findViewById(R.id.goal_under);
        goal_over = findViewById(R.id.goal_over);

        hint = findViewById(R.id.betHint);

        coin_view = findViewById(R.id.coin_view1);
        coin_view.setText(MainActivity.getCoins() + " EP");

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("box");

        selected_players = bundle.getIntegerArrayList("selected_players");

        id_one = selected_players.get(0);
        id_two = selected_players.get(1);
        id_three = selected_players.get(2);
        id_four = selected_players.get(3);
        id_five = selected_players.get(4);

        str_one = players.get(id_one);
        str_two = players.get(id_two);
        str_three = players.get(id_three);
        str_four = players.get(id_four);
        str_five = players.get(id_five);

        name_one.setText(str_one);
        name_two.setText(str_two);
        name_three.setText(str_three);
        name_four.setText(str_four);
        name_five.setText(str_five);

        stake_coin = 0;
        bet_type = -1;
        human_first = 1;

        kick_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag_kick)
                {
                    case 0:
                        break;
                    case 1:
                        human_first = 1;
                        kick_first.setActivated(false); // dark
                        save_first.setActivated(false);
                        flag_save = 0;
                        flag_kick = 0;
                        break;
                }
            }
        });

        save_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag_save)
                {
                    case 0:
                        human_first = 0;
                        save_first.setActivated(true);
                        kick_first.setActivated(true);
                        flag_save = 1;
                        flag_kick = 1;
                        break;
                    case 1:
                        break;
                }
            }
        });

        name_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(one_selected == 0)
                {
                    subs.append(str_one + " ");
                    substitution.setText(subs);
                    one_selected = 1;
                    substituted_players.add(id_one);
                }
            }
        });

        name_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(two_selected == 0)
                {
                    subs.append(str_two + " ");
                    substitution.setText(subs);
                    two_selected = 1;
                    substituted_players.add(id_two);
                }
            }
        });

        name_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(three_selected == 0)
                {
                    subs.append(str_three + " ");
                    substitution.setText(subs);
                    three_selected = 1;
                    substituted_players.add(id_three);
                }
            }
        });

        name_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(four_selected == 0)
                {
                    subs.append(str_four + " ");
                    substitution.setText(subs);
                    four_selected = 1;
                    substituted_players.add(id_four);
                }
            }
        });

        name_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(five_selected == 0)
                {
                    subs.append(str_five + " ");
                    substitution.setText(subs);
                    five_selected = 1;
                    substituted_players.add(id_five);
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subs.delete(5, subs.length());
                substitution.setText(subs);
                one_selected = 0;
                two_selected = 0;
                three_selected = 0;
                four_selected = 0;
                five_selected = 0;
                substituted_players.clear();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

                if(bet_type != -1)
                {
                    if(!pattern.matcher(stake.getText().toString()).matches() || stake.getText().toString().length() <= 0)
                    {
                        hint.setText("Check stake input");
                        return;
                    }
                    else if(Integer.parseInt(stake.getText().toString()) <= 0)
                    {
                        hint.setText("Bad stake");
                        return;
                    }
                    else if(Integer.parseInt(stake.getText().toString()) > MainActivity.getCoins())
                    {
                        hint.setText("Balance not enough : (");
                        return;
                    }
                }

                if(substituted_players.size() == 5)
                {
                    if(stake.getText().toString().length() > 0) {
                        stake_coin = Integer.parseInt(stake.getText().toString());
                    }

                        Bundle bundle = new Bundle();
                        bundle.putIntegerArrayList("selected_players",substituted_players);

                        Intent intent = new Intent(Substitution.this, ThirdActivity.class);
                        intent.putExtra("box", bundle);
                        startActivity(intent);

                }

            }
        });

        goal_under.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag_under)
                {
                    case 0:
                        bet_type = 0;
                        goal_under.setActivated(true);
                        goal_over.setActivated(false);
                        flag_under = 1;
                        break;
                    case 1:
                        bet_type = -1;
                        goal_under.setActivated(false);
                        flag_under = 0;
                        break;
                }
            }
        });

        goal_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag_over)
                {
                    case 0:
                        bet_type = 1;
                        goal_over.setActivated(true);
                        goal_under.setActivated(false);
                        flag_over = 1;
                        break;
                    case 1:
                        bet_type = -1;
                        goal_over.setActivated(false);
                        flag_over = 0;
                        break;
                }
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
