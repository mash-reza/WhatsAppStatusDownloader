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
import android.widget.ImageButton;
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
    ImageButton deleteButton;
    ImageButton shareButton;


    SimpleExoPlayer player;
    DataSource.Factory mediaDataSourceFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Intent intent = getIntent();

        imageView = findViewById(R.id.status_imageView);
        playerView = findViewById(R.id.status_videoView);
        layout = findViewById(R.id.status_layout);
        deleteButton = findViewById(R.id.delete_button);
        shareButton = findViewById(R.id.share_button);

        deleteButton.setOnClickListener(v -> {
            Log.i(TAG, "onCreate: delete button clicked");
        });

//        shareButton.setOnClickListener(v -> {
//            Log.i(TAG, "onCreate: share button clicked");
//            Intent shareIntent = new Intent();
//            shareIntent.setAction(Intent.ACTION_SEND);
//            shareIntent.putExtra(Intent.EXTRA_STREAM, path);
//            shareIntent.setType("image/jpg");
//            startActivity(Intent.createChooser(shareIntent,getResources().getString(R.string.share_intent_title)));
//
//
//        });
        deleteButton.setOnClickListener(v -> {
            File file = new File(path);
            try {
                boolean isDeleted = file.delete();
                Log.i(TAG, "onCreate: deleting file" + isDeleted);
            } catch (Exception e) {
                Log.e(TAG, "onCreate: ", e);
            }
        });


        path = intent.getStringExtra("path");
        type = intent.getIntExtra("type", 2);
        if (type == Constants.STATUS_TYPE_IMAGE) {
            playerView.setVisibility(View.GONE);
            Glide.with(this).load(new File(path)).into(imageView);
            shareButton.setOnClickListener(v -> {
                Log.i(TAG, "onCreate: share button clicked");
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
                shareIntent.setType("image/jpg");
                startActivity(Intent.createChooser(shareIntent,getResources().getString(R.string.share_intent_title)));


            });
        } else if (type == Constants.STATUS_TYPE_VIDEO) {
            imageView.setVisibility(View.GONE);
            shareButton.setOnClickListener(v -> {
                Log.i(TAG, "onCreate: share button clicked");
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
                shareIntent.setType("video/mp4");
                startActivity(Intent.createChooser(shareIntent,getResources().getString(R.string.share_intent_title)));


            });
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
