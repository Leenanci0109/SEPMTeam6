package com.example.foodlog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    android.widget.ProgressBar splashProgress;
    int SPLASH_TIME = 3500; //This is 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playProgress();
        setContentView(R.layout.activity_splash);
        new android.os.Handler(getMainLooper()).postDelayed(() -> {
            android.content.Intent mySuperIntent = new android.content.Intent(SplashActivity.this, LoginActivity.class);
            startActivity(mySuperIntent);
            finish();

        }, SPLASH_TIME);
    }
    private void playProgress() {
        android.animation.ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(4500)
                .start();
    }
}