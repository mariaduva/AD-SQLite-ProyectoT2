package com.example.proyectobadt2_maraduque;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ConsultActivity extends AppCompatActivity implements View.OnClickListener{

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

    }
}