package com.gomara.Presenter;

import com.gomara.Prosecer.Comunicado;

import java.util.ArrayList;

public interface ComunicadoPresenter {
    //Vista
    void showCoupons(ArrayList<Comunicado> comunicados);

    //Interactor
    void getCoupons(String anio,String curso);
}
