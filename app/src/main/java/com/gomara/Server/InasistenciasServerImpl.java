package com.gomara.Server;

import android.util.Log;

import androidx.annotation.NonNull;

import com.gomara.Presenter.InasistenciasPresenter;
import com.gomara.Presenter.InasistenciasPresenterImpl;
import com.gomara.Prosecer.Alumno;
import com.gomara.adapter.RecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class InasistenciasServerImpl implements InasistenciasServer{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    InasistenciasPresenter presenter;
    public InasistenciasServerImpl(InasistenciasPresenter inasistenciasPresenter) {
        presenter = inasistenciasPresenter;
    }

    @Override
    public void getInasistencias(String anio, String curso) {
        db.collection("Cursos/" + anio + curso + "/Alumnos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            ArrayList<Alumno> allAlumnos = new ArrayList<>();

                            for(QueryDocumentSnapshot document : task.getResult()){
                                allAlumnos.add(new Alumno(document.getId(),document.get("Nombre").toString(),Float.parseFloat(document.get("Inasistencias").toString())));
                            }

                            presenter.showInasistencias(allAlumnos);
                        }else
                            Log.w("","Error getting documents." , task.getException());
                    }
                });
    }

    @Override
    public void getAnioCurso(String uid) {
        db.collection("User").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    String anio = task.getResult().get("anio").toString();
                    String curso = task.getResult().get("curso").toString();

                    boolean isPreseptor = false;
                    if(task.getResult().contains("preseptor"))
                        isPreseptor = task.getResult().getBoolean("preseptor");

                    presenter.isPreseptor(isPreseptor);
                    presenter.showAnioCurso(anio,curso);
                }
            }
        });
    }
}
