package com.example.app02;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        button1.setOnClickListener(new Button1Listener());
        button2.setOnClickListener(new Button2Listener());
    }

    class Button1Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //实例化一个intent对象，为启动另一个Activity做准备
            Intent intent = new Intent();
            //设置改intent将要从Activity跳转到Second Activity
            intent.setClass(FirstActivity.this, SecondActivity.class);
            intent.putExtra("key", "欢迎使用《Android开发实验教程》!");
            startActivity(intent);
        }
    }

    class Button2Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse("smsto://131123456789");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms body", "欢迎使用《Android开发实验教程》!");
            startActivity(intent);
        }
    }
}
