package com.example.app02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView myTextView = findViewById(R.id.myTextView);
        Intent intent = getIntent();
        String temp = intent.getStringExtra("key");
        myTextView.setText(temp);
    }
}
