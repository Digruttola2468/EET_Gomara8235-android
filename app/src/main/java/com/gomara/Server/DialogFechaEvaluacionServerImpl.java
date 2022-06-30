package com.gomara.Server;

import android.util.Log;

import androidx.annotation.NonNull;

import com.gomara.Presenter.DialogFechaEvaluacionImpl;
import com.gomara.Presenter.DialogFechaEvaluacionPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class DialogFechaEvaluacionServerImpl implements DialogFechaEvaluacionServer{

    private DialogFechaEvaluacionPresenter dialogFechaEvaluacionPresenter;
    public DialogFechaEvaluacionServerImpl(DialogFechaEvaluacionPresenter dialogFechaEvaluacion) {
        this.dialogFechaEvaluacionPresenter = dialogFechaEvaluacion;
    }


    @Override
    public void setFechaEvaluacion(String anio, String curso, String materia, String fecha, String temas) {

        Map<String,String> mapeo = new HashMap<>();
        mapeo.put("materia",materia);
        if(!fecha.equals("")){
            mapeo.put("evaluacion",fecha);
        }
        if (!temas.equals("")){
            mapeo.put("temas",temas);
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Cursos/" + anio + curso + "/Materias").whereEqualTo("materia",materia).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    String id = task.getResult().getDocumentChanges().get(0).getDocument().getId();


                    db.collection("Cursos/" + anio + curso + "/Materias").document(id).set(mapeo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialogFechaEvaluacionPresenter.onSuccess("Se Guardo Con Exito");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialogFechaEvaluacionPresenter.onFailed(e.getMessage());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialogFechaEvaluacionPresenter.onFailed(e.getMessage());
                        }
                    });
                }
            }
        });
    }

}
