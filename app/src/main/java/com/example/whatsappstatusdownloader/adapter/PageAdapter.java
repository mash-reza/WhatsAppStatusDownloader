package com.example.whatsappstatusdownloader.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.view.fragment.CachedStatus;
import com.example.whatsappstatusdownloader.view.fragment.GalleryStatus;


public class PageAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "PageAdapter";
    private int numberOfTabs;
    private Context context;
    //private FragmentManager fragmentManager;
//    private CachedStatus cachedStatus = new CachedStatus();
//    private GalleryStatus galleryStatus = new GalleryStatus();

    public PageAdapter(FragmentManager fm, int numberOfTabs, Context context) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        //this.fragmentManager = fm;
        this.context = context;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        //notifyDataSetChanged();
        Log.i(TAG, "getItemPosition() returned: ");
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int i) {
        Log.i(TAG, "getItem: " + i);
        switch (i) {
            case 0:
                //galleryStatus.onDestroyView();
                return new CachedStatus();
            case 1:
                //galleryStatus.onCreate(null);
                return new GalleryStatus();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.tab_item_cached_title);
            case 1:
                return context.getString(R.string.tab_item_gallery_title);
                default:return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

}
