package com.example.whatsappstatusdownloader.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
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
    private RecyclerView recyclerView;

    public GalleryAdapter(Context context, List<Status> statusList, RecyclerView recyclerView) {
        this.context = context;
        this.statusList = statusList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public GalleryAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.gallery_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        switch (getItemViewType(i)) {
            case Constants.STATUS_TYPE_IMAGE:
                myHolder.foreground.setVisibility(View.GONE);
                myHolder.playIcon.setVisibility(View.GONE);
                Glide.with(context).load(new File(statusList.get(i).getAddress())).into(myHolder.image);
                myHolder.image.setOnClickListener(v -> {
                    Intent intent = new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class);
                    intent.putExtra("path", statusList.get(i).getAddress());
                    intent.putExtra("type", Constants.STATUS_TYPE_IMAGE);
                    context.startActivity(intent);
                });
                break;
            case Constants.STATUS_TYPE_VIDEO:
                myHolder.image.setVisibility(View.GONE);
                myHolder.foreground.setOnClickListener(v -> {
                    Intent intent2 = new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class);
                    intent2.putExtra("path", statusList.get(i).getAddress());
                    intent2.putExtra("type", Constants.STATUS_TYPE_VIDEO);
                    context.startActivity(intent2);
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return statusList.get(position).getType();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView foreground;
        ImageView playIcon;
        ImageButton deleteImageButton;
        ImageButton shareImageButton;

        MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.gallery_imageView_item);
            foreground = itemView.findViewById(R.id.gallery_item_foreground);
            playIcon = itemView.findViewById(R.id.gallery_play_icon_imageView);
            deleteImageButton = itemView.findViewById(R.id.gallery_delete_imageButton);
            shareImageButton = itemView.findViewById(R.id.gallery_share_imageButton);
        }
    }
}
