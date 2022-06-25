package com.gomara.Presenter;

import com.gomara.Prosecer.Materias;

import java.util.ArrayList;

public interface MateriasPresenter {
    //View
    void showMaterias(ArrayList<Materias> allMaterias);
    //Presenter
    void getMaterias(String anio,String curso);

    //------------------------------------------------------
    void showAnioCurso(String anio,String curso);
    void getAnioCurso(String userId);
}
