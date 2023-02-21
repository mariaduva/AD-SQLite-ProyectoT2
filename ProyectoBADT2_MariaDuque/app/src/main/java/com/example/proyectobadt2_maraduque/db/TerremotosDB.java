package com.example.proyectobadt2_maraduque.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectobadt2_maraduque.dao.PaisesDao;
import com.example.proyectobadt2_maraduque.dao.TerremotoDao;
import com.example.proyectobadt2_maraduque.entity.PaisAfectado;
import com.example.proyectobadt2_maraduque.entity.Terremoto;

@Database(entities = {Terremoto.class, PaisAfectado.class}, version = 1)
public abstract class TerremotosDB extends RoomDatabase {

    public abstract TerremotoDao terremotoDao();
    public abstract PaisesDao paisesDao();
    private static TerremotosDB terremotoDB;

    public static TerremotosDB getDatabase(Context context) {
        if (terremotoDB == null) {
            terremotoDB = Room.databaseBuilder(
                            context.getApplicationContext(), TerremotosDB.class, "TERREMOTOS_DB")
                    .allowMainThreadQueries().build();
        }
        return terremotoDB;
    }
}
