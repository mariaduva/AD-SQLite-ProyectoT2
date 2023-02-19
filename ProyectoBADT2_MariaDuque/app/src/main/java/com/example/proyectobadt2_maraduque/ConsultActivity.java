package com.example.proyectobadt2_maraduque;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.proyectobadt2_maraduque.dialog.FilterDialog;
import com.example.proyectobadt2_maraduque.dialog.OnFilterListener;

public class ConsultActivity extends AppCompatActivity implements View.OnClickListener, OnFilterListener {

    private int year;
    private String month;
    private String country;
    Button btnFilter;
    ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        btnFilter = findViewById(R.id.btn_filter);
        btnSearch = findViewById(R.id.ibtn_search);

        btnFilter.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
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