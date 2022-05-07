package com.gomara.adapter;

import com.gomara.UI.AlumnoView;

import java.util.ArrayList;

public class getAlumnoView implements AlumnoView {

    private ArrayList<Alumno> allAlumnos = new ArrayList<>();

    @Override
    public void setAllAlumnos(Alumno alumnos) {
        allAlumnos.add(alumnos);
    }

    @Override
    public ArrayList<Alumno> getAllAlumnos() {
        return allAlumnos;
    }

}
