package com.gomara.ui;

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
import com.gomara.Presenter.GestionInasistenciasImpl;
import com.gomara.Presenter.GestionInasistenciasPresenter;
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

public class ActivityGestionInasistencias extends AppCompatActivity implements GestionInasistenciasView{

    private RecyclerView recyclerView;
    private Button btVolver,btGuardar;

    /*
    * Mostrar a todos los alumnos con su respectiva inasistenicia
    * Mostrar al costado de la inasistencias un boton para aumentar o decrementar sus faltas
    * Al pulsar el boton guardar , se tendra que obtener el listado de cada alumno con su inasistencia
    * Actualizar en las bases de datos
    * */


    private RecyclerAdapterGestionInasistencias gestion;
    private String anio,curso;
    private GestionInasistenciasPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_inasistencias);

        btVolver = findViewById(R.id.btVolver_gestionInasistenicas);
        btGuardar = findViewById(R.id.btGuardar_gestionInasistencias);
        recyclerView = findViewById(R.id.recyclerView_gestionInasistencias);

        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        presenter = new GestionInasistenciasImpl(this);

        getAnioCurso();

        btGuardar.setOnClickListener( view -> {
            updateAlumnos(anio,curso,gestion.getAlumno());
        });

        btVolver.setOnClickListener( view -> {
            Intent i = new Intent(ActivityGestionInasistencias.this,Activity_inasistencias.class);
            startActivity(i);
            finish();
        });

    }

    @Override
    public void getAnioCurso() {
        presenter.getAnioCurso();
    }

    @Override
    public void getListAlumno(String anio, String curso) {
        presenter.getListAlumno(anio,curso);
    }

    @Override
    public void updateAlumnos(String anio,String curso,ArrayList<Alumno> alumnos) {
        presenter.updateAlumnos(anio,curso,alumnos);
    }

    @Override
    public void showAnioCurso(String anio, String curso) {
        this.anio = anio;
        this.curso = curso;

        getListAlumno(anio,curso);
    }

    @Override
    public void showListAlumno(ArrayList<Alumno> alumnos) {
        gestion = new RecyclerAdapterGestionInasistencias(alumnos);
        recyclerView.setAdapter(gestion);
    }

    @Override
    public void onSuccessUpdate(String mens) {
        Toast.makeText(this, mens, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(ActivityGestionInasistencias.this,Activity_inasistencias.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onFailueUpdate(String e) {
        Log.w("ERROR UPDATE",e);
    }
}