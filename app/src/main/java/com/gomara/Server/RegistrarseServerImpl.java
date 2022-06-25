package com.gomara.Server;

import android.util.Log;

import androidx.annotation.NonNull;

import com.gomara.Presenter.RegistrarsePresenter;
import com.gomara.dialog.AlertDialogs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrarseServerImpl implements RegistrarseServer{

    private RegistrarsePresenter presenter;
    public RegistrarseServerImpl(RegistrarsePresenter presenter){
        this.presenter = presenter;
    }



    @Override
    public void createUser(String email, String password, String nombre, String apellido, String anio, String curso) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("TAG","Email: " + email + "  Nombre: " + nombre + "  Anio: " + anio + "  Curso: " + curso);

                    Map<String,String> mapeo = new HashMap<>();
                    mapeo.put("email",email);
                    mapeo.put("nombre",nombre);
                    mapeo.put("apellido",apellido);
                    mapeo.put("anio",anio);
                    mapeo.put("curso",curso);

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("User").document(task.getResult().getUser().getUid()).set(mapeo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            presenter.onSuccess("Se agrego correctamente");
                        }
                    });

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                presenter.onFailure(e);
            }
        });
    }
}
