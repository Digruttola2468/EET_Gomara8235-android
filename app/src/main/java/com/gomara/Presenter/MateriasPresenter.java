package com.gomara.Presenter;

import com.gomara.Prosecer.Materias;

import java.util.ArrayList;

public interface MateriasPresenter {
    //View
    void showMaterias(ArrayList<Materias> allMaterias);
    void showAnioCurso(String anio,String curso);
    void showIsDelegado(boolean isDelegado);

    //-----------------------------------------------------
    // Presenter
    void getAnioCurso(String userId);
    void getIsDelegado(String userId);
    void getMaterias(String anio,String curso);
}
