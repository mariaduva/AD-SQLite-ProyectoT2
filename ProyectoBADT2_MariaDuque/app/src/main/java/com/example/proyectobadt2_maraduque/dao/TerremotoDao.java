package com.example.proyectobadt2_maraduque.dao;

import androidx.room.Query;

import com.example.proyectobadt2_maraduque.entity.Terremoto;

import java.util.List;

public interface TerremotoDao {

    @Query("SELECT * FROM TERREMOTOS")
    public List<Terremoto> getAll();
}
