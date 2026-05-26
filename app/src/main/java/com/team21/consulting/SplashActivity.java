package com.team21.consulting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Fade in animation
        ImageView logo = findViewById(R.id.splash_logo);
        TextView tagline = findViewById(R.id.splash_tagline);
        TextView verse = findViewById(R.id.splash_verse);

        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);

        if (logo != null) logo.startAnimation(fadeIn);

        AlphaAnimation fadeIn2 = new AlphaAnimation(0f, 1f);
        fadeIn2.setDuration(1600);
        fadeIn2.setStartOffset(400);
        fadeIn2.setFillAfter(true);
        if (tagline != null) tagline.startAnimation(fadeIn2);
        if (verse != null) verse.startAnimation(fadeIn2);

        // Navigate to MainActivity after 2.5 seconds
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 2500);
    }
}
