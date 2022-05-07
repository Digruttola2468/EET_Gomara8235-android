package com.gomara.UI;

import com.gomara.adapter.Alumno;

import java.util.ArrayList;

public interface AlumnoView {
    void setAllAlumnos(Alumno alumnos);

    ArrayList<Alumno> getAllAlumnos();
}
