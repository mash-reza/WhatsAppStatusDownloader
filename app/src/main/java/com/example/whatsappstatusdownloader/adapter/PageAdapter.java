package com.example.whatsappstatusdownloader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.whatsappstatusdownloader.view.fragment.CachedStatus;
import com.example.whatsappstatusdownloader.view.fragment.GalleryStatus;

public class PageAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;

    public PageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new CachedStatus();
            case 1:
                return new GalleryStatus();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
