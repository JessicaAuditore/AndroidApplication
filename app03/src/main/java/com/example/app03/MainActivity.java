package com.example.app03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText leftOp;
    private EditText rightOp;
    private TextView operator;
    private Button caculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftOp = findViewById(R.id.leftOp);
        rightOp = findViewById(R.id.rightOp);
        operator = findViewById(R.id.operator);
        caculate = findViewById(R.id.caculate);
        operator.setText(R.string.operator);
        caculate.setText(R.string.caculator);
        caculate.setOnClickListener(new CaculateListener());
        Log.i("APP03","MainActivity--->onCreate");
    }

    @Override
    protected void onStart() {
        Log.i("APP03","MainActivity--->onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i("APP03","MainActivity--->onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("APP03","MainActivity--->onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i("APP03","MainActivity--->onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i("APP03","MainActivity--->onResume");
        super.onResume();
    }

    class CaculateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String strLeftOp = leftOp.getText().toString();
            String strRightOp = rightOp.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("leftOp", strLeftOp);
            intent.putExtra("rightOp", strRightOp);
            intent.setClass(MainActivity.this, ResultActivity.class);
            startActivity(intent);
        }
    }
}
