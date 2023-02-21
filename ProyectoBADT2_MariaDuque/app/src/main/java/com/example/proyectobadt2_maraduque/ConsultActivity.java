package com.example.proyectobadt2_maraduque;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectobadt2_maraduque.dao.CountryDao;
import com.example.proyectobadt2_maraduque.dao.EarthquakeDao;
import com.example.proyectobadt2_maraduque.data.AfCountryList;
import com.example.proyectobadt2_maraduque.data.EarthquakeList;
import com.example.proyectobadt2_maraduque.db.EarthquakesDB;
import com.example.proyectobadt2_maraduque.dialog.FilterDialog;
import com.example.proyectobadt2_maraduque.dialog.OnFilterListener;
import com.example.proyectobadt2_maraduque.entity.AffectedCountry;
import com.example.proyectobadt2_maraduque.entity.Earthquake;
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
    EarthquakesDB db;
    CountryDao pDao;
    EarthquakeDao tDao;
    ArrayList<Earthquake> earthquakes;
    ArrayList<AffectedCountry> affectedCountries;

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

    private void loadRV(ArrayList<Earthquake> earthquakes) {
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
        db = EarthquakesDB.getDatabase(this);
        pDao = db.countryDao();
        tDao = db.earthquakeDao();
        earthquakes = (ArrayList<Earthquake>) tDao.getAll();
        affectedCountries = (ArrayList<AffectedCountry>) pDao.getAll();

        if(earthquakes.size() == 0){
            for (Earthquake t : new EarthquakeList().getEarthquakeList()) {
                tDao.insert(t);
            }
            earthquakes = (ArrayList<Earthquake>) tDao.getAll();
        }

        loadRV(earthquakes);

        if(affectedCountries.size() == 0){
            for (AffectedCountry p : new AfCountryList().getAfCountriesList()) {
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
        String filtersYearMonth = getFiltersYearMonth();

        if (filtersYearMonth.equals("%-%-%")) {
            earthquakes = (ArrayList<Earthquake>) tDao.getAll();
        } else {
            earthquakes = (ArrayList<Earthquake>) tDao.selectByMonthYear(filtersYearMonth);
        }

        if (!country.equals("Ninguno")) {
            affectedCountries = (ArrayList<AffectedCountry>) pDao.selectByCountry(country);
            earthquakes = getCoindicences(earthquakes, affectedCountries);
        }

        if (earthquakes.size() == 0) {
            Toast.makeText(this, R.string.no_coincidences, Toast.LENGTH_SHORT).show();
        }
        loadRV(earthquakes);
    }

    @NonNull
    private String getFiltersYearMonth() {
        String filtersYearMonth = "%-";

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
        return filtersYearMonth;
    }

    private ArrayList<Earthquake> getCoindicences(ArrayList<Earthquake> earthquakes, ArrayList<AffectedCountry> affectedCountries) {
        ArrayList<Earthquake> coincidences = new ArrayList<>();
        for (Earthquake t : earthquakes) {
            for (AffectedCountry p : affectedCountries) {
                if (t.date.equals(p.date)) {
                    coincidences.add(t);
                }
            }
        }
        return coincidences;
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