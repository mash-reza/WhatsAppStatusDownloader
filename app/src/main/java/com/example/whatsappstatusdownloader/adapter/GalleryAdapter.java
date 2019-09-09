package com.example.whatsappstatusdownloader.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.model.Status;
import com.example.whatsappstatusdownloader.util.Constants;

import java.io.File;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyHolder> {

    private Context context;
    private List<Status> statusList;

    public GalleryAdapter(Context context, List<Status> statusList) {
        this.context = context;
        this.statusList = statusList;
    }

    @NonNull
    @Override
    public GalleryAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.gallery_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.MyHolder myHolder, int i) {

        if(statusList.get(i).getType() == Constants.STATUS_TYPE_IMAGE){
            myHolder.videoView.setVisibility(View.GONE);
            myHolder.imageView.setVisibility(View.VISIBLE);
            Glide.with(context).load(new File(statusList.get(i).getAddress())).into(myHolder.imageView);


        }else if(statusList.get(i).getType() == Constants.STATUS_TYPE_VIDEO){
            myHolder.videoView.setVisibility(View.VISIBLE);
            myHolder.imageView.setVisibility(View.GONE);



        }



    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        VideoView videoView;
        ImageButton deleteImageButton;
        ImageButton shareImageButton;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gallery_imageView_item);
            videoView = itemView.findViewById(R.id.gallery_videoView_item);
            deleteImageButton = itemView.findViewById(R.id.gallery_delete_imageButton);
            shareImageButton = itemView.findViewById(R.id.gallery_share_imageButton);
        }
    }
}
