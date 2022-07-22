package com.gomara.ui;


public interface RegistrarseView {
    //View
    void onSuccess(String mens);
    void onFailure(Exception e);

    //Presenter
    void createUser(String email,String password,String nombre,String apellido,String anio,String curso);
}
