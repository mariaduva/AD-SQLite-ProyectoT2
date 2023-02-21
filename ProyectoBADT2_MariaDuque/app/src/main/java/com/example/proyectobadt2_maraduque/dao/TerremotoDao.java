package com.example.proyectobadt2_maraduque.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectobadt2_maraduque.entity.Terremoto;

import java.util.List;
@Dao
public interface TerremotoDao {

    @Query("SELECT * FROM TERREMOTOS")
    public List<Terremoto> getAll();


    /*
    María del futuro no se si te sirva esto, pero lo dejo por si acaso
    @Query("SELECT * FROM TERREMOTOS WHERE SUBSTR(fecha_hora, 1, 10) = :fecha_hora")
    public Terremoto selectByDay(String fecha_hora);

    SELECT * FROM TERREMOTOS WHERE fecha_hora LIKE '2023-%-%'

    @Query("SELECT * FROM TERREMOTOS WHERE SUBSTR(fecha_hora, 1, 7) = :fecha_hora")
    public Terremoto selectByMonthYear(String fecha_hora);

    @Query("SELECT * FROM TERREMOTOS WHERE SUBSTR(fecha_hora, 8, 12) = :fecha_hora")
    public Terremoto selectByYear(String fecha_hora);

    @Query("SELECT * FROM TERREMOTOS WHERE SUBSTR(fecha_hora, 1, 4) = :fecha_hora")
    public Terremoto selectByMonth(String fecha_hora);*/

    @Insert
    public long insert(Terremoto terremoto);
}
