package com.example.app09;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Intent intent = new Intent();
    ArrayList<HashMap<String, Object>> arrayList;
    int pos = -1;
    boolean play_flag = false;
    ListViewAdapter listViewAdapter;
    ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        ListView listView = findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter(this, getData());
        listView.setAdapter(listViewAdapter);
        AdapterView.OnItemClickListener listViewListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (play_flag) {
                    stopPlayer(pos);
                    if (pos != position) {
                        pos = position;
                        startPlayer(pos);
                    }
                } else {
                    pos = position;
                    startPlayer(pos);
                }
            }
        };
        listView.setOnItemClickListener(listViewListener);
    }

    private ArrayList<HashMap<String, Object>> getData() {
        arrayList = new ArrayList<>();
        HashMap<String, Object> hashMap1 = new HashMap<>();
        HashMap<String, Object> hashMap2 = new HashMap<>();
        HashMap<String, Object> hashMap3 = new HashMap<>();
        hashMap1.put("image", R.mipmap.play);
        hashMap1.put("songName", "When You Believe");
        hashMap1.put("singer", "Whitney Houston/Mariah Carey");
        arrayList.add(hashMap1);
        hashMap2.put("image", R.mipmap.play);
        hashMap2.put("songName", "What A Wonderful World");
        hashMap2.put("singer", "Bob Thiele");
        arrayList.add(hashMap2);
        hashMap3.put("image", R.mipmap.play);
        hashMap3.put("songName", "Starry Starry Night");
        hashMap3.put("singer", "Don McLean");
        arrayList.add(hashMap3);
        return arrayList;
    }

    private void startPlayer(int pos) {
        arrayList.get(pos).put("image", R.mipmap.stop);
        listViewAdapter.notifyDataSetChanged();
        intent.setAction("com.example.app09.MusicService");
        String songName = (String) arrayList.get(pos).get("songName");
        intent.putExtra("songName", songName + ".mp3");
        intent.setPackage(getPackageName());
        startService(intent);
        imageView.setVisibility(View.VISIBLE);
        play_flag = true;
    }

    private void stopPlayer(int pos) {
        arrayList.get(pos).put("image", R.mipmap.play);
        listViewAdapter.notifyDataSetChanged();
        stopService(intent);
        ImageView button = findViewById(R.id.stopImage);
        button.setVisibility(View.INVISIBLE);
        play_flag = false;
    }

    public void click(View v) {
        new AlertDialog.Builder(this)
                .setTitle((String) arrayList.get((Integer) v.getTag()).get("songName"))
                .setMessage("演唱者:" + (String) arrayList.get((Integer) v.getTag()).get("singer"))
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
}
