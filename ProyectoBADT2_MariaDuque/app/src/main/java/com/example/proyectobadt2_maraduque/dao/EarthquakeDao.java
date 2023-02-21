package com.example.proyectobadt2_maraduque.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectobadt2_maraduque.entity.Earthquake;

import java.util.List;
@Dao
public interface EarthquakeDao {

    @Query("SELECT * FROM EARTHQUAKES")
    public List<Earthquake> getAll();

    @Query("SELECT * FROM EARTHQUAKES WHERE date_time LIKE :date_time")
    public List<Earthquake> selectByMonthYear(String date_time);

    @Insert
    public long insert(Earthquake terremoto);
}
