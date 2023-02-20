package com.example.proyectobadt2_maraduque.dialog;

import java.util.ArrayList;
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

import com.example.proyectobadt2_maraduque.R;
import com.google.android.material.snackbar.Snackbar;

public class FilterDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {

    private static final String VALIDTE_YEAR = "^[12][0-9]{3}$";
    private static final ArrayList<String> MONTHS = new ArrayList<String>() {{
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

    OnFilterListener listener;
    EditText etYear;
    Spinner spnMonth, spnCountry;

    ArrayAdapter adapterMonth, adapterCountry;

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
                        Pattern regex = Pattern.compile(VALIDTE_YEAR);

                        // TODO: Cargar los spinner con los datos de la base de datos
                        String month, country;
                        month = "Enero";
                        country = "España";

                        int year = Integer.parseInt(etYear.getText().toString());
                        if (regex.matcher(etYear.getText().toString()).matches() || etYear.getText().toString().isEmpty()){
                            //listener.OnAceptarFilterListener(spnMonth.getSelectedItem().toString(), year,  spnCountry.getSelectedItem().toString());
                            listener.OnAceptarFilterListener(month, year, country);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
