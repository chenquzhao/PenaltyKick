package com.example.a26083.penalty;

import android.media.Image;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.gesture.Gesture;
import static android.view.GestureDetector.*;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class ThirdActivity extends AppCompatActivity implements OnGestureListener,OnDoubleTapListener{

    private static ImageView textView;
    private GestureDetectorCompat GestureDetect;
    private ImageView soccer;
    private String value1, value2, value3;
//    private int sp = Integer.parseInt(value1);
//    private int sa = Integer.parseInt(value2);
//    private int ms = Integer.parseInt(value3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        soccer = (ImageView) findViewById(R.id.soccer);
        soccer.setImageResource(R.drawable.soccer);

        TextView txt3 = (TextView) findViewById(R.id.txt3);
        TextView txt4 = (TextView) findViewById(R.id.txt4);
        TextView txt5 = (TextView) findViewById(R.id.txt5);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value1 = extras.getString("pow");
            value2 = extras.getString("acc");
            value3 = extras.getString("min");
            txt3.setText(value1);
            txt4.setText(value2);
            txt5.setText(value3);
    }

        textView = (ImageView) findViewById(R.id.txt6);
        textView.setImageResource(R.drawable.goal);

        GestureDetect = new GestureDetectorCompat(this,this);
        GestureDetect.setOnDoubleTapListener(this);

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

        soccer.setTranslationX(0);
        soccer.setTranslationY(0);
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
//        soccer.setTranslationX(-300);
//        soccer.setTranslationY(-300);

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {


    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float minMove = 120;
        float minVelocity = 0;
        float beginX = e1.getX();
        float endX = e2.getX();
        float beginY = e1.getY();
        float endY = e2.getY();

        int one = 200+ (int) (Math.random() * 400);
        int two = 50+ (int) (Math.random() * 550);
        int three = 50+ (int) (Math.random() * 550);
        int four = 200 + (int) (Math.random() * 400);
        int five = 200+ (int) (Math.random() * 400);



//        if(beginX - endX > minMove && Math.abs(velocityX) > minVelocity&& Math.abs(beginY - endY)<minMove){
//            soccer.setTranslationX(-one);
//            Toast.makeText(this,"left",Toast.LENGTH_SHORT).show();
//        }
         if(beginX - endX > minMove && Math.abs(velocityX) > minVelocity&&beginY - endY > minMove && Math.abs(velocityY) > minVelocity){
            soccer.setTranslationX(-two);
            soccer.setTranslationY(-one);
            Toast.makeText(this,"leftup",Toast.LENGTH_SHORT).show();
        }
//        else if(endX - beginX > minMove && Math.abs(velocityX) > minVelocity&&Math.abs(beginY - endY)<minMove){
//            soccer.setTranslationX(two);
//            Toast.makeText(this,"right",Toast.LENGTH_SHORT).show();
//        }
        else if(endX - beginX > minMove && Math.abs(velocityX) > minVelocity&&beginY - endY > minMove && Math.abs(velocityY) > minVelocity){
            soccer.setTranslationX(three);
            soccer.setTranslationY(-four);
            Toast.makeText(this,"rightup",Toast.LENGTH_SHORT).show();
        }
        else if(beginY - endY > minMove && Math.abs(velocityY) > minVelocity&&Math.abs(beginX - endX)<minMove){
            soccer.setTranslationY(-five);
            Toast.makeText(this,"up",Toast.LENGTH_SHORT).show();
        }
//        else if(endY - beginY > minMove && Math.abs(velocityY) > minVelocity){
//            soccer.setTranslationY(300);
//            Toast.makeText(this,"down",Toast.LENGTH_SHORT).show();
//        }

        return false;
    }
}
