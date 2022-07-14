package com.gomara.Server;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.gomara.Presenter.ComunicadoPresenter;
import com.gomara.Prosecer.Comunicado;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ComunicadoServerImpl implements ComunicadoServer {

    private ComunicadoPresenter couponPresenter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ComunicadoServerImpl(ComunicadoPresenter couponPresenter){
        this.couponPresenter = couponPresenter;
    }

    @Override
    public void getCouponsFirebase(String anio,String curso) {

        db.collection("Cursos/" + anio + curso + "/Comunicados")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            ArrayList<Comunicado> allComunicados = new ArrayList<>();

                            for(QueryDocumentSnapshot document : task.getResult()){
                                allComunicados.add(new Comunicado(document.getString("title"),document.getString("contenido"),document.getString("fecha")));
                            }

                            couponPresenter.showCoupons(allComunicados);
                        }else
                            Log.w("","Error getting documents." , task.getException());
                    }
                });
    }

    @Override
    public void getUser(String uid) {
        db.collection("User").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    String anio = task.getResult().get("anio").toString();
                    String curso = task.getResult().get("curso").toString();
                    boolean isPreseptor = false;

                    if(task.getResult().contains("preseptor"))
                        isPreseptor = task.getResult().getBoolean("preseptor");

                    couponPresenter.isPreseptor(isPreseptor);
                    couponPresenter.showUser(anio,curso);
                }
            }
        });
    }

}
