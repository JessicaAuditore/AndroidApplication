package com.example.app06;

import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int intColor = 0;
    GridLayout gridLayout;
    final int[] colors = new int[]{
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5
    };

    final int[] textviews = new int[]{
            R.id.textview01,
            R.id.textview02,
            R.id.textview03,
            R.id.textview04,
            R.id.textview05
    };

    TextView[] views = new TextView[textviews.length];

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                for (int i = 0; i < views.length; i++) {
                    views[i].setBackgroundResource(colors[(i + intColor) % textviews.length]);
                }
                intColor = (intColor + 1) % (textviews.length - 1);
            }
            super.handleMessage(msg);
        }
    };

    String[] chars = new String[]{
            "7", "8", "9", "÷",
            "4", "5", "6", "×",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < textviews.length; i++) {
            views[i] = (TextView) findViewById(textviews[i]);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        }, 0, 200);
        gridLayout = findViewById(R.id.gridlayout);
        initCaculator();
    }

    private void initCaculator() {
        for (int i = 0; i < chars.length; i++) {
            Button button = new Button(this);
            button.setText(chars[i]);
            button.setTextSize(40);
            button.setId(i);
            button.setPadding(15, 35, 15, 35);
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.buttonColor));
            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);
            GridLayout.Spec columnSpec = GridLayout.spec(i % 4);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            params.setGravity(Gravity.FILL);
            gridLayout.addView(button, params);
        }
    }
}
