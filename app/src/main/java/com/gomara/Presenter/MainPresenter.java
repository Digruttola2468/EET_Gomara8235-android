package com.gomara.Presenter;

public interface MainPresenter {
    //View
    void showUser(String mensaje);
    void isAlumnado(Boolean isAlumnado);

    //Presenter
    void getUser(String uid);
    void getUserisAlumnado(String uid);
}
