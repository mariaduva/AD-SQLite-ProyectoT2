package com.example.proyectobadt2_maraduque;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.proyectobadt2_maraduque.dao.PaisesDao;
import com.example.proyectobadt2_maraduque.dao.TerremotoDao;
import com.example.proyectobadt2_maraduque.data.ListadoPaisesAf;
import com.example.proyectobadt2_maraduque.data.ListadoTerremotos;
import com.example.proyectobadt2_maraduque.db.TerremotosDB;
import com.example.proyectobadt2_maraduque.dialog.FilterDialog;
import com.example.proyectobadt2_maraduque.dialog.OnFilterListener;
import com.example.proyectobadt2_maraduque.entity.PaisAfectado;
import com.example.proyectobadt2_maraduque.entity.Terremoto;

import java.util.ArrayList;

public class ConsultActivity extends AppCompatActivity implements View.OnClickListener, OnFilterListener {

    private int year;
    private String month;
    private String country;
    Button btnFilter;
    ImageButton btnSearch;

    //DB
    TerremotosDB db;
    PaisesDao pDao;
    TerremotoDao tDao;
    ArrayList<Terremoto> earthquakes;
    ArrayList<PaisAfectado> affectedCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        btnFilter = findViewById(R.id.btn_filter);
        btnSearch = findViewById(R.id.ibtn_search);

        btnFilter.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        //loadDB();

    }

    private void loadDB() {
        db = TerremotosDB.getDatabase(this);
        pDao = db.paisesDao();
        tDao = db.terremotoDao();
        earthquakes = (ArrayList<Terremoto>) tDao.getAll();
        affectedCountries = (ArrayList<PaisAfectado>) pDao.getAll();

        if(earthquakes.size() == 0){
            for (Terremoto t : new ListadoTerremotos().getListTerremotos()) {
                tDao.insert(t);
            }
        }

        if(affectedCountries.size() == 0){
            for (PaisAfectado p : new ListadoPaisesAf().getListadoPaisesAf()) {
                pDao.insert(p);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_filter) {
            filter();
        } else if (v.getId() == R.id.ibtn_search) {
            search();
        }
    }

    private void search() {
    }

    private void filter() {
        FilterDialog fd = new FilterDialog();
        fd.setCancelable(false);
        fd.show(getSupportFragmentManager(), "FilterDialog");
    }

    public void OnAceptarFilterListener(String fMonth, int fYear, String fCountry) {
        month = fMonth;
        year = fYear;
        country = fCountry;
    }
}