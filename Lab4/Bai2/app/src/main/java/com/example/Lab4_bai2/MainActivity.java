package com.example.Lab4_bai2;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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
        buttonFadeIn = findViewById(R.id.buttonFadeInCode);
        buttonFadeOut = findViewById(R.id.buttonFadeOutCode);
        buttonRotate = findViewById(R.id.buttonRotateCode);
        buttonZoomIn = findViewById(R.id.buttonZoomInCode);
        buttonZoomOut = findViewById(R.id.buttonZoomOutCode);
        buttonMove = findViewById(R.id.buttonMoveCode);
        buttonBlink = findViewById(R.id.buttonBlinkCode);
        buttonSlideUp = findViewById(R.id.buttonSlideUpCode);
        buttonBounce = findViewById(R.id.buttonBounceCode);
        buttonCombine = findViewById(R.id.buttonCombineCode);
    }

    private void ClickEventHandler(){
        buttonFadeIn.setOnClickListener(v -> imageView.startAnimation(createFadeInAnimation()));
        buttonFadeOut.setOnClickListener(v -> imageView.startAnimation(createFadeOutAnimation()));
        buttonRotate.setOnClickListener(v -> imageView.startAnimation(createRotateAnimation()));
        buttonZoomIn.setOnClickListener(v -> imageView.startAnimation(createZoomInAnimation()));
        buttonZoomOut.setOnClickListener(v -> imageView.startAnimation(createZoomOutAnimation()));
        buttonMove.setOnClickListener(v -> imageView.startAnimation(createMoveAnimation()));
        buttonBlink.setOnClickListener(v -> imageView.startAnimation(createBlinkAnimation()));
        buttonSlideUp.setOnClickListener(v -> imageView.startAnimation(createSlideUpAnimation()));
        buttonBounce.setOnClickListener(v -> imageView.startAnimation(createBounceAnimation()));
        buttonCombine.setOnClickListener(v -> imageView.startAnimation(createCombineAnimation()));
    }
    
    public Animation createFadeInAnimation() {
        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f); // Từ trong suốt đến hiện rõ
        fadeIn.setDuration(1000); // 1 giây
        return fadeIn;
    }

    public Animation createFadeOutAnimation() {
        AlphaAnimation fadeOut = new AlphaAnimation(1f, 0f); // Từ hiện rõ đến trong suốt
        fadeOut.setDuration(1000); // 1 giây
        return fadeOut;
    }

    public Animation createRotateAnimation() {
        RotateAnimation rotate = new RotateAnimation(
                0f, 360f, // Góc quay từ 0 đến 360 độ
                Animation.RELATIVE_TO_SELF, 0.5f, // Xoay quanh tâm X
                Animation.RELATIVE_TO_SELF, 0.5f  // Xoay quanh tâm Y
        );
        rotate.setDuration(5000); // 5 giây
        return rotate;
    }

    public Animation createZoomInAnimation() {
        ScaleAnimation zoomIn = new ScaleAnimation(
                1f, 2f, // Từ kích thước gốc đến gấp đôi
                1f, 2f,
                Animation.RELATIVE_TO_SELF, 0.5f, // Tâm X
                Animation.RELATIVE_TO_SELF, 0.5f  // Tâm Y
        );
        zoomIn.setDuration(1000); // 1 giây
        return zoomIn;
    }

    public Animation createZoomOutAnimation() {
        ScaleAnimation zoomOut = new ScaleAnimation(
                2f, 1f, // Từ gấp đôi kích thước về kích thước gốc
                2f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        zoomOut.setDuration(1000); // 1 giây
        return zoomOut;
    }

    public Animation createMoveAnimation() {
        TranslateAnimation move = new TranslateAnimation(
                0, 200, // Dịch chuyển từ vị trí ban đầu sang phải 200px
                0, 0    // Không thay đổi trên trục Y
        );
        move.setDuration(1000); // 1 giây
        return move;
    }

    public Animation createBlinkAnimation() {
        AlphaAnimation blink = new AlphaAnimation(1f, 0f); // Hiện rõ rồi biến mất
        blink.setDuration(500); // 0.5 giây
        blink.setRepeatMode(Animation.REVERSE); // Lặp lại theo chiều ngược
        blink.setRepeatCount(3); // Lặp vô hạn
        return blink;
    }

    public Animation createSlideUpAnimation() {
        TranslateAnimation slideUp = new TranslateAnimation(
                0, 0, // Không thay đổi trên trục X
                200, 0 // Dịch chuyển từ dưới lên trên (200px)
        );
        slideUp.setDuration(1000); // 1 giây
        return slideUp;
    }

    public Animation createBounceAnimation() {
        // Tạo ScaleAnimation
        ScaleAnimation bounce = new ScaleAnimation(
                0.8f, 1.0f, // Tỷ lệ X từ 0.8 đến 1.0
                0.8f, 1.0f, // Tỷ lệ Y từ 0.8 đến 1.0
                Animation.RELATIVE_TO_SELF, 0.5f, // Điểm neo trục X (50% của chính nó)
                Animation.RELATIVE_TO_SELF, 0.5f  // Điểm neo trục Y (50% của chính nó)
        );

        // Cài đặt thời gian thực thi
        bounce.setDuration(1000); // Thời lượng 1 giây

        // Giữ trạng thái cuối cùng sau khi animation kết thúc
        bounce.setFillAfter(true);

        // Sử dụng interpolator bounce
        bounce.setInterpolator(new android.view.animation.BounceInterpolator());

        return bounce;
    }


    public Animation createCombineAnimation() {
        AnimationSet combine = new AnimationSet(true);

        // Thêm các animation
        combine.addAnimation(createFadeInAnimation());
        combine.addAnimation(createRotateAnimation());
        combine.addAnimation(createZoomInAnimation());

        combine.setDuration(2000); // 2 giây
        return combine;
    }


}
