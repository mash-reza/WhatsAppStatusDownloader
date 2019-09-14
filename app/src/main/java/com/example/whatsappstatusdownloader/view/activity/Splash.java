package com.example.whatsappstatusdownloader.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.whatsappstatusdownloader.R;
import com.example.whatsappstatusdownloader.util.Constants;

import java.io.IOException;

public class Splash extends AppCompatActivity {

    private static final String TAG = "Splash";
    SharedPreferences preferences;


    ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background = findViewById(R.id.splash_background_image_view);
        try {
            background.setImageDrawable(Drawable.createFromStream(
                    getAssets().open("images/" + Constants.SPLASH_BACKGROUND_IMAGE_NAME), ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        preferences = getSharedPreferences(Constants.GRANTED_PERMISSION_PREFERENCE, MODE_PRIVATE);

        if (preferences.getBoolean(Constants.GRANTED_PERMISSION_PREFERENCE_KEY, false)) {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(Splash.this, StatusTab.class));
                finish();
            }, 2000);

        } else checkStoragePermission();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "onRequestPermissionsResult: " + grantResults[0]);
            new Handler().postDelayed(() -> {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(Constants.GRANTED_PERMISSION_PREFERENCE_KEY, true).commit();
                startActivity(new Intent(Splash.this, StatusTab.class));
                finish();
            }, 2000);
        } else finish();
    }

    public void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
    }
}
