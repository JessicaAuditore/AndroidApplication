package com.example.app03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView resultView = findViewById(R.id.result);
        Intent intent = getIntent();
        int intLeftOp = Integer.parseInt(intent.getStringExtra("leftOp"));
        int intRightOp = Integer.parseInt(intent.getStringExtra("rightOp"));
        int result = intLeftOp * intRightOp;
        resultView.setText(intLeftOp + "*" + intRightOp + "=" + result);
        Log.i("APP03","ResultActivity--->onCreate");
    }

    @Override
    protected void onStart() {
        Log.i("APP03","ResultActivity--->onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i("APP03","ResultActivity--->onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("APP03","ResultActivity--->onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i("APP03","ResultActivity--->onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i("APP03","ResultActivity--->onResume");
        super.onResume();
    }
}
