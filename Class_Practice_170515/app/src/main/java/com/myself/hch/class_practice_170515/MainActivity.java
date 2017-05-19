package com.myself.hch.class_practice_170515;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/* 스레드를 이용해 프로그레스바를 보여주는 방법(메시지 전송 방식)
   새로 만든 스레드에서 메인 스레드를 접근할 때 핸들러를 사용하는 방법 */
public class MainActivity extends AppCompatActivity {

    //프로그레스바
    ProgressBar bar;
    TextView textView01;
    boolean isRunning = false;

    // 핸들러 객체 선언
    ProgressHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (ProgressBar) findViewById(R.id.progress);
        textView01 = (TextView) findViewById(R.id.textView01);

        //핸들러 객체 생성
        handler = new ProgressHandler();
    }

    @Override
    public void onStart() {
        super.onStart();
        //프로그래스 바 초기값 설정
        bar.setProgress(0);
        //새로운 스레드 생성
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 20 && isRunning; i++) {
                        Thread.sleep(1000);
                        //메시지 객체 참조
                        // (1) ==============
                        Message msg = handler.obtainMessage();
                        //메시지를 핸들러의 메시지 큐에 전달
                        // (2) ==============
                        handler.sendMessage(msg);
                    }
                } catch (Exception ex) {
                    Log.e("SampleThreadActivity", "Exception in processing message.", ex);
                }
            }
        });

        isRunning = true;
        thread1.start();
    }

    @Override
    public void onStop() {
        super.onStop();

        isRunning = false;
    }
    //Handler 클래스를 상속하여 새로운 핸들러 클래스 정의
    public class ProgressHandler extends Handler {
        /* 메인 스레드에서 수행할 기능 정의(UI 업데이트)
           - 메시지 큐에 전달된 메시지를 꺼내 순서대로 처리 */
        @Override
        public void handleMessage(Message msg) {
            //프로그래스바 진행률을 5%씩 증가
            bar.incrementProgressBy(5);

            //프로그래스바에 설정된 값을 getProgress() 메소드로 가져와 처리
            if (bar.getProgress() == bar.getMax()) {
                textView01.setText("Done");
            } else {
                textView01.setText("Progressing ..." + bar.getProgress());
            }
        }
    }
}

