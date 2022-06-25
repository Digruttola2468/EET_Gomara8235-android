package com.gomara.Presenter;

public interface ActivityLoginPresenter {
    //View
    void onSuccess(String userId);
    void onFailure(Exception e);
    //Presenter
    void IniciarSesion(String email,String password);
}
