package com.example.whatsappstatusdownloader.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        switch (getItemViewType(i)) {
            case Constants.STATUS_TYPE_IMAGE:
                myHolder.playIcon.setVisibility(View.GONE);
                Glide.with(context).load(statusList.get(i).getAddress()).into(myHolder.image);
                myHolder.image.setOnClickListener(v -> {
                    Intent intent = new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class);
                    intent.putExtra("path", statusList.get(i).getAddress());
                    intent.putExtra("type", Constants.STATUS_TYPE_IMAGE);
                    context.startActivity(intent);
                });
                break;
            case Constants.STATUS_TYPE_VIDEO:
                Glide.with(context).load(Uri.fromFile(new File(statusList.get(i).getAddress()))).into(myHolder.image);
                myHolder.image.setOnClickListener(v -> {
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
        ImageView playIcon;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.gallery_imageView_item);
            playIcon = itemView.findViewById(R.id.gallery_play_icon_imageView);
        }
    }
}
