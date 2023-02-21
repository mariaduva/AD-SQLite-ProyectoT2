package com.example.proyectobadt2_maraduque.data;

import com.example.proyectobadt2_maraduque.entity.AffectedCountry;

import java.util.ArrayList;

public class AfCountryList {

    private static ArrayList<AffectedCountry> AfCountriesList;

    public AfCountryList() {
        AfCountriesList = new ArrayList<AffectedCountry>();

        AfCountriesList.add(new AffectedCountry("22-mayo-1960, 15:11", "Chile"));
        AfCountriesList.add(new AffectedCountry("26-diciembre-2004, 07:58", "Indonesia"));
        AfCountriesList.add(new AffectedCountry("27-marzo-1964, 17:36", "Estados Unidos"));
        AfCountriesList.add(new AffectedCountry("11-marzo-2011, 14:46", "Japón"));
        AfCountriesList.add(new AffectedCountry("4-noviembre-1952, 16:58", "Rusia"));
        AfCountriesList.add(new AffectedCountry("13-agosto-1868, 21:30", "Chile"));
        AfCountriesList.add(new AffectedCountry("28-octubre-1746, 22:30", "Perú"));
        AfCountriesList.add(new AffectedCountry("26-enero-1700, 21:30", "Estados Unidos"));
        AfCountriesList.add(new AffectedCountry("27-febrero-2010, 03:34", "Chile"));
        AfCountriesList.add(new AffectedCountry("6-febrero-2023, 01:17", "Turquía"));
        AfCountriesList.add(new AffectedCountry("6-febrero-2023, 01:17", "Siria"));
        AfCountriesList.add(new AffectedCountry("31-enero-1906, 15:36", "Ecuador"));
        AfCountriesList.add(new AffectedCountry("31-enero-1906, 15:36", "Colombia"));
        AfCountriesList.add(new AffectedCountry("25-noviembre-1833, 20:00", "Indonesia"));
        AfCountriesList.add(new AffectedCountry("1-noviembre-1755, 10:16", "Portugal"));
        AfCountriesList.add(new AffectedCountry("8-julio-1730, 04:45", "Chile"));
        AfCountriesList.add(new AffectedCountry("11-abril-2012, 15:38", "Indonesia"));
        AfCountriesList.add(new AffectedCountry("28-marzo-2005, 23:09", "Indonesia"));
        AfCountriesList.add(new AffectedCountry("9-marzo-1957, 14:22", "Estados Unidos"));
        AfCountriesList.add(new AffectedCountry("15-agosto-1950", "India"));
        AfCountriesList.add(new AffectedCountry("15-agosto-1950", "China"));
        AfCountriesList.add(new AffectedCountry("10-noviembre-1922, 23:53", "Argentina"));
        AfCountriesList.add(new AffectedCountry("28-marzo-1787, 11:30", "México"));
        AfCountriesList.add(new AffectedCountry("3-febrero-1923, 04:58", "Rusia"));
        AfCountriesList.add(new AffectedCountry("20-octubre-1687, 09:15", "Perú"));
        AfCountriesList.add(new AffectedCountry("16-diciembre-1575, 14:30", "Chile"));
        AfCountriesList.add(new AffectedCountry("16-septiembre-2015, 19:54", "Chile"));
        AfCountriesList.add(new AffectedCountry("23-junio-2001, 15:33", "Perú"));

    }

    public ArrayList<AffectedCountry> getAfCountriesList() {
        return AfCountriesList;
    }
}
