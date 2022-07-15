package com.gomara.UI;

import android.net.Uri;

public interface ViewHorariosView {
    //
    void showAnioCurso(String anio,String curso);
    void getAnioCurso(String uid);

    //How many Subjects of school have?
    void getSizeAllMaterias(String anio,String curso);
    void showSizeAllMaterias(int size);

    //Verify is Preseptor
    void getIsPreseptor(String uid);
    void showIsPreseptor(boolean isPreseptor);

    //FirebaseStorage
    void getImage(String path);
    void updateImage(String anio, String curso, Uri uri);
    void showImage(Uri uri);
    void onSuccess(String mens);
    void onFailure(Exception e);
}
