package com.example.proyectobadt2_maraduque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.proyectobadt2_maraduque.dao.PaisesDao;
import com.example.proyectobadt2_maraduque.dao.TerremotoDao;
import com.example.proyectobadt2_maraduque.data.ListadoPaisesAf;
import com.example.proyectobadt2_maraduque.data.ListadoTerremotos;
import com.example.proyectobadt2_maraduque.db.TerremotosDB;
import com.example.proyectobadt2_maraduque.dialog.FilterDialog;
import com.example.proyectobadt2_maraduque.dialog.OnFilterListener;
import com.example.proyectobadt2_maraduque.entity.PaisAfectado;
import com.example.proyectobadt2_maraduque.entity.Terremoto;
import com.example.proyectobadt2_maraduque.rvutil.EarthquakeAdapter;

import java.util.ArrayList;

public class ConsultActivity extends AppCompatActivity implements View.OnClickListener, OnFilterListener {

    public static int year = 0;
    public static String month = "Ninguno";
    public static String country = "Ninguno";

    //Components
    Button btnFilter;
    ImageButton btnSearch;
    TextView tvFilters;

    //Recycler View
    RecyclerView rv;
    LinearLayoutManager llm;
    EarthquakeAdapter adapter;

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

        rv = findViewById(R.id.rvEarthquakes);

        btnFilter = findViewById(R.id.btn_filter);
        btnSearch = findViewById(R.id.ibtn_search);
        tvFilters = findViewById(R.id.tvFiltersSlct);

        btnFilter.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        loadDB();

        updateFilters();
    }

    private void loadRV(ArrayList<Terremoto> earthquakes) {
        llm = new LinearLayoutManager(this);
        adapter = new EarthquakeAdapter(this.earthquakes);

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    private void updateFilters() {
        String filters = "";

        if (!month.equals("Ninguno")) {
            filters += "Mes: " + month;
        }

        if (!country.equals("Ninguno")) {
            if (!filters.isEmpty()) {
                filters += " ";
            }
            filters += "País: " + country;
        }

        if (year != 0) {
            if (!filters.isEmpty()) {
                filters += " ";
            }
            filters += "Año: " + year;
        }

        if (filters.isEmpty()) {
            filters = "Ninguno";
        }

        tvFilters.setText(filters);
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
        earthquakes = (ArrayList<Terremoto>) tDao.getAll();
        affectedCountries = (ArrayList<PaisAfectado>) pDao.getAll();
        loadRV(earthquakes);
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
        //"%-Feb-2002%" -> Example
        String filtersYearMonth = "%-";
        if (!country.equals("Ninguno")) {
            affectedCountries = (ArrayList<PaisAfectado>) pDao.selectByCountry(country);
        }

        if(!month.equals("Ninguno")){
            filtersYearMonth += month + "-";
        } else {
            filtersYearMonth += "%-";
        }

        if(year != 0){
            filtersYearMonth += year + "%";
        } else {
            filtersYearMonth += "%";
        }

        if (filtersYearMonth.equals("%-%-%")) {
            earthquakes = (ArrayList<Terremoto>) tDao.getAll();
        } else {
            earthquakes = (ArrayList<Terremoto>) tDao.selectByMonthYear(filtersYearMonth);
        }
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
        updateFilters();
    }
}