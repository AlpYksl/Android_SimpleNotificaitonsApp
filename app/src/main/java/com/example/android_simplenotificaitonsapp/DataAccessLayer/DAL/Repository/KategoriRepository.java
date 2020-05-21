package com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.BlankEntity;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.EntityBase;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.KategoriEntity;

import java.util.Vector;

public class KategoriRepository extends IRepository {
    public static final String Table_Name = "Kategoriler";
    public static final String ID = "SıraNo";
    public static final String Aciklama = "Aciklama";
    public KategoriRepository(Context context) {
        super(context);
    }

    @Override
    public long getCount() {
        SQLiteDatabase db = super.dbg.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+Table_Name,null);
        long r = cur.getCount();
        db.close();
        return r;
    }

    @Override
    public boolean Add(EntityBase e) {
        KategoriEntity he = (KategoriEntity) e;
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Aciklama,he.getAciklama());
        long r = db.insert(Table_Name,null,cv);
        db.close();
        if(r>0)
            return true;
        else
            return false;
    }

    @Override
    public boolean Update(EntityBase e) {
        KategoriEntity he = (KategoriEntity) e;
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Aciklama,he.getAciklama());
        long r = db.update(Table_Name,cv,ID+" =?",new String[]{String.valueOf(he.getID())});
        db.close();
        if(r>0)
            return true;
        else
            return false;
    }

    @Override
    public boolean Delete(int id) {
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        long r = db.delete(Table_Name,ID+" =? ",new String[]{String.valueOf(id)});
        db.close();
        if (r>0)
            return true;
        else
            return false;
    }

    @Override
    public EntityBase GetRecord(int id) {
        EntityBase entity = null;
        SQLiteDatabase db = super.dbg.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * from "+Table_Name+" where SıraNo = ? ",new String[]{String.valueOf(id)});

        if (cur.moveToNext()){
            entity = new KategoriEntity(id,cur.getString(cur.getColumnIndex(Aciklama)));
        }
        else {
            entity = new BlankEntity();
        }
        return entity;
    }

    @Override
    public Vector<EntityBase> GetResult() {
        EntityBase entity = null;
        SQLiteDatabase db = super.dbg.getReadableDatabase();
        Cursor cur = db.query(Table_Name,new String[]{ID,Aciklama},"",null,"","","");
        Vector<EntityBase> records = new Vector<EntityBase>(cur.getCount());
        while(cur.moveToNext()){
            entity = new KategoriEntity(cur.getInt(0),cur.getString(cur.getColumnIndex(Aciklama)));
            records.add(entity);
        }
        return records;
    }
}
