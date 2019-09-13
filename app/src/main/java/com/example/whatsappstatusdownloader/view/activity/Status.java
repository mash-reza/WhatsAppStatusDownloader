package com.example.whatsappstatusdownloader.view.activity;

import android.content.Intent;
import android.graphics.Color;
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
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.ortiz.touchview.TouchImageView;

import java.io.File;


public class Status extends AppCompatActivity {
    private static final String TAG = "Status";

    int type;
    String path;

    TouchImageView imageView;
    PlayerView playerView;
    ConstraintLayout layout;

    FloatingActionButton mainFab;
    FloatingActionButton deleteFab;
    FloatingActionButton shareFab;
    private Animation fab_open;
    private Animation fab_close;
    private Animation fab_clock;
    private Animation fab_anticlock;

    SimpleExoPlayer player;
    DataSource.Factory mediaDataSourceFactory;

    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Intent intent = getIntent();

        imageView = findViewById(R.id.status_imageView);
        playerView = findViewById(R.id.status_videoView);
        layout = findViewById(R.id.status_layout);

        mainFab = findViewById(R.id.main_fab);
        deleteFab = findViewById(R.id.delete_fab);
        shareFab = findViewById(R.id.share_fab);

        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);


        mainFab.setOnClickListener(v -> {

            if (isOpen) {
                deleteFab.animate().translationY(0);
                shareFab.animate().translationY(0);
                mainFab.startAnimation(fab_anticlock);
                deleteFab.setClickable(false);
                shareFab.setClickable(false);
                isOpen = false;
            } else {
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
            playerView.setVisibility(View.GONE);
            Glide.with(this).load(new File(path)).into(imageView);
        } else if (type == Constants.STATUS_TYPE_VIDEO) {
            imageView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void initializePlayer() {

        player = ExoPlayerFactory.newSimpleInstance(this);

        mediaDataSourceFactory =new DefaultDataSourceFactory(this, Util.getUserAgent(this, "mediaPlayerSample"));

        MediaSource mediaSource =new ExtractorMediaSource.Factory(mediaDataSourceFactory)
                .createMediaSource(Uri.parse(path));


        player.prepare(mediaSource,false,false);
        player.setPlayWhenReady(true);


        playerView.setShutterBackgroundColor(Color.TRANSPARENT);
        playerView.setPlayer(player);
        playerView.requestFocus();

    }

    private void releasePlayer() {
        player.release();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) initializePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23) initializePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) releasePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) releasePlayer();
    }
}
