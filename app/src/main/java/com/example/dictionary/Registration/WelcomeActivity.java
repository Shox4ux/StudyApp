package com.example.dictionary.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dictionary.R;

public class WelcomeActivity extends AppCompatActivity {
    Animation topAnimation,middleAnimation,bottomAnimation;
    View first,second,third,fourth,fifth,sixth,seventh;
    ImageView school;
    TextView fast,education,quite;

    private static int SPLASH_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        middleAnimation = AnimationUtils.loadAnimation(this,R.anim.middle_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        setView();
        setAnimation();
        nextView();




    }

    private void nextView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME);
    }

    private void setAnimation() {
        first.setAnimation(topAnimation);
        second.setAnimation(topAnimation);
        third.setAnimation(topAnimation);
        fourth.setAnimation(topAnimation);
        fifth.setAnimation(topAnimation);
        sixth.setAnimation(topAnimation);
        seventh.setAnimation(topAnimation);

        quite.setAnimation(bottomAnimation);
        school.setAnimation(middleAnimation);
        fast.setAnimation(middleAnimation);
        education.setAnimation(middleAnimation);

    }


    private void setView() {
        first = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        third = findViewById(R.id.third_line);
        fourth = findViewById(R.id.fourth_line);
        fifth = findViewById(R.id.fifth_line);
        sixth = findViewById(R.id.sixth_line);
        seventh = findViewById(R.id.seventh_line);

        quite = findViewById(R.id.quite);
        school = findViewById(R.id.school);
        fast = findViewById(R.id.fast);
        education = findViewById(R.id.education);

    }
}