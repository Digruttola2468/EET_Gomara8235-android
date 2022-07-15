package com.gomara.Server;

import android.net.Uri;

public interface ViewHorariosServer {
    void getAnioCurso(String uid);

    void getSizeAllMaterias(String anio,String curso);

    //Verify is Preseptor
    void getIsPreseptor(String uid);

    //FirebaseStorage
    void getImage(String path);
    void updateImage(String anio, String curso, Uri uri);
}
