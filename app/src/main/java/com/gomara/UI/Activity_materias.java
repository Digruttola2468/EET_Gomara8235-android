package com.gomara.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

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
    private Button btVolver;

    private MateriasPresenter presenter = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        btVolver = findViewById(R.id.btVolver_materias);
        recyclerView = findViewById(R.id.recyclerView_materias);

        presenter = new MateriasPresenterImpl(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        progressDialog = new ProgressDialog(Activity_materias.this);
        progressDialog.create();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        getAnioCurso(auth.getUid().toString());



        btVolver.setOnClickListener( (v) -> {
            Intent i = new Intent(Activity_materias.this, Activity_main.class);
            startActivity(i);
        });
    }

    @Override
    public void showMaterias(ArrayList<Materias> allMaterias) {
        progressDialog.dismiss();
        RecyclerAdapterMaterias adapter = new RecyclerAdapterMaterias(allMaterias);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void getMaterias(String anio, String curso) {
        presenter.getMaterias(anio,curso);
    }

    //-----------------------------------------------------------
    @Override
    public void showAnioCurso(String anio, String curso) {
        getMaterias(anio,curso);
    }


    @Override
    public void getAnioCurso(String userId) {
        presenter.getAnioCurso(userId);
    }
}
