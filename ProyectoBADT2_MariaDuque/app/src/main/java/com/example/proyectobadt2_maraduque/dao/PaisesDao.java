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

    @Insert
    public long insert(PaisAfectado paisAfectado);
}
