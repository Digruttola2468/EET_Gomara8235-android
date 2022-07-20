package com.gomara.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.gomara.R;
import com.gomara.Prosecer.Alumno;
import com.gomara.adapter.RecyclerAdapterGestionInasistencias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ActivityGestionInasistencias extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btVolver,btGuardar;

    /*
    * Mostrar a todos los alumnos con su respectiva inasistenicia
    * Mostrar al costado de la inasistencias un boton para aumentar o decrementar sus faltas
    * Al pulsar el boton guardar , se tendra que obtener el listado de cada alumno con su inasistencia
    * Actualizar en las bases de datos
    * */

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerAdapterGestionInasistencias gestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_inasistencias);

        btVolver = findViewById(R.id.btVolver_gestionInasistenicas);
        btGuardar = findViewById(R.id.btGuardar_gestionInasistencias);
        recyclerView = findViewById(R.id.recyclerView_gestionInasistencias);

        recyclerView.setLayoutManager(new LinearLayoutManager(null));

        db.collection("Cursos/5a/Alumnos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<Alumno> alumn = new ArrayList<>();

                for(DocumentSnapshot documentSnapshot : task.getResult()){
                    alumn.add(new Alumno(documentSnapshot.getId(),documentSnapshot.getString("Apellido"),documentSnapshot.getDouble("Inasistencias")));
                }

                gestion = new RecyclerAdapterGestionInasistencias(alumn);
                recyclerView.setAdapter(gestion);
            }
        });


        btGuardar.setOnClickListener( view -> {
            ArrayList<Alumno> alumList = gestion.getAlumno();
            for (int i = 0; i < alumList.size(); i++) {

                db.collection("Cursos/5a/Alumnos").document(alumList.get(i).getId()).update("Inasistencias",alumList.get(i).getInasistencias()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ActivityGestionInasistencias.this, "Se Actualizo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            Intent i = new Intent(ActivityGestionInasistencias.this,Activity_inasistencias.class);
            startActivity(i);
            finish();
        });

        btVolver.setOnClickListener( view -> {
            Intent i = new Intent(ActivityGestionInasistencias.this,Activity_inasistencias.class);
            startActivity(i);
            finish();
        });

    }
}