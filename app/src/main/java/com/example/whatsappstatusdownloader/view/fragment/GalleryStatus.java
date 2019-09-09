package com.example.whatsappstatusdownloader.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.adapter.GalleryAdapter;
import com.example.whatsappstatusdownloader.util.Repository;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryStatus extends Fragment {

    RecyclerView recyclerView;


    public GalleryStatus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery_status, container, false);
        //recyclerView
        recyclerView = view.findViewById(R.id.gallery_recycler_view);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new GalleryAdapter(getActivity(), Repository.getStatusFromPhone()));
        return view;
    }

}
