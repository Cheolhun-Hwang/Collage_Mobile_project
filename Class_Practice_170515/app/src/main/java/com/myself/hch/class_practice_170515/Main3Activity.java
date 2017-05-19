package com.myself.hch.class_practice_170515;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

//여러 객체에 동시에 애니메이션 적용하기
public class Main3Activity extends AppCompatActivity {
    ImageView swingImage;
    ImageView waterImage;
    ImageView waterImage_slow;
    ImageView waterImage_fast;
    ImageView skyImage;

    Animation shakeAnimation;
    Animation dropAnimation;
    Animation dropAnimation_slow;
    Animation dropAnimation_fast;
    Animation flowAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // swing 이미지에 애니메이션 객체 설정
        swingImage = (ImageView) findViewById(R.id.swingImage);
        //액션정보 로딩
        // (1) ==========
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        //이미지에 애니메이션 설정
        // (2) ==========
        swingImage.setAnimation(shakeAnimation);

        // water 이미지에 애니메이션 객체 설정
        waterImage = (ImageView) findViewById(R.id.waterImage);
        waterImage_slow = (ImageView) findViewById(R.id.waterImage_slow);
        waterImage_fast = (ImageView) findViewById(R.id.waterImage_fast);
        //액션정보 로딩
        // (3) ==========
        dropAnimation = AnimationUtils.loadAnimation(this, R.anim.drop);
        dropAnimation_slow = AnimationUtils.loadAnimation(this, R.anim.drop_slow);
        dropAnimation_fast = AnimationUtils.loadAnimation(this, R.anim.drop_fast);
        //이미지에 애니메이션 설정
        // (4) ==========
        waterImage.setAnimation(dropAnimation);
        waterImage_fast.setAnimation(dropAnimation_fast);
        waterImage_slow.setAnimation(dropAnimation_slow);
        // sky 이미지에 애니메이션 객체 설정
        skyImage = (ImageView) findViewById(R.id.skyImage);
        //액션정보 로딩
        // (5) ==========
        flowAnimation = AnimationUtils.loadAnimation(this, R.anim.flow);
        //이미지에 애니메이션 설정
        // (6) ==========
        skyImage.setAnimation(flowAnimation);
        Resources res = getResources();//res 폴더 관리자(Resources 객체) 얻어오기

        //이미지 리소스를 디코딩하여 비트맵 이미지로 변환(리소스, 리소스id)

        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.sky_background);
        //이미지의 크기를 저장
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        //이미지 크기를 이미지 뷰의 크기로 지정
        ViewGroup.LayoutParams params = skyImage.getLayoutParams();
        params.width = bitmapWidth;
        params.height = bitmapHeight;

        //비트맵 이미지를 이미지 뷰에 보일 비트맵 이미지로 설정
        // (7) ==========
        skyImage.setImageBitmap(bitmap);

        //애니메이션의 시작과 종료 상태를 알기 위해 AnimationListener 설정
        // (8) ==========
        flowAnimation.setAnimationListener(new AnimationAdapter());
    }

    /**
     * 화면에 보여지기 전에 호출되는 메소드(애니메이션 시작점)- 윈도우가 포커스를 받는 시점
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Toast.makeText(getApplicationContext(), "onWindowFocusChanged : " +
                hasFocus, Toast.LENGTH_LONG).show();
        //윈도우가 포커스를 받으면 hasFocus는 true
        if (hasFocus) {
            shakeAnimation.start();
            dropAnimation.start();
            dropAnimation_fast.start();
            dropAnimation_slow.start();
            flowAnimation.start();
        } else {//초기상태로 리셋
            shakeAnimation.reset();
            dropAnimation.reset();
            dropAnimation_slow.start();
            dropAnimation_fast.start();
            flowAnimation.reset();
        }
    }

    //화면에 뷰가 추가될 때 호출
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        Toast.makeText(getApplicationContext(), "attached.", Toast.LENGTH_LONG).show();
    }

    //화면에 뷰가 제거될 때 호출
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        Toast.makeText(getApplicationContext(), "detached.", Toast.LENGTH_LONG).show();
    }

    /**
     * 애니메이션의 시작과 종료 시점을 알기 위한 리스너
     */
    private final class AnimationAdapter implements Animation.AnimationListener {

        public void onAnimationStart(Animation animation) {
            Toast.makeText(getApplicationContext(), "Animation Started.", Toast.LENGTH_LONG).show();
        }

        public void onAnimationEnd(Animation animation) {
            Toast.makeText(getApplicationContext(), "Animation Ended.", Toast.LENGTH_LONG).show();
        }

        public void onAnimationRepeat(Animation animation) {

        }
    }
}
