package com.example.whatsappstatusdownloader.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.model.Status;
import java.util.List;

public class CachedAdapter extends RecyclerView.Adapter<CachedAdapter.MyHolder> {
    private static final String TAG = "CachedAdapter";

    private Context context;
    private List<Status> statuses;

    public CachedAdapter(Context context, List<Status> statuses) {
        this.context = context;
        this.statuses = statuses;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.cached_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        if (statuses.get(i).getType() == Status.TYPE_IMAGE) {
            myHolder.videoView.setVisibility(View.GONE);
            myHolder.imageView.setVisibility(View.VISIBLE);
            String filePath = Environment.getExternalStorageDirectory().getAbsoluteFile() + statuses.get(i).getAddress();

            Glide.with(context).load(filePath).into(myHolder.imageView);
        } else {
            myHolder.imageView.setVisibility(View.GONE);
            myHolder.videoView.setVisibility(View.VISIBLE);

//            File file = new File("/sdcard/WhatsApp/Media/WhatsApp Video/Private/vid01.mp4");
            String s = Environment.getExternalStorageDirectory().getAbsolutePath()+statuses.get(i).getAddress();
            Uri uri = Uri.parse(s);
            myHolder.videoView.setVideoURI(uri);
            myHolder.videoView.setOnClickListener(v -> {
                if (myHolder.videoView.isPlaying())
                    myHolder.videoView.pause();
                else
                    myHolder.videoView.start();
            });
        }
        myHolder.imageButton.setOnClickListener(v -> {
            Toast.makeText(context, "cliked", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, com.example.whatsappstatusdownloader.view.activity.Status.class));
        });
    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        VideoView videoView;
        ImageButton imageButton;

        MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cached_imageView_item);
            videoView = itemView.findViewById(R.id.cached_videoView_item);
            imageButton = itemView.findViewById(R.id.cached_download_imageButton);
        }
    }
}
