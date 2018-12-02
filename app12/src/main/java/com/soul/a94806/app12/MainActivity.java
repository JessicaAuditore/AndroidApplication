package com.soul.a94806.app12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.soul.a94806.app12.utils.HttpDownloader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String mp3Addr = "http://file3.data.weipan.cn/57277001/96e7eaa76fa61c6b617543dade3aad55d2a32c8c?ip=1543391848,223.104.4.46&ssig=Xvky6TQr4r&Expires=1543392448&KID=sae,l30zoo1wmz&fn=%E6%A9%84%E6%A6%84%E6%A0%91.mp3&se_ip_debug=223.104.4.46&from=1221134";
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread httpDownloader = new Thread(new HttpDownloader(mp3Addr, "songs", "橄榄树.mp3"));
                httpDownloader.start();
            }
        });
    }
}
