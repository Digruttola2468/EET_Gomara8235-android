package com.gomara.Presenter;

import com.gomara.Prosecer.Alumno;
import com.gomara.Server.InasistenciasServer;
import com.gomara.Server.InasistenciasServerImpl;
import com.gomara.ui.Activity_inasistencias;
import com.gomara.ui.InasistenciasView;

import java.util.ArrayList;

public class InasistenciasPresenterImpl implements InasistenciasPresenter{


    private InasistenciasServer server = new InasistenciasServerImpl(this);

    private InasistenciasView view;
    public InasistenciasPresenterImpl(InasistenciasView view){
        this.view = view;
    }

    @Override
    public void showInasistencias(ArrayList<Alumno> allAlumnos) {
        view.showInasistencias(allAlumnos);
    }

    @Override
    public void getInasistencias(String anio, String curso) {
        server.getInasistencias(anio,curso);
    }

    @Override
    public void isPreseptor(boolean isPreseptor) {
        view.isPreseptor(isPreseptor);
    }

    @Override
    public void showAnioCurso(String anio, String curso) {
        view.showAnioCurso(anio, curso);
    }

    @Override
    public void getAnioCurso(String uid) {
        server.getAnioCurso(uid);
    }
}
