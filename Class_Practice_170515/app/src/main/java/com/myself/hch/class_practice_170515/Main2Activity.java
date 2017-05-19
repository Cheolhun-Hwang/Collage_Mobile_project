package com.myself.hch.class_practice_170515;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView1, imageView2;
    EditText editText1, editText2;
    Button button1;

    /* 안드로이드에서는 화면에 보이는 모든 리소스를 메인 스레드에서 관리하며,
       다른 스레드에서 화면의 리소스에 대한 동시 접근을 금지한다.
       따라서 스레드에서 메인화면의 리소스를 사용하기 위해서는 핸들러에 전달하여
       처리를 요청하는 방식을 주로 사용한다.
     */
    Handler handler = new Handler();//핸들러 객체 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //DogThread 객체를 생성(강아지구분 0) 및 스레드 start
                // (1) ==============
                DogThread thread1 = new DogThread(0);
                thread1.start();
                //DogThread 객체를 생성(강아지구분 1) 및 스레드 start
                // (2) ==============
                DogThread thread2 = new DogThread(1);
                thread2.start();
            }
        });
    }//end of onCreate()

    //DogThread 클래스 정의
    class DogThread extends Thread {
        int dogIndex;//강아지 구분(0, 1)
        int stateIndex;//강아지 상태(이미지종류) 구분(0, 1, 2)
        int i = 0;

        /* 강아지 이미지 파일 3개를 ArrayList 타입의 리스트로 저장하기 위해
           images ArrayList 객체 생성
           - ArrayList의 타입은 강아지 이미지를 파일명이 아닌 리소스 id를 구분하기 위해
             Integer(정수)로 설정 */
        // (3) ==============
        ArrayList<Integer> images = new ArrayList<Integer>();
        //DogThread()객체가 만들어 질 때 강아지를 구분하는 인덱스값(0, 1)을 파라미터로 받는다.
        public DogThread(int index) {//생성자
            dogIndex = index;

            //images 리스트 객체에 3개의 이미지 리소스 id를 추가
            images.add(R.drawable.dog_standing);//리소스 id : 0
            images.add(R.drawable.dog_running);//리소스 id : 1
            images.add(R.drawable.dog_biting);//리소스 id : 2
        }

        public void run() {
            stateIndex = 0;

            for (i = 0; i < 10; i++) {
                /* 핸들러 객체의 post()메소드를 이용하여
                   새로 생성한 Runnable 객체를 전달하면 Runnable 객체의
                   run() 메소드의 코드가 메인 스레드에서 실행 */
                handler.post(new Runnable() {
                    public void run() {
                        //=========== 메인 스레드에서 실행할 코드 ===========
                        if (dogIndex == 0) {
                            //이미지뷰의 강아지 이미지를 바꾼다(0, 1, 2)
                            // (3) ==============
                            //입력상자에 현재 실행 상황을 표시
                            editText1.append("dog #" + dogIndex + " Running : " + i +"\n");

                        } else if (dogIndex == 1) {
                            imageView2.setImageResource(images.get(stateIndex));
                            editText2.append("dog #" + dogIndex + " Running : " + i +"\n");
                        }
                    }
                    //=======================================================
                });//end of post()

                try {
                    /* Thread sleep Time을 임의의 시간(500, 3000)으로 설정하기 위해
                       getRandomTime 호출 */
                    int sleepTime = getRandomTime(500, 3000);
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stateIndex++;
                //stateIndex 값이 이미지 갯수보다 크거나 같으면 0으로 설정
                if (stateIndex >= images.size()) {
                    stateIndex = 0;
                }
            }//end of for
        }//end of run()

        //0.5초(500)과 3초(3000) 사이의 난수 발생(시간)
        public int getRandomTime(int min, int max) {
            return min + (int)(Math.random() * (max - min));
        }

    }//end of DogThread
}
