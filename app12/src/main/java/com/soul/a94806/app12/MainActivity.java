package com.soul.a94806.app12;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.soul.a94806.app12.utils.HttpDownloader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String mp3Addr = "http://file3.data.weipan.cn/79616382/74f912857d228ae0b598fcc90ff6dd1d93cf8a5b?ip=1543992427,223.104.4.92&ssig=lhKEXr9W%2Fr&Expires=1543993027&KID=sae,l30zoo1wmz&fn=%E9%BD%90%E8%B1%AB+-+%E6%A9%84%E6%A6%84%E6%A0%91.mp3&se_ip_debug=223.104.4.92&from=1221134";
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread httpDownloader = new Thread(new HttpDownloader(mp3Addr, "songs", "橄榄树.mp3", handler));
                httpDownloader.start();
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_LONG).show();
            super.handleMessage(msg);
        }
    };
}
