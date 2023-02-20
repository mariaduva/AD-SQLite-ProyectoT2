package com.example.proyectobadt2_maraduque.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.proyectobadt2_maraduque.ConsultActivity;
import com.example.proyectobadt2_maraduque.R;
import com.example.proyectobadt2_maraduque.dao.PaisesDao;
import com.example.proyectobadt2_maraduque.db.TerremotosDB;
import com.example.proyectobadt2_maraduque.entity.PaisAfectado;
import com.example.proyectobadt2_maraduque.entity.Terremoto;
import com.google.android.material.snackbar.Snackbar;

public class FilterDialog extends DialogFragment {

    private static final String VALIDTE_YEAR = "^[12][0-9]{3}$";
    private static final ArrayList<String> MONTHS = new ArrayList<String>() {{
        add("Ninguno");
        add("Enero");
        add("Febrero");
        add("Marzo");
        add("Abril");
        add("Mayo");
        add("Junio");
        add("Julio");
        add("Agosto");
        add("Septiembre");
        add("Octubre");
        add("Noviembre");
        add("Diciembre");
    }};
    ArrayList<String> countries;
    ArrayAdapter adapterMonth, adapterCountry;
    OnFilterListener listener;

    //Components
    EditText etYear;
    Spinner spnMonth, spnCountry;

    //DB
    TerremotosDB db;
    PaisesDao pDao;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.filter_dialog, null);

        etYear = v.findViewById(R.id.etYear);
        spnMonth = v.findViewById(R.id.spnMonth);
        spnMonth.setPrompt("Mes");
        spnCountry = v.findViewById(R.id.spnCountry);

        adapterMonth = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, MONTHS);
        spnMonth.setAdapter(adapterMonth);

        db = TerremotosDB.getDatabase(FilterDialog.this.getContext());
        pDao = db.paisesDao();
        countries = removeAndSort((ArrayList<String>) pDao.getAllCountries());
        countries.add(0, "Ninguno");
        adapterCountry = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, countries);
        spnCountry.setAdapter(adapterCountry);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        builder.setTitle(R.string.title_dialog)
                .setPositiveButton(R.string.btn_aceptar, null)
                .setNegativeButton(R.string.btn_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });

        AlertDialog ad = builder.create();
        ad.setCanceledOnTouchOutside(false);

        ad.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btn = ((AlertDialog) dialogInterface).getButton(DialogInterface.BUTTON_POSITIVE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int year = Integer.parseInt(etYear.getText().toString());

                        if (etYear.getText().toString().matches(VALIDTE_YEAR) || etYear.getText().toString().isEmpty()){
                            listener.OnAceptarFilterListener(spnMonth.getSelectedItem().toString(), year, spnCountry.getSelectedItem().toString());
                            dialogInterface.dismiss();
                        } else {
                            Snackbar.make(btn, R.string.error_invalid_year, Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        return ad;
    }

    private ArrayList<String> removeAndSort(ArrayList<String> allCountries) {
        HashSet<String> set = new HashSet<>(allCountries);
        ArrayList<String> sortedCountries = new ArrayList<>(set);
        Collections.sort(sortedCountries);
        return sortedCountries;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFilterListener) {
            listener = (OnFilterListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSizeListener");
        }
    }

    @Override
    public void onDetach() {
        if (listener != null) {
            listener = null;
        }
        super.onDetach();
    }
}
