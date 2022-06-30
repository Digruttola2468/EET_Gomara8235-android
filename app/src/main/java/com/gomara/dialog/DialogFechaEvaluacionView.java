package com.gomara.dialog;

public interface DialogFechaEvaluacionView {
    void setFechaEvaluacion(String anio,String curso,String materia,String fecha,String temas);
    void onSuccess(String mens);
    void onFailed(String mens);
}
