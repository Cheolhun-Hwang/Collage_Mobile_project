package com.myself.hch.class_practice_170508;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<MyPhoneAddressManager> list;
    MyPhoneAddressManager mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<MyPhoneAddressManager>();

        for(int i = 0;i<5;i++){
            mp = new MyPhoneAddressManager("황철훈", "010-4826-0178", "나", "noImage");
            list.add(mp);
        }


        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(new Myadapter(getApplicationContext(), R.layout.listview_item, list ));
    }
}
