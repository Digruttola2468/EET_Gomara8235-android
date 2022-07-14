package com.gomara.Presenter;

import com.gomara.Server.DialogComunicadoServer;
import com.gomara.Server.DialogComunicadoServerImpl;
import com.gomara.dialog.DialogComunicadoView;

public class DialogComunicadoPresenterImpl implements DialogComunicadoPresenter{


    private DialogComunicadoServer server = new DialogComunicadoServerImpl(this);

    private DialogComunicadoView view;
    public DialogComunicadoPresenterImpl(DialogComunicadoView view) {
        this.view = view;
    }

    @Override
    public void onSuccess(String mens) {
        view.onSuccess(mens);
    }

    @Override
    public void onFailure(String mens) {
        view.onFailure(mens);
    }

    @Override
    public void addComunicado(String title, String contenido, String anio, String curso) {
        server.addComunicado(title, contenido, anio, curso);
    }
}
