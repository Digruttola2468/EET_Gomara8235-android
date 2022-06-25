package com.gomara.Presenter;

import com.gomara.Prosecer.Alumno;

import java.util.ArrayList;

public interface InasistenciasPresenter {

    void showInasistencias(ArrayList<Alumno> allAlumnos);
    void getInasistencias(String anio,String curso);

    void showAnioCurso(String anio,String curso);
    void getAnioCurso(String uid);

}
