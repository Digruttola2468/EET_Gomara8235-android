package com.gomara.adapter;

import android.util.Log;

import com.gomara.UI.sendInfo;

import java.util.ArrayList;

public class getInfo implements sendInfo {

    ArrayList<Alumno> allAlumnos = new ArrayList<>();

    @Override
    public void getAlumnos(Alumno alumnos) {
        allAlumnos.add(alumnos);
    }

    @Override
    public ArrayList<Alumno> getAllAlumnos() {
        return allAlumnos;
    }

}
