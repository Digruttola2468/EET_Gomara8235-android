package com.gomara.Presenter;

import com.gomara.Server.LoginServer;
import com.gomara.Server.LoginServerImpl;
import com.gomara.ui.ActivityLoginView;

public class ActivityLoginPresenterImpl implements ActivityLoginPresenter{

    private LoginServer server = new LoginServerImpl(this);

    private ActivityLoginView view;
    public ActivityLoginPresenterImpl(ActivityLoginView view){
        this.view = view;
    }


    @Override
    public void onSuccess(String userId) {
        view.onSuccess(userId);
    }

    @Override
    public void onFailure(Exception e) {
        view.onFailure(e);
    }

    @Override
    public void IniciarSesion(String email, String password) {
        server.SignIn(email,password);
    }
}
