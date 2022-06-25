package com.gomara.Server;

import android.util.Log;

import androidx.annotation.NonNull;

import com.gomara.Presenter.MateriasPresenter;
import com.gomara.Prosecer.Materias;
import com.gomara.adapter.RecyclerAdapterMaterias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MateriasServerImpl implements MateriasServer{

    private MateriasPresenter materiasPresenter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MateriasServerImpl(MateriasPresenter materiasPresenter){
        this.materiasPresenter = materiasPresenter;
    }

    @Override
    public void getMaterias(String anio, String curso) {
        db.collection("Cursos/" + anio + curso + "/Materias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            ArrayList<Materias> allMaterias = new ArrayList<>();

                            for(QueryDocumentSnapshot document : task.getResult()){
                                allMaterias.add(new Materias(document.getString("materia")));
                            }

                            materiasPresenter.showMaterias(allMaterias);

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
                    materiasPresenter.showAnioCurso(anio,curso);
                }
            }
        });
    }

}
