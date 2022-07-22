package com.gomara.Server;

import androidx.annotation.NonNull;

import com.gomara.Presenter.ActivityLoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginServerImpl implements LoginServer {

    private ActivityLoginPresenter presenter;
    public LoginServerImpl(ActivityLoginPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void SignIn(String email, String passowod) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email,passowod).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    presenter.onSuccess(task.getResult().getUser().getUid());
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
