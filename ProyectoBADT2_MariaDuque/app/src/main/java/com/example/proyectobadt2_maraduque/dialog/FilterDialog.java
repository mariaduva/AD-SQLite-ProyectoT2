package com.example.proyectobadt2_maraduque.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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
import com.example.proyectobadt2_maraduque.dao.CountryDao;
import com.example.proyectobadt2_maraduque.db.EarthquakesDB;
import com.google.android.material.snackbar.Snackbar;

public class FilterDialog extends DialogFragment {
    private static final String VALIDTE_YEAR = "^[12][0-9]{3}$";
    private static final int CURRENT_YEAR = 2023;
    private static final ArrayList<String> MONTHS = new ArrayList<String>() {{
        add("Ninguno");
        add("enero");
        add("febrero");
        add("marzo");
        add("abril");
        add("mayo");
        add("junio");
        add("julio");
        add("agosto");
        add("septiembre");
        add("octubre");
        add("noviembre");
        add("diciembre");
    }};

    int year = 0;
    ArrayList<String> countries;
    ArrayAdapter adapterMonth, adapterCountry;
    OnFilterListener listener;

    //Components
    EditText etYear;
    Spinner spnMonth, spnCountry;
    Button btnClear;

    //DB
    EarthquakesDB db;
    CountryDao pDao;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.filter_dialog, null);

        etYear = v.findViewById(R.id.etYear);
        spnMonth = v.findViewById(R.id.spnMonth);
        spnCountry = v.findViewById(R.id.spnCountry);
        btnClear = v.findViewById(R.id.btnClear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etYear.setText("");
                spnMonth.setSelection(0);
                spnCountry.setSelection(0);
            }
        });

        adapterMonth = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, MONTHS);
        spnMonth.setAdapter(adapterMonth);

        db = EarthquakesDB.getDatabase(FilterDialog.this.getContext());
        pDao = db.countryDao();
        countries = removeAndSort((ArrayList<String>) pDao.getAllCountries());

        countries.add(0, "Ninguno");
        adapterCountry = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, countries);
        spnCountry.setAdapter(adapterCountry);

        etYear.setText(ConsultActivity.year == 0 ? "" : String.valueOf(ConsultActivity.year));
        spnMonth.setSelection(getPosition(ConsultActivity.month, MONTHS));
        spnCountry.setSelection(getPosition(ConsultActivity.country, countries));

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
                        if (etYear.getText().toString().isEmpty()){
                            year = 0;
                            listener.OnAceptarFilterListener(spnMonth.getSelectedItem().toString(), year, spnCountry.getSelectedItem().toString());
                            dialogInterface.dismiss();
                        } else {
                            year = Integer.parseInt(etYear.getText().toString());
                            if (!etYear.getText().toString().matches(VALIDTE_YEAR) || year > CURRENT_YEAR){
                                Snackbar.make(btn, R.string.error_invalid_year, Snackbar.LENGTH_LONG).show();
                            } else {
                                listener.OnAceptarFilterListener(spnMonth.getSelectedItem().toString(), year, spnCountry.getSelectedItem().toString());
                                dialogInterface.dismiss();
                            }
                        }
                    }
                });
            }
        });
        return ad;
    }

    private int getPosition(String month, ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(month)) {
                return i;
            }
        }
        return 0;
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
