package com.gomara.ui;

import com.gomara.Prosecer.Comunicado;

import java.util.ArrayList;

public interface ComunicadoView {
    //Vista
    void showUser(String anio,String curso);
    void showComunicado(ArrayList<Comunicado> coupon);
    void isPreseptor(boolean isPreseptor);

    //Presenter
    void getUser(String uid);
    void getComunicado(String anio,String curso);
}
