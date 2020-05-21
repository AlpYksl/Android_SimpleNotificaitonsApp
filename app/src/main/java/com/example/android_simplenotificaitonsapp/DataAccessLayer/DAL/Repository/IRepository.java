package com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Repository;

import android.content.Context;

import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.EntityBase;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Veritabani;

import java.util.Vector;

public abstract class IRepository {

    protected Context context;
    protected Veritabani dbg;

    public IRepository(Context context){
        this.context = context;
        dbg = new Veritabani(context,"ornek.db",null,1);
    }
    public abstract long getCount();
    public abstract boolean Add(EntityBase e);
    public abstract boolean Update(EntityBase e);
    public abstract boolean Delete(int id);
    public abstract EntityBase GetRecord(int id);
    public abstract Vector<EntityBase> GetResult();
}
