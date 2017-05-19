package com.myself.hch.class_practice_170515;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

//비디오 재생
public class VideoPlayback extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_playback);

        setTitle("동영상 재생");

        //인텐트 반환
          //(1) ===========
        Intent it = getIntent();
        //인텐트의 it_tag 값 반환
          // (2) ===========
        String tag = it.getStringExtra("it_tag");
        TextView title = (TextView)findViewById(R.id.title);
        VideoView videoview = (VideoView)findViewById(R.id.videoview);

        int stringId;
        String myKey;

        //어플리케이션 리소스 객체 생성
          //(3) ===========
        Resources res = getResources();

        //"title"+tag 값의 이름으로 된 동영상 제목 출력
          //(4) ===========
        stringId = res.getIdentifier("title" + tag, "string", getPackageName());
        myKey = res.getString(stringId);
        title.setText(myKey);
        //"video"+tag 값의 이름으로 된 동영상 파일의 id와 파일명 추출
         //(5) ===========
        stringId = res.getIdentifier("video" + tag, "string", getPackageName());
        myKey = res.getString(stringId);
        //"raw" 폴더에 있는 동영상 파일의 id 인식
          //(6) ===========
        int id_video = res.getIdentifier(myKey, "raw", getPackageName());

        //동영상 파일의 uri 저장
          //(7) ===========
        Uri uri = Uri.parse("android.resource://com.myself.hch.class_practice_170515/"+id_video);
        //비디오뷰에 동영상 uri 설정
          //(8) ===========
        videoview.setVideoURI(uri);
		//동영상 시작
          //(9) ===========
        videoview.start();

        //미디어 제어기 생성
        MediaController mcontroller = new MediaController(this);
        //미디오뷰에 미디어제어기 설정
        videoview.setMediaController(mcontroller);
    }
    //동영상 목록 아이템 클릭시 호출되는 메소드 - 동영상 종료
    public void closeVideo(View v) {
        finish();
    }
}
