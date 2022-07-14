package com.gomara.Presenter;

import com.gomara.Prosecer.Comunicado;

import java.util.ArrayList;

public interface ComunicadoPresenter {
    //Vista
    void showUser(String anio,String curso);
    void showCoupons(ArrayList<Comunicado> comunicados);
    void isPreseptor(boolean isPreseptor);

    //Interactor
    void getUser(String uid);
    void getCoupons(String anio,String curso);
}
