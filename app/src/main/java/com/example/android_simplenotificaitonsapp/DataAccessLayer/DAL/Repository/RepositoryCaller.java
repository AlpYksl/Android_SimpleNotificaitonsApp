package com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Repository;

import android.content.Context;

import java.util.ArrayList;

public class RepositoryCaller
{
    private static RepositoryCaller rc = null;
    private Context context;

    private ArrayList<IRepository> repositories;
    private RepositoryCaller(Context context){
        repositories = new ArrayList<IRepository>();
        this.context = context;
    }
    public static RepositoryCaller create(Context context){
        if(rc == null){
            rc = new RepositoryCaller(context);
        }
        return rc;
    }
    public IRepository getRepository(int repName){
        IRepository ir =null;
        switch (repName){
            case RepositoryNames.NOTIFICATIONS:
                ir = new HatirlatmaRepository(context);
                break;
            case RepositoryNames.CATEGORY:
                ir = new KategoriRepository(context);
                break;
        }
        return ir;
    }
}
