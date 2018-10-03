package com.example.app04;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        List<View> views = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View view1 = inflater.inflate(R.layout.tab1, null);
        View view2 = inflater.inflate(R.layout.tab2, null);
        View view3 = inflater.inflate(R.layout.tab3, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        viewPager.setAdapter(new HomePagerAdapter(views));
    }

    public class HomePagerAdapter extends PagerAdapter {
        List<View> views;

        public HomePagerAdapter(List<View> views) {
            this.views = views;
        }

        public int getCount() {
            return views.size();
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }
}