package com.week3.qewqs.week3_example3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText hakbun, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        hakbun = (EditText) findViewById(R.id.std_no);
        score = (EditText) findViewById(R.id.std_score);
    }

    public void onOK_btnClicked(View v){
        Intent myIntent = new Intent(SecondActivity.this, MainActivity.class);
        myIntent.putExtra("hakbun", hakbun.getText().toString());
        myIntent.putExtra("score", score.getText().toString());
        setResult(RESULT_OK, myIntent);

        finish();
    }

    public void onCancel_btnClicked(View v){
        setResult(RESULT_CANCELED);

        finish();
    }
}
