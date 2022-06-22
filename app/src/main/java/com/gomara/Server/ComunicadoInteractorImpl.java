package com.gomara.Server;

import android.util.Log;

import androidx.annotation.NonNull;

import com.gomara.Presenter.ComunicadoPresenter;
import com.gomara.Prosecer.Comunicado;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ComunicadoInteractorImpl implements ComunicadoInteractor {

    ComunicadoPresenter couponPresenter;


    public ComunicadoInteractorImpl(ComunicadoPresenter couponPresenter){
        this.couponPresenter = couponPresenter;
    }

    @Override
    public void getCouponsFirebase(String anio,String curso) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Cursos/" + anio + curso + "/Comunicados")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            ArrayList<Comunicado> allComunicados = new ArrayList<>();

                            for(QueryDocumentSnapshot document : task.getResult()){
                                allComunicados.add(new Comunicado(document.getString("title"),document.getString("contenido")));
                            }

                            couponPresenter.showCoupons(allComunicados);
                        }else
                            Log.w("","Error getting documents." , task.getException());
                    }
                });
    }

    @Override
    public void getCouponsAuth() {

    }
}
