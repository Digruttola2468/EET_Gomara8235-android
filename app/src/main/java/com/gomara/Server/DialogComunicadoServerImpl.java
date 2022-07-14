package com.gomara.Server;

import androidx.annotation.NonNull;

import com.gomara.Presenter.DialogComunicadoPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DialogComunicadoServerImpl implements DialogComunicadoServer{

    private DialogComunicadoPresenter presenter;

    public DialogComunicadoServerImpl(DialogComunicadoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addComunicado(String title, String contenido, String anio, String curso) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,String> mapeo = new HashMap<>();

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(date);

        mapeo.put("title",title);
        mapeo.put("contenido",contenido);
        mapeo.put("fecha",strDate);

        db.collection("Cursos/" + anio + curso + "/Comunicados").document().set(mapeo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    presenter.onSuccess("Se agrego Correctamente");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                presenter.onFailure(e.getMessage());
            }
        });
    }
}
