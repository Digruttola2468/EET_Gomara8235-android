package com.gomara.Presenter;

import com.gomara.Prosecer.Materias;
import com.gomara.Server.MateriasServer;
import com.gomara.Server.MateriasServerImpl;
import com.gomara.UI.MateriasView;

import java.util.ArrayList;

public class MateriasPresenterImpl implements MateriasPresenter{


    //Sincronizamos con el Posterior osea Base de datos
    private MateriasServer materiasServer = new MateriasServerImpl(this);

    //Inyeccion de Dependencia del anterior
    private MateriasView materiasView;


    public MateriasPresenterImpl(MateriasView materiasView){
        this.materiasView = materiasView;
    }


    @Override
    public void showMaterias(ArrayList<Materias> allMaterias) {
        materiasView.showMaterias(allMaterias);
    }
    @Override
    public void getMaterias(String anio, String curso) {
        materiasServer.getMaterias(anio,curso);
    }


    @Override
    public void showAnioCurso(String anio, String curso) {
        materiasView.showAnioCurso(anio,curso);
    }

    @Override
    public void getAnioCurso(String userId) {
        materiasServer.getAnioCurso(userId);
    }
}
