package com.gomara.Server;

import android.util.Log;

import androidx.annotation.NonNull;

import com.gomara.Presenter.ViewHorariosPresenter;
import com.gomara.Prosecer.Materias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewHorariosServerImpl implements ViewHorariosServer{

    private ViewHorariosPresenter viewHorariosPresenter;
    public ViewHorariosServerImpl(ViewHorariosPresenter viewHorariosPresenter){
        this.viewHorariosPresenter = viewHorariosPresenter;
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public void getAnioCurso(String uid) {
        db.collection("User").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    String anio = task.getResult().get("anio").toString();
                    String curso = task.getResult().get("curso").toString();

                    viewHorariosPresenter.showAnioCurso(anio,curso);
                }

            }
        });

    }

    @Override
    public void getSizeAllMaterias(String anio, String curso) {
        db.collection("Cursos/" + anio + curso + "/Materias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                            viewHorariosPresenter.showSizeAllMaterias(task.getResult().size());
                        else
                            Log.w("","Error getting documents." , task.getException());
                    }
                });
    }
}
