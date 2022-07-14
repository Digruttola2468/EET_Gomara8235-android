package com.gomara.dialog;

public interface DialogComunicadoView {
    void onSuccess(String mens);
    void onFailure(String mens);

    void addComunicado(String title,String contenido,String anio,String curso);
}
