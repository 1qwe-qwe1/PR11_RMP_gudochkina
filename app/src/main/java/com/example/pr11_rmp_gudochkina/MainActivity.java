package com.example.pr11_rmp_gudochkina;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ImageView sunImageView = findViewById(R.id.sun);
        Animation sunRiseAnimation = AnimationUtils.loadAnimation(this, R.anim.sun_rise);
        sunImageView.startAnimation(sunRiseAnimation);

        ImageView clockImageView = findViewById(R.id.clock);
        Animation clockTurnAnimation = AnimationUtils.loadAnimation(this, R.anim.clock_turn);
        clockImageView.startAnimation(clockTurnAnimation);

        ImageView hourImageView = findViewById(R.id.hour_hand);
        Animation hourTurnAnimation = AnimationUtils.loadAnimation(this, R.anim.hour_turn);
        hourImageView.startAnimation(hourTurnAnimation);
        animateStars();

        brightenSkyBackground();
    }
    private void animateStars() {
        int[] starIds = {R.id.star1, R.id.star2, R.id.star3, R.id.star4, R.id.star5};

        for (int starId : starIds) {
            ImageView star = findViewById(starId);
            Animation starAnimation = AnimationUtils.loadAnimation(this, R.anim.stars_disappear);
            starAnimation.setStartOffset((int) (Math.random() * 1000));
            star.startAnimation(starAnimation);
        }
    }

    private void brightenSkyBackground() {
        ImageView skyImageView = findViewById(R.id.sky);

        LayerDrawable layerDrawable = (LayerDrawable) skyImageView.getDrawable();
        if (layerDrawable != null) {
            Drawable nightSky = layerDrawable.getDrawable(1);

            ObjectAnimator fadeOutAnimator = ObjectAnimator.ofInt(
                    nightSky,
                    "alpha",
                    255,
                    0
            );
            fadeOutAnimator.setDuration(5000);
            fadeOutAnimator.start();
        }

    }
}