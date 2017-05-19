package com.myself.hch.class_practice_170508;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hch on 2017-05-08.
 */

public class Myadapter extends BaseAdapter {
    Context mContext;
    int layout;
    ArrayList<MyPhoneAddressManager> list;
    LayoutInflater inf;

    public Myadapter(){
        this.mContext = null;
        this.list = null;
        this.layout = 0;
    }

    public Myadapter(Context ctx, int l, ArrayList<MyPhoneAddressManager> list){
        this.mContext = ctx;
        this.list = list;
        this.layout = l;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            this.inf = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(layout, parent, false);
        }

        TextView Name = (TextView) convertView.findViewById(R.id.listview_item_name);
        TextView Call = (TextView) convertView.findViewById(R.id.listview_item_callnumber);
        TextView Relation = (TextView)convertView.findViewById(R.id.listview_item_relation);
        ImageView Image = (ImageView)convertView.findViewById(R.id.listview_item_image);

        MyPhoneAddressManager MPAM = list.get(position);

        if(MPAM.getImage().equals("noImage")){
            Name.setText(MPAM.getName());
            Call.setText(MPAM.getPhone());
            Relation.setText(MPAM.getRelation());
        }else{
            Name.setText(MPAM.getName());
            Call.setText(MPAM.getPhone());
            Relation.setText(MPAM.getRelation());
            //Image.set
        }

        return convertView;
    }
}
