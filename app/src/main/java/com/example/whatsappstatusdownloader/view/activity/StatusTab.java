package com.example.whatsappstatusdownloader.view.activity;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.adapter.PageAdapter;

public class StatusTab extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tabItem1;
    TabItem tabItem2;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_tab);

        //init ui
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        tabItem1 = findViewById(R.id.cachedTabItem);
        tabItem2 = findViewById(R.id.galleryTabItem);
        viewPager = findViewById(R.id.viewPager);


        //setting viewpager
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



    }
}
