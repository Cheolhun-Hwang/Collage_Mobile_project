package com.myself.hch.class_practice_170508_listview09;
//데이터를 담아둘 클래스 선언(DTO:Data Transfer Object)
public class SingerItem {

    int resId;//이미지 리소스 id
    String name;//이름
	String company;//소속
    String song;//노래

    //생성자
    public SingerItem(int resId, String name, String company, String song ) {
        this.resId = resId;
        this.name = name;
        this.company = company;
        this.song = song;
    }


    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
