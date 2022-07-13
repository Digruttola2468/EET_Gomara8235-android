package com.gomara.Server;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.gomara.Presenter.ActivitySettingUserPresenter;
import com.gomara.Presenter.ActivitySettingUserPresenterImpl;
import com.gomara.UI.Activity_settingUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SettingUserServerImpl implements SettingUserServer{

    private ActivitySettingUserPresenter presenter;
    public SettingUserServerImpl(ActivitySettingUserPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getUpdateUser(String nombre, String apellido,String email) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        db.collection("User").document(auth.getUid())
                .update("nombre",nombre,
                        "apellido",apellido,"email",email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            presenter.onSuccess("Se modifico con exito");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        presenter.onFailure(e.getMessage());
                    }
                });

    }

    @Override
    public void getUserActual() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        db.collection("User").document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    String nombre = task.getResult().get("nombre").toString();
                    String apellido = task.getResult().get("apellido").toString();
                    String email = task.getResult().get("email").toString();

                    presenter.onSuccessGetUserActual(nombre,apellido,email);
                }
            }
        });
    }
}
