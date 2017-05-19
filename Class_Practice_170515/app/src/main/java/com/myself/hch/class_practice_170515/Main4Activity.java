package com.myself.hch.class_practice_170515;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;


public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        setTitle("노래 목록");
    }

    //노래목록 아이템 클릭시 호출되는 메소드
    public void play(View v) {
        //클릭한 뷰의 id 인식
        // (1) =============
        int id = v.getId();
        //클릭한 뷰의 id에 해당하는 리니어레이아웃 인식
        // (2) =============
        LinearLayout layout = (LinearLayout) findViewById(id);
        //레이아웃 태그 값 추출(선택한 노래의 tag 값(1, 2, 3))
        // (3) =============
        String tag = (String)layout.getTag();

        //AudioImage 클래스로 데이터를 전송하기 위한 인텐트 생성
        // (4) =============
        Intent it = new Intent(this, AudioImage.class);
        //태그값을 'it_tag"에 저장
        it.putExtra("it_tag", tag);
        //AudioImage 클래스의 액티비티 호출
        startActivity(it);
    }
}
