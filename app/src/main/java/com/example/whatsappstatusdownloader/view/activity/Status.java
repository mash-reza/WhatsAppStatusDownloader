package com.example.whatsappstatusdownloader.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.util.Constants;

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

        imageView = findViewById(R.id.sta)

        path = intent.getStringExtra("path");
        type = intent.getIntExtra("type",2);
        if (type == Constants.STATUS_TYPE_IMAGE){

        }else if(type == Constants.STATUS_TYPE_VIDEO){


        }

    }
}
