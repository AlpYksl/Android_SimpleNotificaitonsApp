package com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities;

public class EntityBase {

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public  EntityBase(){}
    public EntityBase(int id){
        this();
        ID = id;
    }
}
