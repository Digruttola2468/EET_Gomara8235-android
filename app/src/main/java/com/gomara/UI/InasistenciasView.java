package com.gomara.UI;

import com.gomara.Prosecer.Alumno;

import java.util.ArrayList;

public interface InasistenciasView {
    void showInasistencias(ArrayList<Alumno> allAlumnos);
    void getInasistencias(String anio,String curso);

    void showAnioCurso(String anio,String curso);
    void getAnioCurso(String uid);
}
