package com.example.proyectobadt2_maraduque.entity;

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

    @ColumnInfo(name = "c_fecha_hora")
    public String fecha;

    @ColumnInfo(name = "pais")
    public String país;

    public PaisAfectado(String fecha, String país) {
        this.fecha = fecha;
        this.país = país;
    }

}
