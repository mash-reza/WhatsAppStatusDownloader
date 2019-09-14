package com.example.whatsappstatusdownloader.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.adapter.GalleryAdapter;
import com.example.whatsappstatusdownloader.util.Constants;
import com.example.whatsappstatusdownloader.util.Repository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryStatus extends Fragment {
    private static final String TAG = "GalleryStatus";

    RecyclerView recyclerView;

    public GalleryStatus() {
        // Required empty public constructor
    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);

        Log.i(TAG, "onCreateView: GalleryFragment");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery_status, container, false);
        super.onViewCreated(view, savedInstanceState);
        //recyclerView
        recyclerView = view.findViewById(R.id.gallery_recycler_view);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new GalleryAdapter(getActivity(),Repository.getStatusFromPhone()));
        return view;
    }

    
    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: GalleryFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: GalleryFragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: GalleryFragment");
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(String event){
        Log.i(TAG, "onMessageEvent: event "+event);
        if(event.equals(Constants.REFRESH_GALLERY_EVENT_MESSAGE))
            recyclerView.setAdapter(new GalleryAdapter(getActivity(),Repository.getStatusFromPhone()));
    }
}
