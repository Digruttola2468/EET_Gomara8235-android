package com.gomara.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Presenter.MateriasPresenter;
import com.gomara.Presenter.MateriasPresenterImpl;
import com.gomara.Prosecer.Materias;
import com.gomara.adapter.RecyclerAdapterMaterias;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Activity_materias extends AppCompatActivity implements MateriasView{

    private RecyclerView recyclerView;
    private Button btVolver,btFechaEvaluacion;

    private MateriasPresenter presenter;
    private ProgressDialog progressDialog;

    private ArrayList<String> materias;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        btVolver = findViewById(R.id.btVolver_materias);
        recyclerView = findViewById(R.id.recyclerView_materias);
        btFechaEvaluacion = findViewById(R.id.btFechaEvaluacion_materias);

        presenter = new MateriasPresenterImpl(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(Activity_materias.this);
        progressDialog.create();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        getAnioCurso(auth.getUid());

        btFechaEvaluacion.setVisibility(View.GONE);

        btVolver.setOnClickListener( (v) -> {
            Intent i = new Intent(Activity_materias.this, Activity_main.class);
            startActivity(i);
        });
        btFechaEvaluacion.setOnClickListener( (v) -> {
            DialogFechaEvaluacion fechaEvaluacion = new DialogFechaEvaluacion(materias);
            fechaEvaluacion.show(getSupportFragmentManager(),null);
        });
    }

    @Override
    public void showMaterias(ArrayList<Materias> allMaterias) {
        progressDialog.dismiss();
        RecyclerAdapterMaterias adapter = new RecyclerAdapterMaterias(allMaterias);
        recyclerView.setAdapter(adapter);

        materias = new ArrayList<>();
        for (int i = 0; i < allMaterias.size(); i++) {
            materias.add( allMaterias.get(i).getMateria() );
        }
    }
    @Override
    public void getMaterias(String anio, String curso) {
        presenter.getMaterias(anio,curso);
    }

    @Override
    public void getIsDelegado(String uid) {
        presenter.getIsDelegado(uid);
    }

    //-----------------------------------------------------------
    @Override
    public void showAnioCurso(String anio, String curso) {
        getMaterias(anio,curso);

    }

    @Override
    public void showIsDelegado(boolean isDelegado) {
        if(isDelegado)
            btFechaEvaluacion.setVisibility(View.VISIBLE);
    }


    @Override
    public void getAnioCurso(String userId) {
        presenter.getAnioCurso(userId);
        getIsDelegado(userId);
    }
}
