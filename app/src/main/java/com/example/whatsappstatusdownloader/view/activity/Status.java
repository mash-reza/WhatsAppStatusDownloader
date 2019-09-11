package com.example.whatsappstatusdownloader.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.util.Constants;

import java.io.File;

public class Status extends AppCompatActivity {
    private static final String TAG = "Status";

    int type;
    String path;

    ImageView imageView;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Intent intent = getIntent();

        imageView = findViewById(R.id.status_imageView);
        videoView = findViewById(R.id.status_videoView);

        path = intent.getStringExtra("path");
        type = intent.getIntExtra("type", 2);
        if (type == Constants.STATUS_TYPE_IMAGE) {
            videoView.setVisibility(View.GONE);
            Glide.with(this).load(new File(path)).into(imageView);
        } else if (type == Constants.STATUS_TYPE_VIDEO) {
            imageView.setVisibility(View.GONE);
            videoView.setVideoURI(Uri.parse(path));
            MediaController mc = new MediaController(this);
            mc.setAnchorView(videoView);
            mc.setMediaPlayer(videoView);
            videoView.setMediaController(mc);
        }

    }
}
