package com.gomara.Server;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.gomara.Presenter.GestionInasistenciasImpl;
import com.gomara.Presenter.GestionInasistenciasPresenter;
import com.gomara.Prosecer.Alumno;
import com.gomara.UI.ActivityGestionInasistencias;
import com.gomara.adapter.RecyclerAdapterGestionInasistencias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GestionInasistenciasServerImpl implements GestionInasistenciasServer{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private GestionInasistenciasPresenter presenter;
    public GestionInasistenciasServerImpl(GestionInasistenciasPresenter gestionInasistencias) {
        this.presenter = gestionInasistencias;
    }

    @Override
    public void getAnioCurso() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        db.collection("User").document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    String anio = task.getResult().getString("anio");
                    String curso = task.getResult().getString("curso");

                    presenter.showAnioCurso(anio,curso);
                }
            }
        });
    }

    @Override
    public void getListAlumno(String anio, String curso) {
        db.collection("Cursos/"+ anio + curso + "/Alumnos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<Alumno> alumn = new ArrayList<>();

                for(DocumentSnapshot documentSnapshot : task.getResult()){
                    alumn.add(new Alumno(documentSnapshot.getId(),documentSnapshot.getString("Apellido"),documentSnapshot.getDouble("Inasistencias")));
                }

                presenter.showListAlumno(alumn);
            }
        });
    }

    @Override
    public void updateAlumnos(String anio,String curso,ArrayList<Alumno> alumnos) {
        for (int i = 0; i < alumnos.size(); i++) {

            db.collection("Cursos/" + anio + curso + "/Alumnos").document(alumnos.get(i).getId()).update("Inasistencias",alumnos.get(i).getInasistencias()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        presenter.onSuccessUpdate("Se Actualizo correctamente");
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    presenter.onFailueUpdate(e.getMessage());
                }
            });

        }
    }
}
