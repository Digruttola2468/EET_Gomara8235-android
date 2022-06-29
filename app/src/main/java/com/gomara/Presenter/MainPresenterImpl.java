package com.gomara.Presenter;

import com.gomara.Server.MainServer;
import com.gomara.Server.MainServerImpl;
import com.gomara.UI.Activity_main;
import com.gomara.UI.MainView;

public class MainPresenterImpl implements MainPresenter{

    private MainServer server = new MainServerImpl(this);

    private MainView mainView;
    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void showUser(String mensaje) {
        mainView.showUser(mensaje);
    }

    @Override
    public void isAlumnado(Boolean isAlumado) {
        mainView.isAlumnado(isAlumado);
    }

    @Override
    public void getUser(String uid) {
        server.getUser(uid);
    }

    @Override
    public void getUserisAlumnado(String uid) {
        server.getUserisAlumnado(uid);
    }
}
