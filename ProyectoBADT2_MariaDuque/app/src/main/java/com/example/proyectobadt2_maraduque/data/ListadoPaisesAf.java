package com.example.proyectobadt2_maraduque.data;

import com.example.proyectobadt2_maraduque.entity.PaisAfectado;

import java.util.ArrayList;

public class ListadoPaisesAf {

    private static ArrayList<PaisAfectado> paisesAf;

    public ListadoPaisesAf() {
        paisesAf = new ArrayList<PaisAfectado>();

        paisesAf.add(new PaisAfectado("22-mayo-1960, 15:11", "Chile"));
        paisesAf.add(new PaisAfectado("26-diciembre-2004, 07:58", "Indonesia"));
        paisesAf.add(new PaisAfectado("27-marzo-1964, 17:36", "Estados Unidos"));
        paisesAf.add(new PaisAfectado("11-marzo-2011, 14:46", "Japón"));
        paisesAf.add(new PaisAfectado("4-noviembre-1952, 16:58", "Rusia"));
        paisesAf.add(new PaisAfectado("13-agosto-1868, 21:30", "Chile"));
        paisesAf.add(new PaisAfectado("28-octubre-1746, 22:30", "Perú"));
        paisesAf.add(new PaisAfectado("26-enero-1700, 21:30", "Estados Unidos"));
        paisesAf.add(new PaisAfectado("27-febrero-2010, 03:34", "Chile"));
        paisesAf.add(new PaisAfectado("6-febrero-2023, 01:17", "Turquía"));
        paisesAf.add(new PaisAfectado("6-febrero-2023, 01:17", "Siria"));
        paisesAf.add(new PaisAfectado("31-enero-1906, 15:36", "Ecuador"));
        paisesAf.add(new PaisAfectado("31-enero-1906, 15:36", "Colombia"));
        paisesAf.add(new PaisAfectado("25-noviembre-1833, 20:00", "Indonesia"));
        paisesAf.add(new PaisAfectado("1-noviembre-1755, 10:16", "Portugal"));
        paisesAf.add(new PaisAfectado("8-julio-1730, 04:45", "Chile"));
        paisesAf.add(new PaisAfectado("11-abril-2012, 15:38", "Indonesia"));
        paisesAf.add(new PaisAfectado("28-marzo-2005, 23:09", "Indonesia"));
        paisesAf.add(new PaisAfectado("9-marzo-1957, 14:22", "Estados Unidos"));
        paisesAf.add(new PaisAfectado("15-agosto-1950", "India"));
        paisesAf.add(new PaisAfectado("15-agosto-1950", "China"));
        paisesAf.add(new PaisAfectado("10-noviembre-1922, 23:53", "Argentina"));
        paisesAf.add(new PaisAfectado("28-marzo-1787, 11:30", "México"));
        paisesAf.add(new PaisAfectado("3-febrero-1923, 04:58", "Rusia"));
        paisesAf.add(new PaisAfectado("20-octubre-1687, 09:15", "Perú"));
        paisesAf.add(new PaisAfectado("16-diciembre-1575, 14:30", "Chile"));
        paisesAf.add(new PaisAfectado("16-septiembre-2015, 19:54", "Chile"));
        paisesAf.add(new PaisAfectado("23-junio-2001, 15:33", "Perú"));

    }

    public ArrayList<PaisAfectado> getListadoPaisesAf() {
        return paisesAf;
    }
}
