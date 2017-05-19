package com.myself.hch.week7_practice_exam03;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ProgressDialog progressDialog;
    boolean isRunning = false;
    Handler handler;
    Thread thread;
    ProgressRunnable runnable;

    TextView progressText;
    Button btnShow, btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        runnable = new ProgressRunnable();

        progressText = (TextView) findViewById(R.id.dataItem);


        btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("진행상태");
                progressDialog.setMessage("데이터를 확인하는 중입니다.");

                progressDialog.show();
            }
        });

        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(progressDialog != null){
                    progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    protected  void onStart(){
        super.onStart();

        progressBar = (ProgressBar) findViewById(R.id.progressBar01);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i = 0; i<10 && isRunning ; i++){
                        Thread.sleep(1000);

                        handler.post(runnable);
                    }
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();

        isRunning = false;
    }

    public void onButtonClicked(View v){
        isRunning = true;
        thread.start();
    }

    public class ProgressRunnable implements Runnable
    {
        @Override
        public void run() {
            progressBar.incrementProgressBy(10);
            if(progressBar.getProgress() <= progressBar.getMax()){
                progressText.setText(progressBar.getProgress() + "%");
            }
        }
    }
}
