package com.gomara.UI;

import com.gomara.Prosecer.Alumno;

import java.util.ArrayList;

public interface GestionInasistenciasView {

    void getAnioCurso();
    void getListAlumno(String anio,String curso);
    void updateAlumnos(String anio,String curso,ArrayList<Alumno> alumnos);

    void showAnioCurso(String anio,String curso);
    void showListAlumno(ArrayList<Alumno> alumnos);
    void onSuccessUpdate(String mens);
    void onFailueUpdate(String e);
}
