package com.example.whatsappstatusdownloader.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.adapter.PageAdapter;
import com.example.whatsappstatusdownloader.view.fragment.CachedStatus;
import com.example.whatsappstatusdownloader.view.fragment.GalleryStatus;

public class StatusTab extends AppCompatActivity {
    private static final String TAG = "StatusTab";
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private CachedStatus cachedStatus = new CachedStatus();
    private GalleryStatus galleryStatus = new GalleryStatus();

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
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), this,cachedStatus,galleryStatus));
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//                Log.i(TAG, "onPageScrolled: "+i);
//                switch (i){
//                    case 0:
//                        Log.i(TAG, "onPageScrolled:0 i"+i);
//                        Log.i(TAG, "onPageScrolled:0 v"+v);
//                        Log.i(TAG, "onPageScrolled:0 i1"+i1);
////                        viewPager.setCurrentItem(1);
//                        break;
//                    case 1:
//                        Log.i(TAG, "onPageScrolled:1 "+i);
//                        Log.i(TAG, "onPageScrolled:1 "+v);
//                        Log.i(TAG, "onPageScrolled:1 "+i1);
////                        viewPager.setCurrentItem(0);
//                        break;
//                }
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                switch (i) {
//                    case 0:
//                        Log.d(TAG, "onPageSelected: " + i);
//                        viewPager.setCurrentItem(0);
//                        break;
//                    case 1:
//                        Log.d(TAG, "onPageSelected: " + i);
//                        viewPager.setCurrentItem(1);
//                        break;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tabLayout.getSelectedTabPosition()) {
//                    case 0:
//                        Log.i(TAG, "onTabSelected: 1");
//                        viewPager.setCurrentItem(0);
//                        break;
//                    case 1:
//                        Log.i(TAG, "onTabSelected: 2");
//                        viewPager.setCurrentItem(1);
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        checkStoragePermission();

    }


    public void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
