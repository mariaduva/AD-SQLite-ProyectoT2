package com.example.proyectobadt2_maraduque.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "AF_COUNTRIES",
        primaryKeys = { "c_date_time", "country" },
        foreignKeys = {
                @ForeignKey(
                        entity = Earthquake.class,
                        parentColumns = "date_time",
                        childColumns = "c_date_time",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class AffectedCountry {
    @NonNull
    @ColumnInfo(name = "c_date_time")
    public String date;

    @NonNull
    @ColumnInfo(name = "country")
    public String country;

    public AffectedCountry(String date, String country) {
        this.date = date;
        this.country = country;
    }
}
