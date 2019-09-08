package com.example.whatsappstatusdownloader;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

public class Splash extends AppCompatActivity {

    public static final String SPLASH_BACKGROUND_IMAGE_NAME = "splash_image.jpg";

    ImageView background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background = findViewById(R.id.splash_background_image_view);
        try {
            background.setImageDrawable(Drawable.createFromStream(
                    getAssets().open("images/"+SPLASH_BACKGROUND_IMAGE_NAME),""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Handler().postDelayed(() -> {
            startActivity(new Intent(Splash.this,StatusTab.class));
        },2000);
    }
}
