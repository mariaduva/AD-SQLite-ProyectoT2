package com.example.proyectobadt2_maraduque.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "PAISES_AFECTADOS",
        primaryKeys = { "c_fecha_hora", "pais" },
        foreignKeys = {
                @ForeignKey(
                        entity = Terremoto.class,
                        parentColumns = "fecha_hora",
                        childColumns = "c_fecha_hora",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class PaisAfectado {
    @NonNull
    @ColumnInfo(name = "c_fecha_hora")
    public String fecha;

    @NonNull
    @ColumnInfo(name = "pais")
    public String pais;

    public PaisAfectado(String fecha, String pais) {
        this.fecha = fecha;
        this.pais = pais;
    }
}
