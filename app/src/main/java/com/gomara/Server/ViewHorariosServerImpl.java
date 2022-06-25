package com.gomara.Server;

import androidx.annotation.NonNull;

import com.gomara.Presenter.ViewHorariosPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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
}
