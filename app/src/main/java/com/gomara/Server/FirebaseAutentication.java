package com.gomara.Server;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.gomara.UI.Activity_main;
import com.gomara.dialog.AlertDialogs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseAutentication {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public void SignUp(FragmentManager manager,String email,String password,String nombre,String apellido,String anio,String curso){
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

                    db.collection("User").document(task.getResult().getUser().getUid()).set(mapeo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            AlertDialogs dialogs = new AlertDialogs("SignUp","Se agrego correctamente");
                            dialogs.show(manager,null);
                        }
                    });

                }

            }
        });
    }

    public void SignIn(Context context,FragmentManager fragmentManager, String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(context, Activity_main.class);
                    i.putExtra("User",mAuth.getCurrentUser().getUid());
                    context.startActivity(i);
                }
                if(task.isCanceled()){
                    AlertDialogs dialogs = new AlertDialogs("Error","El email o la contraseña son Incorrectas");
                    dialogs.show(fragmentManager,"TAG");
                }
            }
        });
    }

}
