package com.gomara.UI;

import com.gomara.Prosecer.Comunicado;

import java.util.ArrayList;

public interface ComunicadoView {
    //Vista
    void showComunicado(ArrayList<Comunicado> coupon);

    //Presenter
    void getComunicado(String anio,String curso);
}
