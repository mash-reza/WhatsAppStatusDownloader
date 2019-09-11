package com.example.whatsappstatusdownloader.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
        Line manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new CachedAdapter(getActivity(), Repository.getStatus(),view.findViewById(R.id.cached_recycler_view)));
//        recyclerView.setDrawingCacheEnabled(true);
//        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        return view;
    }

}
