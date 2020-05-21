package com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities;

public class KategoriEntity extends EntityBase {
    private String aciklama;

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
    public  KategoriEntity(int id, String aciklama){
        super(id);
        this.aciklama =aciklama;
    }
}
