package com.example.proyectobadt2_maraduque.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectobadt2_maraduque.dao.CountryDao;
import com.example.proyectobadt2_maraduque.dao.EarthquakeDao;
import com.example.proyectobadt2_maraduque.entity.AffectedCountry;
import com.example.proyectobadt2_maraduque.entity.Earthquake;

@Database(entities = {Earthquake.class, AffectedCountry.class}, version = 1)
public abstract class EarthquakesDB extends RoomDatabase {

    public abstract EarthquakeDao earthquakeDao();
    public abstract CountryDao countryDao();
    private static EarthquakesDB terremotoDB;

    public static EarthquakesDB getDatabase(Context context) {
        if (terremotoDB == null) {
            terremotoDB = Room.databaseBuilder(
                            context.getApplicationContext(), EarthquakesDB.class, "EARTHQUAKES_DB")
                    .allowMainThreadQueries().build();
        }
        return terremotoDB;
    }
}
