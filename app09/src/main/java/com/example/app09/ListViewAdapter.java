package com.example.app09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private ArrayList<HashMap<String, Object>> data;
    Bean bean = null;

    public ListViewAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            bean = new Bean();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.listitem, null);
            bean.imageView = convertView.findViewById(R.id.image);
            bean.songName = convertView.findViewById(R.id.songName);
            bean.singer = convertView.findViewById(R.id.singer);
            bean.detail = convertView.findViewById(R.id.detail);
            convertView.setTag(bean);
        } else {
            bean = (Bean) convertView.getTag();
        }
        bean.imageView.setBackgroundResource((int) data.get(position).get("image"));
        bean.songName.setText((String) data.get(position).get("songName"));
        bean.singer.setText((String) data.get(position).get("singer"));
        bean.detail.setOnClickListener(this);
        bean.detail.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        ((MainActivity) context).click(v);
    }
}
