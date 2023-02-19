package com.example.proyectobadt2_maraduque.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "TERREMOTOS",
        indices = {@Index(value = {"nombre_dis"}, unique = true)})
public class Terremoto {
    @PrimaryKey
    @ColumnInfo(name = "fecha_hora")
    public String fecha;

    @ColumnInfo(name = "nombre_dis")
    public String nombre;

    @ColumnInfo(name = "magnitud")
    public double magnitud;

    @ColumnInfo(name = "coordenadas")
    public String coordenadas;

    @ColumnInfo(name = "lugar")
    public String lugar;

    @ColumnInfo(name = "muertos")
    public int muertos;

    public Terremoto(String fecha, String nombre, double magnitud, String coordenadas, String lugar, int muertos) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.magnitud = magnitud;
        this.coordenadas = coordenadas;
        this.lugar = lugar;
        this.muertos = muertos;
    }

}