package com.gomara.Presenter;

import com.gomara.Prosecer.Alumno;
import com.gomara.Server.GestionInasistenciasServer;
import com.gomara.Server.GestionInasistenciasServerImpl;
import com.gomara.ui.GestionInasistenciasView;

import java.util.ArrayList;

public class GestionInasistenciasImpl implements GestionInasistenciasPresenter{

    GestionInasistenciasServer server = new GestionInasistenciasServerImpl(this);
    GestionInasistenciasView view;

    public GestionInasistenciasImpl(GestionInasistenciasView view) {
        this.view = view;
    }

    @Override
    public void getAnioCurso() {
        server.getAnioCurso();
    }

    @Override
    public void getListAlumno(String anio, String curso) {
        server.getListAlumno(anio,curso);
    }

    @Override
    public void updateAlumnos(String anio, String curso, ArrayList<Alumno> alumnos) {
        server.updateAlumnos(anio,curso,alumnos);
    }

    @Override
    public void showAnioCurso(String anio, String curso) {
        view.showAnioCurso(anio,curso);
    }

    @Override
    public void showListAlumno(ArrayList<Alumno> alumnos) {
        view.showListAlumno(alumnos);
    }

    @Override
    public void onSuccessUpdate(String mens) {
        view.onSuccessUpdate(mens);
    }

    @Override
    public void onFailueUpdate(String e) {
        view.onFailueUpdate(e);
    }
}
