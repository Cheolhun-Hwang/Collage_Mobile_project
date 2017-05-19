package com.week3.qewqs.week3_example3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int REQEUST_CODE = 1001; //요청코드
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView) findViewById(R.id.resultTextview);
    }

    public void onInsert_btnClicked(View v){
        Intent myIntent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivityForResult(myIntent, REQEUST_CODE);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultcode, Intent data){
        super.onActivityResult(requestCode, resultcode, data);

        switch(requestCode){
            case REQEUST_CODE:
                if(resultcode == RESULT_OK){
                    String str = String.format("성적 입력결과\n\n학번 : %s\n성적 : %s",
                            data.getStringExtra("hakbun"),
                            data.getStringExtra("score"));
                    resultTextView.setText(str);
                }else if (resultcode == RESULT_CANCELED){
                    resultTextView.setText("성적 입력을취소하였습니다.");
                }
                break;
        }
    }
}
