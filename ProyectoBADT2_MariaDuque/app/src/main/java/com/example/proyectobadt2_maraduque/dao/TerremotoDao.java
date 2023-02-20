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

    @Insert
    public long insert(Terremoto terremoto);
}
