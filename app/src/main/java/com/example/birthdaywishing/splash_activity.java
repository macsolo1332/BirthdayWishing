package com.example.birthdaywishing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class splash_activity extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    ImageView reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);



        reminder = findViewById(R.id.b_reminder);

        //Set Animations
        reminder.setAnimation(topAnim);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splash_activity.this,Login.class);
            startActivity(intent);
            finish();
        },2000);
    }
}
