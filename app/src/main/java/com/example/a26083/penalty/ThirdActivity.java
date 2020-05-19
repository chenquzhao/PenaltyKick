package com.example.a26083.penalty;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import android.view.Display;
import android.view.MotionEvent;
import android.gesture.Gesture;
import static android.view.GestureDetector.*;
import static java.lang.StrictMath.abs;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ThirdActivity extends AppCompatActivity implements OnGestureListener,OnDoubleTapListener{

    private GestureDetectorCompat GestureDetect;

    private RelativeLayout activity_third;

    private int power = 25;

    private int score_valid;

    private ImageView soccer;
    private ImageView keeper;

    private ImageView keeper_mark;
    private ImageView soccer_mark;
    private ImageView key_point;

    private ImageView leftDown_save, leftMiddle_save, leftTop_save;
    private ImageView middleTop_save, middleDown_save;
    private ImageView rightDown_save, rightMiddle_save, rightTop_save;

    private MediaPlayer kick_music;
    private MediaPlayer goal_music;
    private MediaPlayer caromhall;

    private TextView scoreBoard;
    private TextView reminder;
    private TextView scoreBoard_two;
    private TextView axis;

    private StringBuffer scores = new StringBuffer("HUM:");
    private StringBuffer scores_two = new StringBuffer("COM:");

    private TextView score_one, score_two;

    private int p_score, ai_score = 0;
    private int p_kick, ai_kick = 0;

    private int label_player;
    private int label_keeper;
    private int play_mode; // 0 - to kick, 1 - to save

    private int xMove, yMove;
    private Random random = new Random();
    private DimsCollection dimsCollection = new DimsCollection();
    private int inExcuting = 0;

    private Button lStep, rStep, uStep, dStep, perform, home;

    private ImageView fut_card;

    private int xlen = 0;
    private int ylen = -220;

    private ObjectAnimator set_keeper;

    private int is_saved = 0;

    private int game_over = 0;

    private int fut_card_id = 0;

    private ArrayList<Integer> selected_players;

    private static final int COMPLETED = 0;

    private SeekBar power_control;
    private TextView power_intro;

    @SuppressLint("HandlerLeak")
    private Handler judge_goal = new Handler()
    {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == COMPLETED)
            {
                if(score_valid == 0)
                {
                    reminder.setText("Miss");
                }
                else
                {
                    reminder.setText("GOOOOOOOOAL");
                }
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler score_handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what == COMPLETED)
            {
                score_one.setText(String.valueOf(p_score));
                score_two.setText(String.valueOf(ai_score));

                scoreBoard.setText(scores);
                scoreBoard_two.setText(scores_two);
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler save_handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg_save) {
            if(msg_save.what == COMPLETED)
            {
                int save_label = random_index(0, 8);

                ObjectAnimator saveX = ObjectAnimator.ofFloat(soccer,"TranslationX",dimsCollection.getSaves_end()[save_label][0]);
                ObjectAnimator saveY = ObjectAnimator.ofFloat(soccer,"TranslationY",dimsCollection.getSaves_end()[save_label][1]);
                AnimatorSet set_save = new AnimatorSet();
                set_save.play(saveX).with(saveY);
                set_save.setDuration(500);
                set_save.start();
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler settle_handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg_game) {
            if(msg_game.what == COMPLETED)
            {
                if(p_score > ai_score)
                {
                    reminder.setText("Player Wins!!!");
                    axis.setText("What a clever man!");
                }
                else
                {
                    axis.setText("Try again?");
                    if(p_score < ai_score)
                    {
                        reminder.setText("AI wins!!!");
                    }
                    else
                    {
                        reminder.setText("Tie!!! Have a cup of tea!");
                    }
                }

                home.setVisibility(View.VISIBLE);
                keeper.setVisibility(View.INVISIBLE);
                soccer.setVisibility(View.INVISIBLE);
                fut_card.setVisibility(View.INVISIBLE);
                power_control.setVisibility(View.INVISIBLE);
                power_intro.setVisibility(View.INVISIBLE);
                keeper_mark.clearAnimation();
                soccer_mark.clearAnimation();
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler settle_keeper = new Handler()
    {
        @Override
        public void handleMessage(Message msg_keeper) {
            if(msg_keeper.what == COMPLETED)
            {
                reminder.setText("");
                key_point.setVisibility(View.INVISIBLE);
                keeper.setVisibility(View.VISIBLE);

                if(play_mode == 0)
                {
                    display_fut_card(selected_players.get(fut_card_id));
                    setFlickerAnimation(soccer_mark);
                    power_control.setVisibility(View.VISIBLE);
                    power_intro.setVisibility(View.VISIBLE);
                    power_control.setProgress(25);
                }
                else
                {
                    fut_card.setImageResource(R.drawable.fut_ai);
                    setFlickerAnimation(keeper_mark);
                    power_control.setVisibility(View.INVISIBLE);
                    power_intro.setVisibility(View.INVISIBLE);
                }
            }

        }
    };

    @SuppressLint("HandlerLeak")
    private Handler settle_key = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == COMPLETED)
            {
                inExcuting = 1;
                key_point.setVisibility(View.VISIBLE);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(key_point, "scaleX", 1f, 1.5f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(key_point, "scaleY", 1f, 1.5f);

                AnimatorSet set = new AnimatorSet();
                set.play(animator1).with(animator2);
                set.setDuration(4000);
                set.start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        key_point.setVisibility(View.INVISIBLE);
                        inExcuting = 0;
                    }
                }, 5000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(Substitution.getHuman_first() == 1)
        {
            play_mode = 0;
        }
        else
        {
            play_mode = 1;
        }

        kick_music = MediaPlayer.create(ThirdActivity.this, R.raw.peng);
        goal_music = MediaPlayer.create(ThirdActivity.this, R.raw.goal);
        caromhall = MediaPlayer.create(ThirdActivity.this, R.raw.caromhall);

        MainActivity.time_bomb.stop();
        MainActivity.time_bomb.prepareAsync();

        caromhall.start();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("box");

        selected_players = bundle.getIntegerArrayList("selected_players");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        activity_third = findViewById(R.id.third_activity);

        activity_third.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLightGreen));

        keeper_mark = findViewById(R.id.keeper_mark);
        soccer_mark = findViewById(R.id.soccer_mark);

        key_point = findViewById(R.id.key_point);

        key_point.setVisibility(View.INVISIBLE);

        keeper_mark.setVisibility(View.INVISIBLE);
        soccer_mark.setVisibility(View.INVISIBLE);


        power_control = findViewById(R.id.power_control);
        power_control.setVisibility(View.INVISIBLE);

        power_intro = findViewById(R.id.power_intro);
        power_intro.setVisibility(View.INVISIBLE);

        soccer = findViewById(R.id.soccer);
        keeper = findViewById(R.id.keeper);

        leftDown_save = findViewById(R.id.leftsave);
        leftMiddle_save = findViewById(R.id.leftmiddlesave);
        leftTop_save = findViewById(R.id.lefttopsave);

        middleDown_save = findViewById(R.id.middledownsave);
        middleTop_save = findViewById(R.id.middletopsave);

        rightDown_save = findViewById(R.id.rightsave);
        rightMiddle_save = findViewById(R.id.rightmiddlesave);
        rightTop_save = findViewById(R.id.righttopsave);

        keeper.setImageResource(R.drawable.keeper);

        fut_card = findViewById(R.id.fut_card);



        if(play_mode == 0)
        {
            display_fut_card(selected_players.get(fut_card_id));
            setFlickerAnimation(soccer_mark);
            power_control.setVisibility(View.VISIBLE);
            power_intro.setVisibility(View.VISIBLE);
        }
        else
        {
            fut_card.setImageResource(R.drawable.fut_ai);
            setFlickerAnimation(keeper_mark);
        }

        leftDown_save.setImageResource(R.drawable.leftsave);
        leftMiddle_save.setImageResource(R.drawable.leftmiddle);
        leftTop_save.setImageResource(R.drawable.lefttop);

        middleDown_save.setImageResource(R.drawable.downsave);
        middleTop_save.setImageResource(R.drawable.middlesave);

        rightDown_save.setImageResource(R.drawable.rightsave);
        rightMiddle_save.setImageResource(R.drawable.rightmiddle);
        rightTop_save.setImageResource(R.drawable.righttop);

        leftDown_save.setVisibility(View.INVISIBLE);
        leftMiddle_save.setVisibility(View.INVISIBLE);
        leftTop_save.setVisibility(View.INVISIBLE);

        middleDown_save.setVisibility(View.INVISIBLE);
        middleTop_save.setVisibility(View.INVISIBLE);

        rightDown_save.setVisibility(View.INVISIBLE);
        rightMiddle_save.setVisibility(View.INVISIBLE);
        rightTop_save.setVisibility(View.INVISIBLE);

//        keeper.setImageResource(R.drawable.keeper);

        GestureDetect = new GestureDetectorCompat(this,this);
        GestureDetect.setOnDoubleTapListener(this);

        scoreBoard = findViewById(R.id.scoreBoard);
        reminder = findViewById(R.id.reminder);
        scoreBoard_two = findViewById(R.id.scoreBoard_two);
        axis = findViewById(R.id.axis);

        score_one = findViewById(R.id.score_one);
        score_two = findViewById(R.id.score_two);

        lStep = findViewById(R.id.leftStep);
        rStep = findViewById(R.id.RightStep);
        uStep = findViewById(R.id.UpStep);
        dStep = findViewById(R.id.DownStep);

        home = findViewById(R.id.home);
        home.setVisibility(View.INVISIBLE);

        axis.setText("Don't shoot me sir, I am not Sivir : )");

//        lStep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                xlen -= 10;
//                soccer.setTranslationX(xlen);
//                axis.setText("X: " + xlen + " Y: " + ylen);
//            }
//        });
//
//        rStep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                xlen += 10;
//                soccer.setTranslationX(xlen);
//                axis.setText("X: " + xlen + " Y: " + ylen);
//            }
//        });
//
//        uStep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ylen -= 10;
//                soccer.setTranslationY(ylen);
//                axis.setText("X: " + xlen + " Y: " + ylen);
//            }
//        });
//
//        dStep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ylen += 10;
//                soccer.setTranslationY(ylen);
//                axis.setText("X: " + xlen + " Y: " + ylen);
//            }
//        });

        perform = findViewById(R.id.perform);

//        perform.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                soccer.setTranslationX(dimsCollection.getDims_rt()[index][0]);
//                soccer.setTranslationY(dimsCollection.getDims_rt()[index][1]);
//                if(index < dimsCollection.getDims_rt().length - 1) index += 1;
//            }
//        });

        lStep.setVisibility(View.INVISIBLE);
        rStep.setVisibility(View.INVISIBLE);
        uStep.setVisibility(View.INVISIBLE);
        dStep.setVisibility(View.INVISIBLE);
        perform.setVisibility(View.INVISIBLE);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("p_score", p_score);
                bundle.putInt("ai_score", ai_score);

                Intent intent1 = new Intent(ThirdActivity.this, Result.class);
                intent1.putExtra("box", bundle);
                startActivity(intent1);
            }
        });

        bindViews();

    }

    private void bindViews()
    {
        power_control.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                power = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setFlickerAnimation(ImageView iv_chat_head) {
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); //
        iv_chat_head.setAnimation(animation);
    }

    @Override
    protected void onStop() {
        caromhall.stop();
        caromhall.prepareAsync();

        super.onStop();
    }

    @Override
    public void onDestroy() {
        kick_music.stop();
        goal_music.stop();
        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GestureDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    // show fut card
    private void display_fut_card(int index)
    {
        switch (index)
        {
            case 0:
                fut_card.setImageResource(R.drawable.fut_ronaldo);
                break;
            case 1:
                fut_card.setImageResource(R.drawable.fut_zidane);
                break;
            case 2:
                fut_card.setImageResource(R.drawable.fut_henry);
                break;
            case 3:
                fut_card.setImageResource(R.drawable.fut_raul);
                break;

            case 4:
                fut_card.setImageResource(R.drawable.fut_ronaldinho);
                break;
            case 5:
                fut_card.setImageResource(R.drawable.fut_cr7);
                break;
            case 6:
                fut_card.setImageResource(R.drawable.fut_messi);
                break;
            case 7:
                fut_card.setImageResource(R.drawable.fut_mbappe);
                break;
            case 8:
                fut_card.setImageResource(R.drawable.fut_hazard);
                break;
            case 9:
                fut_card.setImageResource(R.drawable.fut_debru);
                break;
            case 10:
                fut_card.setImageResource(R.drawable.fut_lewandowski);
                break;
            case 11:
                fut_card.setImageResource(R.drawable.fut_neymar);
                break;
            case 12:
                fut_card.setImageResource(R.drawable.fut_son);
                break;
            case 13:
                fut_card.setImageResource(R.drawable.fut_salah);
                break;
            case 14:
                fut_card.setImageResource(R.drawable.fut_modric);
                break;
            case 15:
                fut_card.setImageResource(R.drawable.fut_zaza);
                break;
            case 16:
                fut_card.setImageResource(R.drawable.fut_lingard);
                break;
            case 17:
                fut_card.setImageResource(R.drawable.fut_shihao);
                break;
            case 18:
                fut_card.setImageResource(R.drawable.fut_kong);
                break;
            case 19:
                fut_card.setImageResource(R.drawable.fut_baloteli);
                break;
        }
    }

    // perform left kick
    private void perform_leftKick(int range)
    {
        switch (range)
        {
            // in range
            case 0:
                label_player = random_index(0, 22);
                break;
            case 1:
                label_player = random_index(23, 37);
                break;
            case 2:
                label_player = random_index(38, 55);
                break;
        }
        xMove = dimsCollection.getLeft_kick()[label_player][0];
        yMove = dimsCollection.getLeft_kick()[label_player][1];
    }

    // perform middle kick
    private void perform_MiddleKick(int range)
    {
        switch (range)
        {
            // in range
            case 0:
                label_player = random_index(0, 22);
                break;
            case 1:
                label_player = random_index(23, 34);
                break;
            case 2:
                label_player = random_index(35, 49);
                break;
        }
        xMove = dimsCollection.getMiddle_kick()[label_player][0];
        yMove = dimsCollection.getMiddle_kick()[label_player][1];
    }

    // perform right kick
    private void perform_RightKick(int range)
    {
        switch (range)
        {
            // in range
            case 0:
                label_player = random_index(0, 22);
                break;
            case 1:
                label_player = random_index(23, 37);
                break;
            case 2:
                label_player = random_index(38, 55);
                break;
        }
        xMove = dimsCollection.getRight_kick()[label_player][0];
        yMove = dimsCollection.getRight_kick()[label_player][1];
    }

    private void resetSoccer()
    {
        soccer.setTranslationX(0);
        soccer.setTranslationY(0);

        if(play_mode == 0)
        {
            play_mode = 1;

        }
        else
        {
            play_mode = 0;
        }

        if (goal_music.isPlaying())
        {
            goal_music.stop();
            goal_music.prepareAsync();
        }

        if(!caromhall.isPlaying())
        {
            caromhall.start();
        }

        unshow_keeper(label_keeper);
        inExcuting = 0;
        is_saved = 0;

        Message msg_keeper = new Message();
        msg_keeper.what = COMPLETED;
        settle_keeper.sendMessage(msg_keeper);

        Message msg_game = new Message();
        msg_game.what = COMPLETED;

        Message msg_key = new Message();
        msg_key.what = COMPLETED;

        if(p_kick <= 5 && ai_kick <= 5)
        {
            if(5 - ai_kick < p_score - ai_score)
            {
                game_over = 1;
                settle_handler.sendMessage(msg_game);
            }
            else if(5 - p_kick < ai_score - p_score)
            {
                game_over = 1;
                settle_handler.sendMessage(msg_game);
            }
            else if((5 - p_kick == ai_score - p_score) ^ (5 - ai_kick == p_score - ai_score))
            {
                settle_key.sendMessage(msg_key);
            }
        }
        else if(p_kick == ai_kick)
        {
            if(p_score > ai_score)
            {
                game_over = 1;
                settle_handler.sendMessage(msg_game);
            }
            else if(p_score < ai_score)
            {
                game_over = 1;
                settle_handler.sendMessage(msg_game);
            }
            else if(p_kick == 10)
            {
                game_over = 1;
                settle_handler.sendMessage(msg_game);
            }
        }
        else
        {
            settle_key.sendMessage(msg_key);
        }
    }

    // return random integer in [start, end]
    private int random_index(int start, int end)
    {
        return random.nextInt(end - start + 1) + start;
    }

    // check if is saved
    private void check_save(int [][] dims)
    {
        if(dimsCollection.in_range(xMove, yMove, dims))
        {
            is_saved = 1;
        }
    }

    // display random keeper and check if is saved
    private void show_keeper()
    {
        switch (label_keeper)
        {
            case 0:
                leftDown_save.setVisibility(View.VISIBLE);
                set_keeper = ObjectAnimator.ofFloat(leftDown_save, "alpha", 0f, 1f);
                check_save(dimsCollection.getDims_ld());
                break;

            case 1:
                leftMiddle_save.setVisibility(View.VISIBLE);
                set_keeper = ObjectAnimator.ofFloat(leftMiddle_save, "alpha", 0f, 1f);
                check_save(dimsCollection.getDims_lm());
                break;

            case 2:
                leftTop_save.setVisibility(View.VISIBLE);
                set_keeper = ObjectAnimator.ofFloat(leftTop_save, "alpha", 0f, 1f);
                check_save(dimsCollection.getDims_lt());
                break;

            case 3:
                middleDown_save.setVisibility(View.VISIBLE);
                set_keeper = ObjectAnimator.ofFloat(middleDown_save, "alpha", 0f, 1f);
                check_save(dimsCollection.getDims_md());
                break;

            case 4:
                middleTop_save.setVisibility(View.VISIBLE);
                set_keeper = ObjectAnimator.ofFloat(middleTop_save, "alpha", 0f, 1f);
                check_save(dimsCollection.getDims_mt());
                break;

            case 5:
                rightDown_save.setVisibility(View.VISIBLE);
                set_keeper = ObjectAnimator.ofFloat(rightDown_save, "alpha", 0f, 1f);
                check_save(dimsCollection.getDims_rd());
                break;

            case 6:
                rightMiddle_save.setVisibility(View.VISIBLE);
                set_keeper = ObjectAnimator.ofFloat(rightMiddle_save, "alpha", 0f, 1f);
                check_save(dimsCollection.getDims_rm());
                break;

            case 7:
                rightTop_save.setVisibility(View.VISIBLE);
                set_keeper = ObjectAnimator.ofFloat(rightTop_save, "alpha", 0f, 1f);
                check_save(dimsCollection.getDims_rt());
                break;
        }
    }

    // hide specified keeper
    private void unshow_keeper(int num)
    {
        switch (num)
        {
            case 0:
                leftDown_save.setVisibility(View.INVISIBLE);
                break;

            case 1:
                leftMiddle_save.setVisibility(View.INVISIBLE);
                break;

            case 2:
                leftTop_save.setVisibility(View.INVISIBLE);
                break;

            case 3:
                middleDown_save.setVisibility(View.INVISIBLE);
                break;

            case 4:
                middleTop_save.setVisibility(View.INVISIBLE);
                break;

            case 5:
                rightDown_save.setVisibility(View.INVISIBLE);
                break;

            case 6:
                rightMiddle_save.setVisibility(View.INVISIBLE);
                break;

            case 7:
                rightTop_save.setVisibility(View.INVISIBLE);
                break;
        }
    }

    // fling gesture
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if(inExcuting == 1 || game_over == 1) return false;

        inExcuting = 1;

        float minMove = 120;

        float beginX = e1.getX();
        float endX = e2.getX();
        float beginY = e1.getY();
        float endY = e2.getY();

        if((beginY - endY <= minMove) && play_mode == 0)
        {
            inExcuting = 0;
            return false;
        }

        if((abs(beginY - endY) <= minMove) && abs(beginX - endX) <= minMove)
        {
            inExcuting = 0;
            return false;
        }

        //x -470, y -540 left top
        //x -470, y -220 left bottom

        //x 0, y -540 middle top
        //x 0, y -220 middle bottom

        //x 470, y -540 right top
        //x 470, y -220 right bottom

        keeper_mark.clearAnimation();
        soccer_mark.clearAnimation();

        kick_music.start();


        switch(play_mode)
        {
            case 0: // to kick
                p_kick += 1;

                int[] xxx_shoot;
                int shoot_selection;

                if(selected_players.get(fut_card_id) <= 4)
                {
                    xxx_shoot = dimsCollection.getLegend_shoot();
                    if(power <= 50)
                    {
                        shoot_selection = 0;
                    }
                    else
                    {
                        shoot_selection = random_index(2, 9);
                    }
                }
                else if(selected_players.get(fut_card_id) <= 9)
                {
                    xxx_shoot = dimsCollection.getSuper_shoot();
                    if(power <= 50)
                    {
                        shoot_selection = 0;
                    }
                    else
                    {
                        shoot_selection = random_index(4, 9);
                    }
                }
                else if(selected_players.get(fut_card_id) <= 14)
                {
                    xxx_shoot = dimsCollection.getStar_shoot();
                    if(power <= 50)
                    {
                        shoot_selection = 0;
                    }
                    else
                    {
                        shoot_selection = random_index(4, 9);
                    }
                }
                else
                {
                    xxx_shoot = dimsCollection.getWizard_shoot();
                    if(power <= 50)
                    {
                        shoot_selection = 0;
                    }
                    else
                    {
                        shoot_selection = random_index(2, 9);
                    }
                }

                if(beginX - endX > minMove && beginY - endY > minMove){

                    perform_leftKick(xxx_shoot[shoot_selection]);

                }
                else if(endX - beginX > minMove && beginY - endY > minMove){

                    perform_RightKick(xxx_shoot[shoot_selection]);

                }
                else if(beginY - endY > minMove && Math.abs(beginX - endX) < minMove){

                    perform_MiddleKick(xxx_shoot[shoot_selection]);

                }

                label_keeper = random_index(0, 7);
                show_keeper();
                keeper.setVisibility(View.INVISIBLE);

                fut_card_id += 1;
                if(fut_card_id > 4) fut_card_id -= 5;
                break;

            case 1: // to save
                ai_kick += 1;
                label_player = random_index(0, 161);
                if(label_player <= 55)
                {
                    xMove = dimsCollection.getLeft_kick()[label_player][0];
                    yMove = dimsCollection.getLeft_kick()[label_player][1];
                }
                else if(label_player <= 105)
                {
                    xMove = dimsCollection.getMiddle_kick()[label_player - 56][0];
                    yMove = dimsCollection.getMiddle_kick()[label_player - 56][1];
                }
                else
                {
                    xMove = dimsCollection.getRight_kick()[label_player - 106][0];
                    yMove = dimsCollection.getRight_kick()[label_player - 106][1];
                }

                if(beginX - endX > minMove && endY - beginY > minMove)
                {
                    leftDown_save.setVisibility(View.VISIBLE);
                    set_keeper = ObjectAnimator.ofFloat(leftDown_save, "alpha", 0f, 1f);
                    label_keeper = 0;
                    check_save(dimsCollection.getDims_ld());
                }
                if(beginX - endX > minMove && abs(beginY - endY) < minMove)
                {
                    leftMiddle_save.setVisibility(View.VISIBLE);
                    set_keeper = ObjectAnimator.ofFloat(leftMiddle_save, "alpha", 0f, 1f);
                    label_keeper = 1;
                    check_save(dimsCollection.getDims_lm());
                }
                else if(beginX - endX > minMove && beginY - endY > minMove)
                {
                    leftTop_save.setVisibility(View.VISIBLE);
                    set_keeper = ObjectAnimator.ofFloat(leftTop_save, "alpha", 0f, 1f);
                    label_keeper = 2;
                    check_save(dimsCollection.getDims_lt());
                }
                else if(endY - beginY > minMove && Math.abs(beginX - endX) < minMove)
                {
                    middleDown_save.setVisibility(View.VISIBLE);
                    set_keeper = ObjectAnimator.ofFloat(middleDown_save, "alpha", 0f, 1f);
                    label_keeper = 3;
                    check_save(dimsCollection.getDims_md());
                }
                else if(beginY - endY > minMove && Math.abs(beginX - endX) < minMove)
                {
                    middleTop_save.setVisibility(View.VISIBLE);
                    set_keeper = ObjectAnimator.ofFloat(middleTop_save, "alpha", 0f, 1f);
                    label_keeper = 4;
                    check_save(dimsCollection.getDims_mt());
                }
                else if(endX - beginX > minMove && endY - beginY > minMove)
                {
                    rightDown_save.setVisibility(View.VISIBLE);
                    set_keeper = ObjectAnimator.ofFloat(rightDown_save, "alpha", 0f, 1f);
                    label_keeper = 5;
                    check_save(dimsCollection.getDims_rd());
                }
                else if(endX - beginX > minMove && abs(beginY - endY) < minMove)
                {
                    rightMiddle_save.setVisibility(View.VISIBLE);
                    set_keeper = ObjectAnimator.ofFloat(rightMiddle_save, "alpha", 0f, 1f);
                    label_keeper = 6;
                    check_save(dimsCollection.getDims_rm());
                }
                else if(endX - beginX > minMove && beginY - endY > minMove)
                {
                    rightTop_save.setVisibility(View.VISIBLE);
                    set_keeper = ObjectAnimator.ofFloat(rightTop_save, "alpha", 0f, 1f);
                    label_keeper = 7;
                    check_save(dimsCollection.getDims_rt());
                }

                keeper.setVisibility(View.INVISIBLE);
        }

        ObjectAnimator translationX = ObjectAnimator.ofFloat(soccer,"TranslationX",xMove);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(soccer,"TranslationY",yMove);
        AnimatorSet set = new AnimatorSet();
        set.play(translationX).with(translationY);
        set.setDuration(500);
        set.start();

        set_keeper.setDuration(500);
        set_keeper.start();

        if(is_saved == 1)
        {
            TimerTask save_action = new TimerTask() {
                @Override
                public void run() {
                    Message msg_save = new Message();
                    msg_save.what = COMPLETED;
                    save_handler.sendMessage(msg_save);
                }
            };

            Timer timer_save = new Timer();
            timer_save.schedule(save_action, 500);
        }


        TimerTask mark = new TimerTask() {
            @Override
            public void run() {
                if (is_saved == 0 && xMove >= -470 && xMove <= 470 && yMove >= -540 && yMove <= -220)
                {
                    goal_music.start();
                    if (play_mode == 0)
                    {
                        scores.append(" O");
                        p_score += 1;
                    }
                    else
                    {
                        scores_two.append(" O");
                        ai_score += 1;
                    }
                    score_valid = 1;
                }
                else
                {
                    score_valid = 0;
                    if (play_mode == 0)
                    {
                        scores.append(" X");
                    }
                    else
                    {
                        scores_two.append(" X");
                    }
                }

                Message msg1 = new Message();
                msg1.what = COMPLETED;
                judge_goal.sendMessage(msg1);

                Message msg = new Message();
                msg.what = COMPLETED;
                score_handler.sendMessage(msg);
            }
        };

        Timer timer_mark = new Timer();
        timer_mark.schedule(mark,800);

        // reset data
        TimerTask reset = new TimerTask() {
            @Override
            public void run() {
                resetSoccer();
            }
        };

        Timer timer_reset = new Timer();
        timer_reset.schedule(reset, 4800);

        return false;
    }
}
