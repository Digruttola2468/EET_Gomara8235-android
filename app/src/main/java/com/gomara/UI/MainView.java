package com.gomara.UI;

public interface MainView {
    //View
    void showUser(String mensaje);
    void isAlumnado(boolean isAlumnado);

    //Presenter
    void getUser(String uid);
    void getUserisAlumnado(String uid);
}
