package com.gomara.Presenter;

import android.net.Uri;

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

    @Override
    public void getSizeAllMaterias(String anio, String curso) {
        server.getSizeAllMaterias(anio,curso);
    }

    @Override
    public void showSizeAllMaterias(int size) {
        view.showSizeAllMaterias(size);
    }

    @Override
    public void getIsPreseptor(String uid) {
        server.getIsPreseptor(uid);
    }

    @Override
    public void showIsPreseptor(boolean isPreseptor) {
        view.showIsPreseptor(isPreseptor);
    }

    @Override
    public void getImage(String path) {
        server.getImage(path);
    }

    @Override
    public void updateImage(String anio, String curso, Uri uri) {
        server.updateImage(anio, curso, uri);
    }

    @Override
    public void showImage(Uri uri) {
        view.showImage(uri);
    }

    @Override
    public void onSuccess(String mens) {
        view.onSuccess(mens);
    }

    @Override
    public void onFailure(Exception e) {
        view.onFailure(e);
    }
}
