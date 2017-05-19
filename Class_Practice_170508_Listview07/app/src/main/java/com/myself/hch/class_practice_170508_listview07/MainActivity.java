package com.myself.hch.class_practice_170508_listview07;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //어댑터 생성
        adapter = new ListViewAdapter();

        listView = (ListView) findViewById(R.id.listView);

        //리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        //어댑터에 데이터 추가
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.img01),
                "정은지", "에이핑크");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.img02),
                "트와이스", "JYP엔터테인먼트");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.img03),
                "하이라이트", "어라운드어스");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.img04),
                "워너", "YG엔터테인먼트");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.img05),
                "걸스데이", "드림티엔터테인먼트");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.img06),
                "이엑스아이디", "바나나컬쳐엔터테인먼트");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.img07),
                "지코", "세븐시즌스");

        //리스트뷰에서 아이템 클릭시 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* onItemClick() 메서드 params
               - parent : 클릭한 아이템을 포함하는 부모 뷰(ListView)
               - view : 클릭한 항목의 View
               - position : 클릭한 아이템의 Adepter에서의 위치값(0, 1, 2,...)
               - id : DB를 사용했을 때 Cursor의 id 값값
            */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //선택한 아이템의 텍스트를 가져와서 저장
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                String titleStr = item.getTitle();
                String descStr = item.getDescription();
                Drawable iconDrawable = item.getIcon();

                Toast.makeText(getApplicationContext(), "선택한 아이템은 " + titleStr + "입니다",
                        Toast.LENGTH_LONG).show();
            }
        });//end of setOnItemClickListener

        // 필터링에 사용할 검색어는 입력상자에서 입력 받는다.
        EditText editTextSearch = (EditText) findViewById(R.id.editTextSearch);

        /* 입력상자(editTextSearch)의 내용이 변경될 때마다 리스트뷰의 필터링 텍스트를 변경 - 이벤트 처리
           - 입력상자의 내용이 변경될 때의 이벤트 리스너는 addTextChangedListener()를 통해 전달하며,
             TextWatcher 인터페이스를 implements하여 전달하면 됨
           - TextWatcher 인터페이스는 3개의 메서드를 가지고 있는데,
             입력상자의 텍스트가 변경되었을 때의 이벤트는 afterTextChanged() 메서드에서 처리함
         */

        //입력상자(EditText)의 텍스트 변경 이벤트 처리
        editTextSearch.addTextChangedListener(new TextWatcher() {
            //입력상자의 텍스트가 변경되었을 때의 자동 호출됨 - 필터링 수행
            @Override
            public void afterTextChanged(Editable edit) {
                //검색 키워드(필터링할 문자열)
                String filterText = edit.toString();

                //리스트뷰 아이템 필터링
                // 방법1) setFilterText() 메서드를 사용한 필터링 - 필터링 텍스트 팝업창이 뜬다.
                /*
                if (filterText.length() > 0) {
                    listView.setFilterText(filterText);//필터링
                } else {
                    listView.clearTextFilter();//필터링되어 표시된 결과를 초기상태로 clear
                }
                */

                /* 방법2) 리스트뷰를 통하지 않고 어댑터로부터 직접 filter() 객체를 가져와서
                   filter() 메서드로 필터링 수행
                   - setFilterText() 메서들 사용하면 필터링 텍스트 팝업창이 띄어지는 것을 피하기 위해서.
                */

                ((ListViewAdapter)listView.getAdapter()).getFilter().filter(filterText);
            }//end of afterTextChanged()

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });//end of addTextChangedListener
    }//end of onCreate()
}//end of MainActivity