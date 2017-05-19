package com.myself.hch.class_practice_170515;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        setTitle("동영상 목록");
    }

    //동영상 재생 메소드
    public void play(View v) {
        //클릭한 뷰의 id 저장
        //(1) ===========
        int id = v.getId();
        //클릭한 뷰의 id에 해당하는 레이아웃 저장
        //(2) ===========
        LinearLayout layout = (LinearLayout) findViewById(id);
        //레이아웃 태그값 저장(1, 2, 3, 4)
        //(3) ===========
        String tag = (String)layout.getTag();
        //동영상 재생 클래스인 VideoPlayback 클래스를 전송하기 위한 인텐트 생성
        //(4) ===========
        Intent it = new Intent(this, VideoPlayback.class);
        it.putExtra("it_tag", tag);//태그값 저장
        startActivity(it);//애티비티 호출
    }
}
