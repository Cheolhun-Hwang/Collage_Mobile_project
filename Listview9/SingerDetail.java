package com.example.user.listview09;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//상세 정보를 위한 액티비티
public class SingerDetail extends AppCompatActivity {
    ImageView imageView;
    TextView nameTextView, companyTextView, songTextView;
    Button backBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singer_detail);


        //부분화면 레이아웃에 정의된 객체 참조
        imageView = (ImageView) findViewById(R.id.imageView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        companyTextView = (TextView) findViewById(R.id.companyTextView);
        songTextView = (TextView) findViewById(R.id.songTextView);
        backBtn = (Button) findViewById(R.id.backBtn);

        //인텐트 정보를 가져와서 저장
        Intent intent = getIntent();

        imageView.setImageResource(intent.getIntExtra("img", 0));
        nameTextView.setText(intent.getStringExtra("name"));
        companyTextView.setText(intent.getStringExtra("company"));
        songTextView.setText(intent.getStringExtra("song"));

        //Back 버튼 클릭시 종료 이벤트
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });//end of setOnClickListener
    }//end od onCreate()
}//end of MainActivity
