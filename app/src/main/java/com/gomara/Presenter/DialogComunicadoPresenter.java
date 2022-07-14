package com.gomara.Presenter;

public interface DialogComunicadoPresenter {
    void onSuccess(String mens);
    void onFailure(String mens);

    void addComunicado(String title,String contenido,String anio,String curso);
}
