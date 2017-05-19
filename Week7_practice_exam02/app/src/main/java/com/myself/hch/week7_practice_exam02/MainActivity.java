package com.myself.hch.week7_practice_exam02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    boolean isPageOpen = false;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    LinearLayout slidingPage01;
    Button openBtn01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidingPage01 = (LinearLayout) findViewById(R.id. slidingPage01 );
        translateLeftAnim = AnimationUtils. loadAnimation (this, R.anim. translate_left );
        translateRightAnim = AnimationUtils. loadAnimation (this, R.anim. translate_right );
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);
        openBtn01 = (Button) findViewById(R.id.button1 );
        openBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageOpen){
                    slidingPage01.startAnimation(translateRightAnim);
                }else{
                    slidingPage01.setVisibility(View.VISIBLE);
                    slidingPage01.startAnimation(translateLeftAnim);
                }
            }
        });
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                slidingPage01.setVisibility(View.INVISIBLE );
                openBtn01.setText("Open");
                isPageOpen = false;
            }else {
                openBtn01.setText("Close");
                isPageOpen = true;
            }
    }
        public void onAnimationRepeat(Animation animation) { }
        public void onAnimationStart(Animation animation) { }
    }
}

