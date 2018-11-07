package com.example.app07;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private FrameLayout homeContent;
    private RadioGroup radioGroup;
    private RadioButton rbHome, rbFind, rbSearch, rbProfile;
    static final int NUM_ITEMS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void initView() {
        homeContent = findViewById(R.id.homeContent);
        radioGroup = findViewById(R.id.radioGroup);
        rbHome = findViewById(R.id.rbHome);
        rbFind = findViewById(R.id.rbFind);
        rbSearch = findViewById(R.id.rbSearch);
        rbProfile = findViewById(R.id.rbProfile);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                switch (checkedId) {
                    case R.id.rbHome:
                        index = 0;
                        break;
                    case R.id.rbFind:
                        index = 1;
                        break;
                    case R.id.rbSearch:
                        index = 2;
                        break;
                    case R.id.rbProfile:
                        index = 3;
                        break;
                }

                Fragment fragment = (Fragment) adapter.instantiateItem(homeContent, index);
                adapter.setPrimaryItem(homeContent, 0, fragment);
                adapter.finishUpdate(homeContent);
            }
        });
    }

    FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                case 0:
                    fragment = new HomeFagment();
                    break;
                case 1:
                    fragment = new FindFragment();
                    break;
                case 2:
                    fragment = new SearchFragment();
                    break;
                case 3:
                    fragment = new ProfileFragment();
                    break;
                default:
                    fragment = new HomeFagment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        radioGroup.check(R.id.rbHome);
    }
}
