package com.gomara.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Server.ServerFireBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Activity_materias extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btVolver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        btVolver = findViewById(R.id.btVolver_materias);
        recyclerView = findViewById(R.id.recyclerView_materias);

        btVolver.setOnClickListener((v) -> {
            Intent i = new Intent(Activity_materias.this, Activity_main.class);
            startActivity(i);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ProgressDialog progressDialog = new ProgressDialog(Activity_materias.this);
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

                    new ServerFireBase().leerMaterias(recyclerView,anio,curso);
                }
            }
        });

    }
}
