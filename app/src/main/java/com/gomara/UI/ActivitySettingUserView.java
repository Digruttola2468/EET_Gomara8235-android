package com.gomara.UI;

public interface ActivitySettingUserView {
    void getUpdateUser(String nombre,String apellido,String email);
    void getUserActual();


    void onSuccessGetUserActual(String nombre,String apellido,String email);
    void onFailureGetUserActual(String mens);

    void onSuccess(String mens);
    void onFailure(String mens);
}
