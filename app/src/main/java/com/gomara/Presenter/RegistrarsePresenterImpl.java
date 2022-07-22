package com.gomara.Presenter;

import com.gomara.Server.RegistrarseServer;
import com.gomara.Server.RegistrarseServerImpl;
import com.gomara.ui.RegistrarseView;

public class RegistrarsePresenterImpl implements RegistrarsePresenter{

    private RegistrarseServer server = new RegistrarseServerImpl(this);

    private RegistrarseView view;
    public RegistrarsePresenterImpl(RegistrarseView view){
        this.view = view;
    }

    @Override
    public void onSuccess(String mens) {
        view.onSuccess(mens);
    }

    @Override
    public void onFailure(Exception e) {
        view.onFailure(e);
    }

    @Override
    public void createUser(String email, String password, String nombre, String apellido, String anio, String curso) {
        server.createUser(email, password, nombre, apellido, anio, curso);
    }
}
