package com.example.hch.class_weed6_ex01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hch on 17. 4. 10.
 */

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreate() 호출됨");
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(TAG, "onStartCommand() 호출됨");

        if(intent == null){
            return Service.START_NOT_STICKY;
        }else{
            String from = intent.getStringExtra("from");
            String std_no = intent.getStringExtra("hakbun");

            Log.d(TAG, "from : " + from +", hakbun"+ std_no);

            for(int i = 0 ; i<5 ; i++){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();;
                }
                Log.d(TAG, "Waiting : " + i + "second.");
            }

            Intent serviceIntent = new Intent(getApplicationContext(), MainActivity.class);

            serviceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                    Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP);

            serviceIntent.putExtra("from", "Service");
            serviceIntent.putExtra("contents", "학생(학번:"+std_no+")의 정보를 등록하였습니다.");

            startActivity(serviceIntent);

        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy(){
        Log.d(TAG, "onDestroy() 호출됨");

        super.onDestroy();
    }

}
