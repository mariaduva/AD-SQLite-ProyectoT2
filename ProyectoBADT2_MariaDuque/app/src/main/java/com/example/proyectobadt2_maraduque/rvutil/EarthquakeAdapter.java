package com.example.proyectobadt2_maraduque.rvutil;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectobadt2_maraduque.R;
import com.example.proyectobadt2_maraduque.entity.Terremoto;

import java.util.ArrayList;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeVH> {

    private ArrayList<Terremoto> data;

    public EarthquakeAdapter(ArrayList<Terremoto> data){
        this.data = data;
    }

    @NonNull
    @Override
    public EarthquakeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.earthquake_item, parent, false);
        return new EarthquakeVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeVH holder, int position) {
        holder.bindData(holder.itemView.getContext(), data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class EarthquakeVH extends RecyclerView.ViewHolder{
        TextView tvName, tvMagnitude, tvPlace, tvDate, tvCoordinates, tvDeaths;

        public EarthquakeVH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameEarthquake);
            tvMagnitude = itemView.findViewById(R.id.tvMagnitudeEarthquake);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvDate = itemView.findViewById(R.id.tvDateEarthquake);
            tvCoordinates = itemView.findViewById(R.id.tvCoordinatesEpi);
            tvDeaths = itemView.findViewById(R.id.tvDeaths);
        }

        public void bindData(Context context, Terremoto earthquake) {
            tvName.setText(earthquake.nombre);
            tvMagnitude.setText(String.valueOf(earthquake.magnitud));
            tvPlace.setText(String.format(context.getString(R.string.tv_place_it), earthquake.lugar));
            tvDate.setText(String.format(context.getString(R.string.tv_date_it), earthquake.fecha));
            tvCoordinates.setText(String.format(context.getString(R.string.tv_coordinates_epi_it), earthquake.coordenadas));
            tvDeaths.setText(String.format(context.getString(R.string.tv_deaths_it), earthquake.muertos));
        }
    }
}
