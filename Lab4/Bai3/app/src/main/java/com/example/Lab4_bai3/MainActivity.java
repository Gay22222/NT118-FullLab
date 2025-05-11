package com.example.Lab4_bai3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button buttonFadeIn,  buttonFadeOut, buttonRotate,  buttonZoomIn, buttonZoomOut,
             buttonMove, buttonBlink, buttonSlideUp, buttonBounce, buttonCombine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        ClickEventHandler();

    }

    private void AnhXa(){
        imageView = findViewById(R.id.imageView);
        buttonFadeIn = findViewById(R.id.buttonFadeInXML);
        buttonFadeOut = findViewById(R.id.buttonFadeOutXML);
        buttonRotate = findViewById(R.id.buttonRotateXML);
        buttonZoomIn = findViewById(R.id.buttonZoomInXML);
        buttonZoomOut = findViewById(R.id.buttonZoomOutXML);
        buttonMove = findViewById(R.id.buttonMoveXML);
        buttonBlink = findViewById(R.id.buttonBlinkXML);
        buttonSlideUp = findViewById(R.id.buttonSlideUpXML);
        buttonBounce = findViewById(R.id.buttonBounceXML);
        buttonCombine = findViewById(R.id.buttonCombineXML);
    }

    private void ClickEventHandler(){
        buttonFadeIn.setOnClickListener(v -> applyAnimation(R.anim.anim_fade_in));
        buttonFadeOut.setOnClickListener(v -> applyAnimation(R.anim.anim_fade_out));
        buttonRotate.setOnClickListener(v -> applyAnimation(R.anim.anim_rotate));
        buttonZoomIn.setOnClickListener(v -> applyAnimation(R.anim.anim_zoom_in));
        buttonZoomOut.setOnClickListener(v -> applyAnimation(R.anim.anim_zoom_out));
        buttonMove.setOnClickListener(v -> applyAnimation(R.anim.anim_move));
        buttonBlink.setOnClickListener(v -> applyAnimation(R.anim.anim_blink));
        buttonSlideUp.setOnClickListener(v -> applyAnimation(R.anim.anim_slide_up));
        buttonBounce.setOnClickListener(v -> applyAnimation(R.anim.anim_bounce));
        buttonCombine.setOnClickListener(v -> applyAnimation(R.anim.anim_combine));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    // Hàm áp dụng animation
    private void applyAnimation(int animationResId) {
        Animation animation = AnimationUtils.loadAnimation(this, animationResId);
        imageView.startAnimation(animation);
    }

}
