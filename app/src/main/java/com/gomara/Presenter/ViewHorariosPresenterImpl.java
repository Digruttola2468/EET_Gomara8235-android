package com.gomara.Presenter;

import com.gomara.Server.ViewHorariosServer;
import com.gomara.Server.ViewHorariosServerImpl;
import com.gomara.UI.ViewHorariosView;

public class ViewHorariosPresenterImpl implements ViewHorariosPresenter{

    private ViewHorariosServer server = new ViewHorariosServerImpl(this);

    private ViewHorariosView view;
    public ViewHorariosPresenterImpl(ViewHorariosView view){
        this.view = view;
    }

    @Override
    public void showAnioCurso(String anio, String curso) {
        view.showAnioCurso(anio,curso);
    }

    @Override
    public void getAnioCurso(String uid) {
        server.getAnioCurso(uid);
    }
}
