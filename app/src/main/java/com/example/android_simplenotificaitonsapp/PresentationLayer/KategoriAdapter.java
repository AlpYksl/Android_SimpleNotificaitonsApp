package com.example.android_simplenotificaitonsapp.PresentationLayer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.EntityBase;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.KategoriEntity;

import java.util.Vector;

public class KategoriAdapter extends BaseAdapter {
    private Context context;
    private Vector<EntityBase> model;
    @Override
    public int getCount() {
        return model.size();
    }

    public KategoriAdapter(Context context, Vector<EntityBase> model){
        this.context = context;
        this.model = model;
    }
    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((KategoriEntity) getItem(position)).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        KategoriEntity ke = (KategoriEntity) getItem(position);
        LinearLayout p = new LinearLayout(context);
        p.setOrientation(LinearLayout.HORIZONTAL);
        TextView tID = new TextView(context);
        tID.setText(String.valueOf(ke.getID()));
        tID.setVisibility(View.INVISIBLE);
        TextView taciklama = new TextView(context);
        taciklama.setText(ke.getAciklama());
        taciklama.setTextSize(30);
        taciklama.setHeight(200);
        taciklama.setTextSize(50);
        taciklama.setTextColor(Color.BLACK);
        p.addView(tID);
        p.addView(taciklama);
        return p;
    }
}
