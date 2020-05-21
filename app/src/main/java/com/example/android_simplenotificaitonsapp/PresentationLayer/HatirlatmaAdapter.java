package com.example.android_simplenotificaitonsapp.PresentationLayer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.EntityBase;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.HatirlatmaEntity;

import java.util.Vector;

public class HatirlatmaAdapter extends BaseAdapter {
    private Context context;
    private Vector<EntityBase> model;

    public HatirlatmaAdapter(Context context, Vector<EntityBase> model){
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((HatirlatmaEntity) getItem(position)).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HatirlatmaEntity ke = (HatirlatmaEntity) getItem(position);
        TextView tID = new TextView(context);
        tID.setHeight(190);
        tID.setTextSize(53);
        tID.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tID.setTextColor(Color.BLACK);;
        tID.setText(String.valueOf(ke.getID()));
        return tID;
    }
}