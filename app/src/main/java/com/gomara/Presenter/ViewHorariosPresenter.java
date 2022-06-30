package com.gomara.Presenter;

public interface ViewHorariosPresenter {
    void showAnioCurso(String anio,String curso);
    void getAnioCurso(String uid);

    void getSizeAllMaterias(String anio,String curso);
    void showSizeAllMaterias(int size);
}
