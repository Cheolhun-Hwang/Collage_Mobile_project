package com.myself.hch.class_practice_170508;

/**
 * Created by hch on 2017-05-08.
 */

public class MyPhoneAddressManager {
    String name;
    String phone;
    String relation;
    String image;

    public MyPhoneAddressManager(){
        name = "";
        phone="";
        relation="";
        image="";
    }

    public MyPhoneAddressManager(String n, String p, String r, String i){
        name = n;
        phone=p;
        relation=r;
        image=i;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRelation() {
        return relation;
    }

    public String getImage() {
        return image;
    }
}
