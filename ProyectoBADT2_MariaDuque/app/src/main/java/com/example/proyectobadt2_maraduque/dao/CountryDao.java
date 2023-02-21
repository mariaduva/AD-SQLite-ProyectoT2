package com.example.proyectobadt2_maraduque.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectobadt2_maraduque.entity.AffectedCountry;

import java.util.List;
@Dao
public interface CountryDao {

    @Query("SELECT * FROM AF_COUNTRIES")
    public List<AffectedCountry> getAll();

    @Query("SELECT country FROM AF_COUNTRIES")
    public List<String> getAllCountries();

    @Query("SELECT * FROM AF_COUNTRIES WHERE country = :country")
    public List<AffectedCountry> selectByCountry(String country);

    @Insert
    public long insert(AffectedCountry affectedCountry);
}
