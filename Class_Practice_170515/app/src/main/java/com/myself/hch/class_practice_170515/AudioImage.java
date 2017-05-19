package com.myself.hch.class_practice_170515;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AudioImage extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_image);

        setTitle("노래 재생");

        //이 액티비티를 호출한 인텐트 반환
			// (1) =============
        Intent it = getIntent();
        //인텐트의 "it_tag" 변수에 저장된 값 추출(노래 tag 값 : 1, 2, 3)
        // (2) =============
        String tag = it.getStringExtra("it_tag");

        TextView title = (TextView)findViewById(R.id.title);
        ImageView song_image = (ImageView)findViewById(R.id.song_image);
        TextView lyrics = (TextView)findViewById(R.id.lyrics);

        //어플리케이션 리소스 객체 생성
        // (3) =============
        Resources res = getResources();

        int stringId;
        String myKey;

        //====== 노래 제목 ====
        //"title"과 tag 값의 이름으로 된 노래 제목에 대한 id를 인식하고 해당하는 문자열 출력
        // (4) =============
        stringId = res.getIdentifier("title" + tag, "string", getPackageName());

        //노래제목 인식
        // (5) =============
        myKey = res.getString(stringId);
        Toast.makeText(getApplicationContext(), "노래제목 :" + myKey, Toast.LENGTH_LONG).show();
        //노래제목 표시
        title.setText(myKey);

        // ====== 노래 이미지 ========
        //"title"과 태그 값의 이름으로 된 이미지명에 대한 id를 인식하고 해당하는 문자열의 이미지 출력
        // (6) =============
        stringId = res.getIdentifier("song_image" + tag, "string", getPackageName());
        myKey = res.getString(stringId);
        Toast.makeText(getApplicationContext(), "노래이미지 :" + myKey, Toast.LENGTH_LONG).show();
        int id_image = res.getIdentifier(myKey, "drawable", getPackageName());
        song_image.setImageResource(id_image);

        //==========  노래 가사 ============
        //"lyrics"과 태그 값의 이름으로 된 노래 가사에 대한 id를 인식하고 해당하는 문자열 출력
        // (7) =============
        stringId = res.getIdentifier("lyrics" + tag, "string", getPackageName());
        myKey = res.getString(stringId);
        Toast.makeText(getApplicationContext(), "노래가사 :" + myKey, Toast.LENGTH_LONG).show();

        lyrics.setText(myKey);

        //============ 노래 재생 ================
        //"audio"와 태그값의 이름으로 된 오디오명에 대한 id를 인식하고 해당하는 노래를 미디어플레이어로 재생
        // (8) =============
        stringId = res.getIdentifier("audio" + tag, "string", getPackageName());
        myKey = res.getString(stringId);
        Toast.makeText(getApplicationContext(), "노래재생 :" + myKey, Toast.LENGTH_LONG).show();

        //MediaPlayer 실행(노래 재생)
        // (9) =============
        int id_audio = res.getIdentifier(myKey, "raw", getPackageName());
        mp = MediaPlayer.create(this, id_audio);
        mp.setLooping(false);
        mp.start();
    }

    public void goBack(View v) {
        mp.stop();
        mp.release();
        finish();
    }
}
