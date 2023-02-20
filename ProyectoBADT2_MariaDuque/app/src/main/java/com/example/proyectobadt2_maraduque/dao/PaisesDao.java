package com.example.proyectobadt2_maraduque.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectobadt2_maraduque.entity.PaisAfectado;

import java.util.List;
@Dao
public interface PaisesDao {

    @Query("SELECT * FROM PAISES_AFECTADOS")
    public List<PaisAfectado> getAll();

    @Query("SELECT pais FROM PAISES_AFECTADOS")
    public List<String> getAllCountries();

    @Query("SELECT * FROM PAISES_AFECTADOS WHERE pais = :pais")
    public List<PaisAfectado> selectByCountry(String pais);

    @Insert
    public long insert(PaisAfectado paisAfectado);
}
