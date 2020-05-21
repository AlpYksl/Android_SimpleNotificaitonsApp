package com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities;

public class HatirlatmaEntity extends  EntityBase {

    private String Metin;
    private int Kategori;
    private String Tarih;

    public  HatirlatmaEntity(int id, String Metin, int kategori,String Tarih){
        super(id);
        this.Metin = Metin;
        this.Kategori=kategori;
        this.Tarih = Tarih;
    }

    public int getKategori() {
        return Kategori;
    }

    public String getMetin() {
        return Metin;
    }

    public String getTarih() {
        return Tarih;
    }

    public void setTarih(String tarih) {
        Tarih = tarih;
    }

    public void setMetin(String metin) {
        Metin = metin;
    }

    public void setKategori(int kategori) {
        Kategori = kategori;
    }
}
