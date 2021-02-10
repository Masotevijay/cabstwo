package com.example.cabstwo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cabstwo.R;

public class Splash extends AppCompatActivity
{
    View SplashVieww;
    Handler handler;
    TextView logoText;
    ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash);
        SplashVieww=findViewById(R.id.logo_text);

        //....Animation Code......
        logoText=findViewById(R.id.logo_text);
        logoImage=findViewById(R.id.imag_background);

        AnimatorSet animatorSet=new AnimatorSet();
        ValueAnimator textAnimator= ValueAnimator.ofFloat(1,1.1f);
        textAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value= (Float) animation.getAnimatedValue();

                logoText.setScaleX(value);
                logoText.setScaleY(value);
            }
        });
        ValueAnimator imageAnimator= ValueAnimator.ofFloat(1,1.2f);
        imageAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value= (Float) animation.getAnimatedValue();

                logoImage.setScaleX(value);
                logoImage.setScaleY(value);
            }
        });

        animatorSet.play(textAnimator).with(imageAnimator);
        animatorSet.setDuration(3000);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
//.......End...................
        SplashVieww.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        },3000);
    }
    @Override
    public void onBackPressed()

    {
        super.onBackPressed();
    }
}