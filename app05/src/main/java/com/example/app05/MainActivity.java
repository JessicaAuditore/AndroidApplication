package com.example.app05;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 94806
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup radioGroup;
    private RadioButton downlist;
    private RadioButton downloadbackground;
    private ProgressBar progressbar;
    private ListView listview;
    private ArrayList<HashMap<String, String>> list;
    private NotificationManager nm;
    private Notification notify;
    final int NOTIFICATION_ID = 0x123;
    //i为进度条进度
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        downlist = findViewById(R.id.downlist);
        downloadbackground = findViewById(R.id.downloadbackground);
        findViewById(R.id.download).setOnClickListener(this);
        progressbar = findViewById(R.id.progressbar);
        listview = findViewById(R.id.listview);

        //初始化ListView
        initListView();
        //初始化Notification
        initNotification();
    }

    private void initListView() {
        list = new ArrayList<>();
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();
        HashMap<String, String> map3 = new HashMap<>();
        map1.put("name", "When you leave");
        map1.put("size", "0.7M");
        map2.put("name", "Tomorrow");
        map2.put("size", "1.3M");
        map3.put("name", "as long as you love me");
        map3.put("size", "0.9M");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        SimpleAdapter listAdapter = new SimpleAdapter(this, list,
                R.layout.name_size, new String[]{"name", "size"}, new int[]{R.id.name, R.id.size});
        listview.setAdapter(listAdapter);
        AdapterView.OnItemClickListener listViewListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("App05", "ID:" + view);
                Log.i("App05",
                        "position:" + list.get(position).get("name") + ":" + list.get(position).get("size"));
            }
        };
        listview.setOnItemClickListener(listViewListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initNotification() {
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("CHANNEL ID", "CHANNEL NAME", NotificationManager.IMPORTANCE_HIGH);
        nm.createNotificationChannel(channel);
        //创建通知
        notify = new NotificationCompat.Builder(this, "CHANNEL ID")
                //设置打开通知，该通知自动消失
                .setAutoCancel(true)
                //设置显示在状态栏的通知提示消息
                .setContentText(getResources().getString(R.string.finish))
                //设置通知内容的标题
                .setContentTitle(getResources().getString(R.string.newMsg))
                //设置通知图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置发送通知时系统默认的声音，默认的LED灯，默认的震动
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setWhen(System.currentTimeMillis()).build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download:
                if (i == 0) {
                    //首次点击时设置ListView控件不可见
                    listview.setVisibility(View.GONE);
                    //设置进度条处于可见的状态
                    progressbar.setVisibility(View.VISIBLE);
                    //设置进度条最大进度为100
                    progressbar.setMax(100);
                }
                if (i < progressbar.getMax()) {
                    //设置主进度条的当前值
                    progressbar.setProgress(i);
                    //设置第二进度条的当前值
                    progressbar.setSecondaryProgress(i + 10);
                    //每点击一次进度i+10
                    i += 10;
                } else {
                    //进度结束
                    progressbar.setVisibility(View.GONE);
                    i = 0;
                    if (downlist.isChecked()) {
                        listview.setVisibility(View.VISIBLE);
                    } else {
                        nm.notify(NOTIFICATION_ID, notify);
                    }
                }
                break;
        }
    }
}
