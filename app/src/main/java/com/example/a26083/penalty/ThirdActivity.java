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


public class ThirdActivity extends AppCompatActivity implements OnGestureListener,OnDoubleTapListener{

    private static ImageView textView;
    private GestureDetectorCompat GestureDetect;
    private ImageView soccer;


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
            String value = extras.getString("pow");
            String value2 = extras.getString("acc");
            String value3 = extras.getString("min");
            txt3.setText(value);
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

        soccer.setTranslationX(10);
        soccer.setTranslationY(10);
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
        soccer.setTranslationX(-300);
        soccer.setTranslationY(-300);

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {


    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        return false;
    }
}
