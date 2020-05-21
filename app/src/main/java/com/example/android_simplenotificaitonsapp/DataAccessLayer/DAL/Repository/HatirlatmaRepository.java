package com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.BlankEntity;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.EntityBase;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.HatirlatmaEntity;

import java.util.Vector;

public class HatirlatmaRepository extends IRepository {
    public static final String Table_Name = "Hatirlatmalar";
    public static final String ID = "_id";
    public static final String Metin = "Metin";
    public static final String Tarih = "Tarih";
    public static final String Kategori = "Kategori";
    public HatirlatmaRepository(Context context) {
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
        HatirlatmaEntity he = (HatirlatmaEntity) e;
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Metin,he.getMetin());
        cv.put(Tarih,he.getTarih());
        cv.put(Kategori,he.getKategori());
        long r = db.insert(Table_Name,null,cv);
        db.close();
        if(r>0)
            return true;
        else
            return false;
    }

    @Override
    public boolean Update(EntityBase e) {
        HatirlatmaEntity he = (HatirlatmaEntity) e;
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Metin,he.getMetin());
        cv.put(Tarih,he.getTarih());
        cv.put(Kategori,he.getKategori());
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
        Cursor cur = db.rawQuery("Select * from "+Table_Name+" where _id = ? ",new String[]
                {String.valueOf(id)});

        if (cur.moveToNext()){
            entity = new HatirlatmaEntity(id,cur.getString(cur.getColumnIndex(Metin)),
                    cur.getInt(2),cur.getString(3));

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
        Cursor cur = db.query(Table_Name,new String[]{ID,Metin,Kategori,Tarih},"",
                null,"","","");
        Vector<EntityBase> records = new Vector<EntityBase>(cur.getCount());
        while(cur.moveToNext()){
            entity = new HatirlatmaEntity(cur.getInt(0),cur.getString(cur.getColumnIndex
                    (Metin)),
                    cur.getInt(2),cur.getString(3));
            records.add(entity);
        }
        return records;
    }
}
