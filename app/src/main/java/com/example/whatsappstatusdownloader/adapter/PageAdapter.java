package com.example.whatsappstatusdownloader.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.whatsappstatusdownloader.view.fragment.CachedStatus;
import com.example.whatsappstatusdownloader.view.fragment.GalleryStatus;

public class PageAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "PageAdapter";
    private int numberOfTabs;
    //private FragmentManager fragmentManager;
    private CachedStatus cachedStatus = new CachedStatus();
    private GalleryStatus galleryStatus = new GalleryStatus();

    public PageAdapter(FragmentManager fm, int numberOfTabs, Context context) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        //this.fragmentManager = fm;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        //notifyDataSetChanged();
        Log.d(TAG, "getItemPosition() returned: ");
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int i) {
        Log.d(TAG, "getItem: " + i);
        switch (i) {
            case 0:
                return cachedStatus;
            case 1:
                return galleryStatus;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    public  void update(){

    }
}
