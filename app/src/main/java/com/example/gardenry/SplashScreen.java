package com.example.gardenry;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
ImageView img,logo,splashimg;
TextView tv;
LottieAnimationView lottieAnimationView;
Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        img = findViewById(R.id.img);
        logo=findViewById(R.id.logo);
        tv=findViewById(R.id.textView);
        lottieAnimationView=findViewById(R.id.animationView);
        img.animate().translationY(-1890).setDuration(1000).setStartDelay(3000);
        logo.animate().translationY(-1890).setDuration(1000).setStartDelay(3000);
        lottieAnimationView.animate().translationY(-1890).setDuration(1000).setStartDelay(3000);
        tv.animate().translationY(-1890).setDuration(1000).setStartDelay(3000);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        }, 4000);



    }
}