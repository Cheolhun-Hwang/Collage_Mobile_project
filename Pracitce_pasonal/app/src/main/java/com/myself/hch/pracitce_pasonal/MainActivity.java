package com.myself.hch.pracitce_pasonal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Info person;
    Button btn1;
    Button btn2;

    TextView name;
    TextView age;
    TextView address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        String namestring = "황철훈";
        String agestring = "25";
        String addressstring = "가천대";
        */

        //post1 -> 생성자 아무것도 없는거
        //post2 -> 생성자 다 받아서 생성

        Info person1 = new Info();
        person1.setName("황철훈");
        person1.setAge("25");
        person1.setAddress("가천대");

        Info person2 = new Info("황철훈", "25");

        Info person3 = new Info("황철훈", "25", "가천대");

        Info person4 = new Info("황철훈1", "251", "가천대1");

        name = (TextView) findViewById(R.id.NameTextview);
        name.setText(person1.getName() + person1.getAge() + person1.getAddress());


        age = (TextView) findViewById(R.id.Agetextview);
        age.setText(person1.getallString());

        address = (TextView) findViewById(R.id.addresstextview);
        address.setText(person1.getallString("추가문자열"));









        ///////////////////////////////////////




        btn1 = (Button) findViewById(R.id.BTN_one);
        btn2 = (Button) findViewById(R.id.BTN_two);



    }

    public int sum(int num1, int num2){
        //1
        /*
        int result = num1 + num2;

        return result;*/

        return num1 + num2;
    }

    public void onClick_BTN_one(View v){
        name.setText("Cat");
    }

    public void onClicked(View v){
        switch (v.getId()){
            case R.id.BTN_one:
                name.setText("Cat");
                break;
            case R.id.BTN_two:
                name.setText("Dog");
                break;
        }
    }



    class Info extends MainActivity{
         private String Name;
        String Age;
        String Address;

        public Info(){
            this.Name = "";
            Age = "";
            Address = "";
        }
        public Info(String N, String A){
            this.Name = N;
            Age = A;
            Address = "";
        }

        public Info(String N, String A, String add){
            this.Name = N;
            Age = A;
            Address = add;
        }

        public String getName() {
            return Name;
        }

        public String getAge() {
            return Age;
        }

        public String getAddress() {
            return Address;
        }

        public void setName(String name) {
            Name = name;
        }

        public void setAge(String age) {
            Age = age;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getallString(){
            return this.Name+ " / " +this.Age+this.Address;
        }

        public String getallString(String N){
            return this.Name+this.Age+this.Address+N;
        }
    }



}
