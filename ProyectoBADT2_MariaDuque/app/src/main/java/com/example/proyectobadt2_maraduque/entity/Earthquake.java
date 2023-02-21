package com.example.proyectobadt2_maraduque.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "EARTHQUAKES",
        indices = {@Index(value = {"name_e"}, unique = true)})
public class Earthquake {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date_time")
    public String date;

    @ColumnInfo(name = "name_e")
    public String name;

    @ColumnInfo(name = "magnitude")
    public double magnitude;

    @ColumnInfo(name = "coordinates")
    public String coordinates;

    @ColumnInfo(name = "place")
    public String place;

    @ColumnInfo(name = "deaths")
    public String deaths;

    public Earthquake(String date, double magnitude, String name, String place, String coordinates, String deaths) {
        this.date = date;
        this.magnitude = magnitude;
        this.name = name;
        this.place = place;
        this.coordinates = coordinates;
        this.deaths = deaths;
    }
}
