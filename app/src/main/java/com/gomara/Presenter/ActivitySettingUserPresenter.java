package com.gomara.Presenter;

public interface ActivitySettingUserPresenter {
    void getUpdateUser(String nombre,String apellido);
    void getUserActual();


    void onSuccessGetUserActual(String nombre,String apellido,String email);
    void onFailureGetUserActual(String mens);

    void onSuccess(String mens);
    void onFailure(String mens);
}
