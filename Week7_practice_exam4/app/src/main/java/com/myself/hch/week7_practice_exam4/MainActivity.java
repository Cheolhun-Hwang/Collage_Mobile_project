package com.myself.hch.week7_practice_exam4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressDialog asyncDialog;
    Button startbtn, closebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbtn = (Button) findViewById(R.id.startBtn);
        closebtn = (Button) findViewById(R.id.closeBtn);
    }

    public void onButtonClicked(View v){
        switch (v.getId()){
            case R.id.startBtn :
                Toast.makeText(MainActivity.this, "startBtn call", Toast.LENGTH_SHORT).show();
                if(asyncDialog != null) return;
                new AsyncProgressDialog().execute(101);
                break;
            case R.id.closeBtn:
                Toast.makeText(MainActivity.this, "cloasBtn call", Toast.LENGTH_SHORT).show();
                if (asyncDialog != null){
                    asyncDialog.dismiss();
                }
                break;
        }
    }


    private class AsyncProgressDialog extends AsyncTask<Integer, String, Integer>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();///////////////////////////부모 생성자
            asyncDialog = new ProgressDialog(MainActivity.this);
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            asyncDialog.setTitle("작업처리중.......");
            asyncDialog.setMessage("Loading........");

            asyncDialog.show();
            //super.onPreExecute();///////////////////////////부모생성자
        }

        @Override
        protected Integer doInBackground(Integer... params) {////////////////////////////'...'을 쓰는 의
            final int taskMaxCnt = params[0];

            publishProgress("max", Integer.toString(taskMaxCnt));

            for(int i =0; i<taskMaxCnt ; i++){
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                publishProgress("progress", Integer.toString(i), "작업 번호 " + Integer.toString(i) + "번 수행중");
            }

            return taskMaxCnt;
        }

        @Override
        protected void onProgressUpdate(String... progress){
            super.onProgressUpdate(progress);

            if(progress[0].equals("progress")){
                asyncDialog.setProgress(Integer.parseInt(progress[1]));
                asyncDialog.setMessage(progress[2]);
            }else if(progress[0].equals("max")){
                asyncDialog.setMax(Integer.parseInt(progress[1]));
            }
        }

        @Override
        protected void onPostExecute(Integer result){
            super.onPostExecute(result);

            asyncDialog.dismiss();
            Toast.makeText(MainActivity.this, Integer.toString(result) + "개의 작업 완료", Toast.LENGTH_SHORT).show();
            asyncDialog = null;
        }
    }
}
