package com.example.hch.class_weed6_ex01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText stdNo;
    TextView serviceResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stdNo = (EditText) findViewById(R.id.stdNo_editText);
        serviceResult = (TextView) findViewById(R.id.serviceResult);
    }

    public void onSendButtonClicked(View v){
        String std_no = stdNo.getText().toString();

        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("from", "Activity");
        intent.putExtra("hakbun", std_no);

        startService(intent);
    }

    protected void onNewIntent(Intent intent){
        if(intent != null){
            String str = String.format("서비스에서 전송된 데이터 \n\n보낸곳 : %s\n내 용 : %s",
                    intent.getStringExtra("from"),
                    intent.getStringExtra("contents"));
            serviceResult.setText(str);
        }

        super.onNewIntent(intent);
    }
}
