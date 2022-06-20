package com.gomara.Server;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gomara.Prosecer.Alumno;
import com.gomara.Prosecer.Comunicado;
import com.gomara.adapter.RecyclerAdapter;
import com.gomara.adapter.RecyclerAdapterComunicados;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ServerFireBase {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void leerInasistencias(RecyclerView recyclerView,String anio, String curso){
        db.collection("Cursos/" + anio + curso + "/Alumnos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            ArrayList<Alumno> allAlumnos = new ArrayList<>();

                            for(QueryDocumentSnapshot document : task.getResult()){
                                allAlumnos.add(new Alumno(document.get("Nombre").toString(),Float.parseFloat(document.get("Inasistencias").toString())));
                            }

                            RecyclerAdapter adapter = new RecyclerAdapter(allAlumnos);
                            recyclerView.setAdapter(adapter);
                        }else
                            Log.w("","Error getting documents." , task.getException());
                    }
                });
    }

    public void leerComunicados(RecyclerView recyclerView,String anio, String curso){
        db.collection("Comunicado/" + anio + curso + "/Informacion")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            ArrayList<Comunicado> allComunicados = new ArrayList<>();

                            for(QueryDocumentSnapshot document : task.getResult()){
                                allComunicados.add(new Comunicado(document.getString("title"),document.getString("contenido")));
                            }

                            RecyclerAdapterComunicados adapter = new RecyclerAdapterComunicados(allComunicados);
                            recyclerView.setAdapter(adapter);
                        }else
                            Log.w("","Error getting documents." , task.getException());
                    }
                });
    }


}
