package com.example.gardenry;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    ImageView img,logo,splashimg;
    TextView tv;
    LottieAnimationView lottieAnimationView;
    Handler handler;
    FirebaseUser user;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        auth=auth.getInstance();
        user=auth.getCurrentUser();
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
                if(user!=null){
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

                }
                else{
                    Intent intent=new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);

                }
                finish();

            }
        }, 4000);



    }
}