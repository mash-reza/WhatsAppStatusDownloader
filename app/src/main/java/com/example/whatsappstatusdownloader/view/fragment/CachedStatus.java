package com.example.whatsappstatusdownloader.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.adapter.CachedAdapter;
import com.example.whatsappstatusdownloader.util.Repository;


/**
 * A simple {@link Fragment} subclass.
 */
public class CachedStatus extends Fragment {

    RecyclerView recyclerView;

    public CachedStatus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cached_status, container, false);

        //recyclerView
        recyclerView = view.findViewById(R.id.cached_recycler_view);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new CachedAdapter(getActivity(), Repository.getStatus()));


        return view;
    }

}
