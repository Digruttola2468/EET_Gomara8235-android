package com.gomara.Presenter;

import com.gomara.Server.SettingUserServer;
import com.gomara.Server.SettingUserServerImpl;
import com.gomara.UI.ActivitySettingUserView;
import com.gomara.UI.Activity_settingUser;

public class ActivitySettingUserPresenterImpl implements ActivitySettingUserPresenter{

    private SettingUserServer server = new SettingUserServerImpl(this);

    private ActivitySettingUserView view;
    public ActivitySettingUserPresenterImpl(ActivitySettingUserView activity_settingUser) {
        this.view = activity_settingUser;
    }

    @Override
    public void getUpdateUser(String nombre, String apellido, String email) {
        server.getUpdateUser(nombre, apellido, email);
    }

    @Override
    public void getUserActual() {
        server.getUserActual();
    }

    @Override
    public void onSuccessGetUserActual(String nombre, String apellido, String email) {
        view.onSuccessGetUserActual(nombre, apellido, email);
    }

    @Override
    public void onFailureGetUserActual(String mens) {
        view.onFailureGetUserActual(mens);
    }

    @Override
    public void onSuccess(String mens) {
        view.onSuccess(mens);
    }

    @Override
    public void onFailure(String mens) {
        view.onFailure(mens);
    }
}
