package com.gomara.UI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.Alumno;
import com.gomara.Prosecer.Horarios;
import com.gomara.Server.ServerFireBase;
import com.gomara.adapter.RecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Activity_inasistencias extends Activity {

    private RecyclerView recyclerView;
    private ServerFireBase serverFireBase = new ServerFireBase();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inasistencias);

        recyclerView = findViewById(R.id.recycler_datos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ProgressDialog progressDialog = new ProgressDialog(Activity_inasistencias.this);
        progressDialog.create();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();

        db.collection("User").document(auth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    String anio = task.getResult().get("anio").toString();
                    String curso = task.getResult().get("curso").toString();

                    serverFireBase.leerInasistencias(recyclerView,anio,curso );
                }
            }
        });

    }

}
