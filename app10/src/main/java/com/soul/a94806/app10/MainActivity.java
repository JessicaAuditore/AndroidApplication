package com.soul.a94806.app10;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar bar = null;
    private Button startButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.bar);
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            bar.setVisibility(View.VISIBLE);
            updateBarHandler.post(updateThread);
        }
    }

    private Runnable updateThread = new Runnable() {

        int i = 0;

        @Override
        public void run() {
            i = i + 10;
            Message msg = updateBarHandler.obtainMessage();
            msg.arg1 = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateBarHandler.sendMessage(msg);
            if (i == 100) {
                updateBarHandler.removeCallbacks(updateThread);
                bar.setVisibility(View.GONE);
                i = 0;
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler updateBarHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateBarHandler.post(updateThread);
            bar.setProgress(msg.arg1);
        }
    };
}
