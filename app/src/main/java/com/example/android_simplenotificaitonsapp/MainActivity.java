package com.example.android_simplenotificaitonsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.EntityBase;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.HatirlatmaEntity;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Entities.KategoriEntity;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Repository.IRepository;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Repository.RepositoryCaller;
import com.example.android_simplenotificaitonsapp.DataAccessLayer.DAL.Repository.RepositoryNames;
import com.example.android_simplenotificaitonsapp.PresentationLayer.HatirlatmaAdapter;
import com.example.android_simplenotificaitonsapp.PresentationLayer.KategoriAdapter;

public class MainActivity extends AppCompatActivity {

    private LinearLayout pnlMain;
    private Spinner spnCategory, spnID;
    private EditText txtText,txtDate;
    private RepositoryCaller repositoryCaller;
    private IRepository repository;
    private boolean ins_udp_flag=false;
    private int cat_id = 0,sel_id=0; //Category Ve Selection ID
    private Button btnSave,btnDelete,btnClear;
    private void init(){
        repositoryCaller = RepositoryCaller.create(this);
        pnlMain = (LinearLayout) findViewById(R.id.pnlMain);
        spnCategory = (Spinner) findViewById(R.id.spnCategory);
        spnID = (Spinner) findViewById(R.id.spnID);
        txtText = (EditText) findViewById(R.id.txtText);
        txtDate = (EditText) findViewById(R.id.txtDate);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClear = (Button) findViewById(R.id.btnClear);
    }

    private void BuildCategories(){
        repository =repositoryCaller.getRepository(RepositoryNames.CATEGORY);
        if(repository.getCount()==0){
            repository.Add(new KategoriEntity(0,"Toplantı"));
            repository.Add(new KategoriEntity(0,"Eğlence"));
            repository.Add(new KategoriEntity(0,"Ders"));
            repository.Add(new KategoriEntity(0,"ÖzelGün"));
            repository.Add(new KategoriEntity(0,"DoğumGünü"));
        }
    }

    public void bindCategorySpinner()
    {
        repository = repositoryCaller.getRepository(RepositoryNames.CATEGORY);
        KategoriAdapter adp = new KategoriAdapter(this,repository.GetResult());
        spnCategory.setAdapter(adp);
    }
    public void BindtoHatirlatmaSpinner(){
        repository =repositoryCaller.getRepository(RepositoryNames.NOTIFICATIONS);
        HatirlatmaAdapter adp = new HatirlatmaAdapter(this,repository.GetResult());
        spnID.setAdapter(adp);
    }

    private void spnID_Selection()
    {
        spnID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tID = (TextView) view;
                sel_id= Integer.parseInt(tID.getText().toString());
                repository = repositoryCaller.getRepository(RepositoryNames.NOTIFICATIONS);
                DisplayEntity(repository.GetRecord(sel_id));
                ins_udp_flag = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void spnCategory_Selection()
    {
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout p = (LinearLayout) view;
                TextView tID = (TextView) p.getChildAt(0);
                cat_id = Integer.parseInt(tID.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void registerHandlers()
    {
        spnCategory_Selection();
        spnID_Selection();
    }

    private void DisplayEntity(EntityBase e)
    {
        HatirlatmaEntity he = (HatirlatmaEntity) e;
        txtDate.setText(he.getTarih());
        txtText.setText(he.getMetin());
        for(int i = 0; i<=spnCategory.getCount()-1; i++){
            KategoriEntity itm = (KategoriEntity) spnCategory.getItemAtPosition(i);
            if(he.getKategori()==itm.getID()){
                spnCategory.setSelection(i);
                break;
            }
        }
    }

    private EntityBase GetEntityFromScreen(int id){
        HatirlatmaEntity he = new HatirlatmaEntity(id,txtText.getText().toString(),cat_id,txtDate.getText().toString());
        return he;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            init();
            BuildCategories();
            bindCategorySpinner();
            BindtoHatirlatmaSpinner();
            registerHandlers();
        }catch(Exception e){
            Log.e("Activity:onCreate",e.getMessage());
        }
    }

    public void saveOrupdate_Click(View view)
    {
        repository = repositoryCaller.getRepository(RepositoryNames.NOTIFICATIONS);
        if(ins_udp_flag){
            repository.Add(GetEntityFromScreen(0));
            BindtoHatirlatmaSpinner();
        }
        else {
            repository.Update(GetEntityFromScreen(sel_id));
            BindtoHatirlatmaSpinner();
        }
    }

    public void delete_Click(View view) {
        if(!ins_udp_flag){
            repository.Delete(sel_id);
            BindtoHatirlatmaSpinner();
        }
    }

    public void clearFields_Click(View view) {
        ins_udp_flag =true;
        spnCategory.setSelection(0);
        for(int i = 0; i<=pnlMain.getChildCount()-1;++i){
            View v = pnlMain.getChildAt(i);
            if(v instanceof EditText){
                ((EditText) v).setText("");
            }
        }
    }
}
