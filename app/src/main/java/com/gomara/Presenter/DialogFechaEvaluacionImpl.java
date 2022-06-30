package com.gomara.Presenter;

import com.gomara.Server.DialogFechaEvaluacionServer;
import com.gomara.Server.DialogFechaEvaluacionServerImpl;
import com.gomara.dialog.DialogFechaEvaluacionView;

public class DialogFechaEvaluacionImpl implements DialogFechaEvaluacionPresenter {

    private DialogFechaEvaluacionServer server = new DialogFechaEvaluacionServerImpl(this);

    private DialogFechaEvaluacionView evalu;
    public DialogFechaEvaluacionImpl(DialogFechaEvaluacionView evalu){
        this.evalu = evalu;
    }

    @Override
    public void setFechaEvaluacion(String anio, String curso, String materia, String fecha, String temas) {
        server.setFechaEvaluacion(anio,curso,materia,fecha,temas);
    }

    @Override
    public void onSuccess(String mens) {
        evalu.onSuccess(mens);
    }

    @Override
    public void onFailed(String mens) {
        evalu.onFailed(mens);
    }
}
