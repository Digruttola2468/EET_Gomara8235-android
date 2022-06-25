package com.gomara.UI;

import com.gomara.Prosecer.Materias;

import java.util.ArrayList;

public interface MateriasView {
    //View
    void showMaterias(ArrayList<Materias> allMaterias);

    //Presenter
    void getMaterias(String anio,String curso);
}
