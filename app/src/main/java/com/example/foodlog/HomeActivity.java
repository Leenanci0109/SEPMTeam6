package com.example.foodlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button cambtn, histbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cambtn = (Button) findViewById(R.id.cambtn);
        histbtn = (Button) findViewById(R.id.histbtn);

        cambtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
            startActivity(intent);
        });

        histbtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(intent);
        });
    }
}