package com.example.whatsappstatusdownloader.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.example.whatsappstatusdownloader.R;
public class Tutorial extends AppCompatActivity {

    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(v -> {
            startActivity(new Intent(this, StatusTab.class));
            finish();
        });
    }
}
