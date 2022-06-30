package com.gomara.Server;

public interface MateriasServer {
    //Presenter
    void getAnioCurso(String uid);
    void getIsDelegado(String uid);
    void getMaterias(String anio,String curso);
}
