package com.myself.hch.class_practice_170508_listview06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* ArrayList 생성
           - ArrayList는 일반적인 배열의 기능에서 확장된 클래스로 배열을 데이터를 추가하거나 삭제 가능
        */
        // (1) =====================================================
        final ArrayList<String> items = new ArrayList<String>();
        /* ArrayAdapter 생성
           - ArrayAdapter는 ListView와 ArrayList 어댑터 역할 수행
           - 수정 또는 삭제 시 아이템을 선택할 수 있도록 android.R.layout.simple_list_item_single_choice 선언.
           - 객체 배열이나 리소스에 정의된 배열로부터 데이터를 가져옴
           - params
             첫번째 인수 : Context
             두번째 인수 : Resource : 아이템을 표시할 레이아웃의 리소스 id(작성한 XML 레이아웃 또는 인드로이드에서 제공하는 레이아웃 id)
             세번째 인수 : Objects : 테이터

            - 리스트뷰의 아이템 레이아웃을 만들지 않고, 안드로이드 시스템에서 제공하는 기본 레이아웃 사용
              (android.R.layout.simple_list_item_single_choice: TextView와 RadioButton 위젯으로 구성)
         */
        // (2) ==================================
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items);
        /* 리스트뷰에 어댑터 지정
           - setAdapter()메서드를 통해 리스트뷰에 사용할 데이터를 전달
         */
        listView = (ListView) findViewById(R.id.listView);
        // (3) ==================================
        listView.setAdapter(adapter);

        //추가 버튼을 클릭한 경우 아이템 추가
        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //어댑터에서 관리하는 리스트(아이템) 갯수를 item_cnt에 저장
                // (4) ==================================
                int item_cnt = adapter.getCount();
                //어댑터의 리스트에 아이템 추가
                // (5) ==================================
                items.add("List" + Integer.toString(item_cnt+1));

                /* 리스트뷰 갱신
                   - 어댑터의 리스트(items)에 내용(아이템)을 추가한 후,
                     데이터가 변경되었음을 알려주어 리스트뷰를 갱신한다.
                 */
                // (6) ==================================
                adapter.notifyDataSetChanged();
            }
        });

        //수정 버튼 클릭시 아이템 수정
        Button modifyBtn = (Button) findViewById(R.id.modifyBtn);
        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int item_cnt, checked_position;
                //어댑터에서 관리하는 리스트(아이템) 갯수를 item_cnt에 저장
                // (7) ==================================
                item_cnt = adapter.getCount();

                if (item_cnt > 0) {
                    //현재 선택된 아이템의 position을 checked_position에 저장
                    checked_position = listView.getCheckedItemPosition();
                    //선택한 position이 전체 아이템 갯수 범위 안에 있는지 확인
                    if (checked_position > -1 && checked_position < item_cnt) {
                        //선택한 아이템 수정
                        // (8) ==================================
                        items.set(checked_position, Integer.toString(checked_position+1)+"번 아이템 수정");
                        //리스트뷰 갱신
                        // (9) ==================================
                    }   adapter.notifyDataSetChanged();
                }
            }
        });

        //삭제 버튼 클릭시 아이템 삭제
        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int item_cnt, checked_position;
                //어댑터에서 관리하는 리스트(아이템) 갯수를 item_cnt에 저장
                item_cnt = adapter.getCount();

                if (item_cnt > 0) {
                    //현재 선택된 아이템의 position을 checked_position에 저장
                    // (10) ==================================
                    checked_position = listView.getCheckedItemPosition();
                    if (checked_position > -1 && checked_position < item_cnt) {
                        //선택한 아이템 삭제
                        // (11) ==================================
                        items.remove(checked_position);
                        //리스트뷰 선택 초기화
                        // (12) ==================================
                        listView.clearChoices();
                        //리스트뷰 갱신
                        // (13) ==================================
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
