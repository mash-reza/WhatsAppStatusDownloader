package com.example.whatsappstatusdownloader.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.adapter.PageAdapter;

public class StatusTab extends AppCompatActivity {
    private static final String TAG = "StatusTab";



    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tabItem1;
    TabItem tabItem2;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_tab);
        initUi();
    }

    private void initUi(){
        //init ui
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        tabItem1 = findViewById(R.id.cachedTabItem);
        tabItem2 = findViewById(R.id.galleryTabItem);
        viewPager = findViewById(R.id.viewPager);


        //setting viewpager
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount(),
                this
        ));
        tabLayout.setupWithViewPager(viewPager);
    }
}
