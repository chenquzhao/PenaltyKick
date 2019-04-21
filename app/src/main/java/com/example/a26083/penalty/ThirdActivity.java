package com.example.a26083.penalty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

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

}
}
