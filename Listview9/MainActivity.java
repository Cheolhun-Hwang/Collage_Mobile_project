package com.example.user.listview09;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView itemView;
    MyAdapter adapter;

    //가수 앨범을 담을 리스트(SingerItem 객체를 담아둘 ArrayList 생성)
    ArrayList<SingerItem> album = new ArrayList<SingerItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        album.add(new SingerItem(R.drawable.img01, "정은지", "에이핑크", "너란 봄"));
        album.add(new SingerItem(R.drawable.img02, "트와이스", "JYP엔터테인먼트", "KNOCK KNOCK"));
        album.add(new SingerItem(R.drawable.img03, "하이라이트", "어라운드어스", "얼굴 찌프리지 말아요"));
        album.add(new SingerItem(R.drawable.img04, "워너", "YG엔터테인먼트", "REALLY REALLY"));
        album.add(new SingerItem(R.drawable.img05, "걸스데이", "드림티엔터테인먼트", "I'LL BE YOURS"));
        album.add(new SingerItem(R.drawable.img06, "이엑스아이디", "바나나컬쳐엔터테인먼트", "낮 보다는 밤"));
        album.add(new SingerItem(R.drawable.img07, "지코", "세븐시즌스", "SHES A BABY"));

        // 어댑터 객체 생성
        adapter = new MyAdapter(this, R.layout.singer_item, album);

        //listView 레이아웃 참조
        listView = (ListView) findViewById(R.id.listView);

        //어댑터 객체를 리스트 뷰에 설정
        listView.setAdapter(adapter);

        //리스트뷰에서 아이템 클릭시 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* params
               - parent : 클릭한 아이템을 포함하는 부모 뷰(ListView)
               - view : 클릭한 항목의 View
               - position : 클릭한 아이템의 Adepter에서의 위치값(0, 1, 2,...)
               - id : DB를 사용했을 때 Cursor의 id 값값
            */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position을 이용해 어댑터에서 아이템을 가져옴
                SingerItem curItem = (SingerItem) adapter.getItem(position);

                //getName() 메서드를 이용하여 아이템에서 이름을 가져옴
                String curName = curItem.getName();

                Toast.makeText(getApplicationContext(),
                               "안녕하세요. " + curName + "입니다.",
                               Toast.LENGTH_LONG).show();
                //인텐트 생성
                Intent intent = new Intent(
                        getApplicationContext(),
                        SingerDetail.class
                );

                //인텐트에 액티비티로 전달할 정보 입력
                intent.putExtra("img", album.get(position).resId);
                intent.putExtra("name", album.get(position).name);
                intent.putExtra("company", album.get(position).company);
                intent.putExtra("song", album.get(position).song);

                //액티비티 생성
                startActivity(intent);

            }
        });//end of setOnItemClickListener
    }//end of OnCreate()

    //어댑터객체 클래스 선언(리스트뷰에 사용할 데이터를 관리하고, 각 아이템을 위한 뷰 객체를 생성)
    class MyAdapter extends BaseAdapter {
        Context mContext;//전달받은 Context 객체를 저장할 변수
        int singer_item;
        ArrayList<SingerItem> album;
        LayoutInflater inflater;

        //어댑터 생성자
        public MyAdapter(Context context, int singer_item, ArrayList<SingerItem> album) {
            mContext = context;
            this.singer_item = singer_item;
            this.album = album;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        /* 어댑터를 리스트뷰에 설정하면 리스트뷰(위젯)가 자동 호출함
                - 리스트뷰가 아댑터에게 요청하는 메서드들... */

        /* 어댑터에서 관리하고 있는 데이터(아이템)의 갯수를 반환
           (itemsList의 크기(size) 반환) */
        @Override
        public int getCount() {
            return album.size();//리스트의 크기
        }

        //파라미터로 전달된 인덱스에 해당하는 데이터를 반환
        @Override
        public Object getItem(int position) {
            return album.get(position);//리스트에서 아이템을 가져와 반환
        }

        //현재 아이템의 Id값을 인덱스값(position)을 반환
        @Override
        public long getItemId(int position) {
            return position;
        }

        //리스트에 아이템을 추가
        public void addItem(SingerItem item) {
            album.add(item);
        }

        //리스트의 모든 아이템을 삭제
        public void clear() {
            album.clear();
        }

        @Override//화면에 보일 아이템을 위한 뷰를 만들어 반환
        public View getView(int position, View convertView, ViewGroup parent) {
            //아이템을 위한 레이아웃 생성
            SingerLayout singerLayout = null;

            if (convertView == null) {
                singerLayout = new SingerLayout(mContext);
            } else {
                singerLayout = (SingerLayout) convertView;
            }

            //아이템의 인덱스값(position)을 이용해 리스트에 들어있는 아이템을 가져옴
            SingerItem items = album.get(position);

            //아이템에서 이미지 리소스 id를 가져와, 레이아웃에 이미지 설정
            singerLayout.setImage(items.getResId());

            //아이템에서 이름을 가져와, 레이아웃에 이름 설정
            singerLayout.setNameText(items.getName());

            //아이템에서 소속을 가져와, 레이아웃에 소속 설정
            singerLayout.setCompany(items.getCompany());

            //아이템에서 노래를 가져와, 레이아웃에 노래 설정
            singerLayout.setSong(items.getSong());

            return singerLayout;//레이아웃을 리턴
        }//end of getView()
    }//end of MyAdapter class
}//end of MainActivity
