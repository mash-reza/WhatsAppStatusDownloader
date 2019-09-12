package com.example.whatsappstatusdownloader.view.activity;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.util.Constants;
import com.ortiz.touchview.TouchImageView;

import java.io.File;


public class Status extends AppCompatActivity {
    private static final String TAG = "Status";

    int type;
    String path;

    TouchImageView imageView;
    VideoView videoView;
    ConstraintLayout layout;

    FloatingActionButton mainFab;
    FloatingActionButton deleteFab;
    FloatingActionButton shareFab;
    private Animation fab_open;
    private Animation fab_close;
    private Animation fab_clock;
    private Animation fab_anticlock;

    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Intent intent = getIntent();

        imageView = findViewById(R.id.status_imageView);
        videoView = findViewById(R.id.status_videoView);
        layout = findViewById(R.id.status_layout);

        mainFab = findViewById(R.id.main_fab);
        deleteFab = findViewById(R.id.delete_fab);
        shareFab = findViewById(R.id.share_fab);

        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);


        mainFab.setOnClickListener(v -> {

            if(isOpen){
                deleteFab.animate().translationY(0);
                shareFab.animate().translationY(0);
                mainFab.startAnimation(fab_anticlock);
                deleteFab.setClickable(false);
                shareFab.setClickable(false);
                isOpen = false;
            }else {
                deleteFab.animate().translationY(-150);
                shareFab.animate().translationY(-300);
                mainFab.startAnimation(fab_clock);
                deleteFab.setClickable(true);
                shareFab.setClickable(true);
                isOpen = true;
            }
        });

        path = intent.getStringExtra("path");
        type = intent.getIntExtra("type", 2);
        if (type == Constants.STATUS_TYPE_IMAGE) {
            videoView.setVisibility(View.GONE);
            Glide.with(this).load(new File(path)).into(imageView);
        } else if (type == Constants.STATUS_TYPE_VIDEO) {
            imageView.setVisibility(View.GONE);
            videoView.setVideoURI(Uri.parse(path));
            videoView.start();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        videoView.stopPlayback();
        finish();
    }
}
