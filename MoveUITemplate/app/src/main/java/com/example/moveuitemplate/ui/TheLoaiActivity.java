package com.example.moveuitemplate.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.moveuitemplate.R;
import com.example.moveuitemplate.adapters.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TheLoaiActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SectionsPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);//bar
        viewPager = (ViewPager) findViewById(R.id.viewPage_id); //bar
        adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new Frag1(), "ACTION");
        adapter.AddFragment(new Frag2(), "LOVE");


        viewPager.setAdapter(adapter);//bar
        tabLayout.setupWithViewPager(viewPager);//bar
    }
}
