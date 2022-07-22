package com.gomara.ui;

public interface ActivityLoginView {
    //View
    void onSuccess(String userId);
    void onFailure(Exception e);
    //Presenter
    void IniciarSesion(String email,String password);
}
