package com.example.whatsappstatusdownloader.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
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
    private static final String TAG = "CachedStatus";

    RecyclerView recyclerView;

    public CachedStatus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: CachedFragment");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cached_status, container, false);

        //recyclerView
        recyclerView = view.findViewById(R.id.cached_recycler_view);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
//        CachedAdapter adapter = new CachedAdapter(getActivity(), Repository.getStatus());
        recyclerView.setAdapter(new CachedAdapter(getActivity(), Repository.getStatus()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: CachedFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: CachedFragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: CachedFragment");
    }

}
