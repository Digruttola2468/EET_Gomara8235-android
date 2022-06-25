package com.gomara.UI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Presenter.InasistenciasPresenter;
import com.gomara.Presenter.InasistenciasPresenterImpl;
import com.gomara.Prosecer.Alumno;
import com.gomara.adapter.RecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Activity_inasistencias extends Activity implements InasistenciasView{

    private RecyclerView recyclerView;

    private ProgressDialog progressDialog;

    private InasistenciasPresenter inasistenciasPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inasistencias);

        recyclerView = findViewById(R.id.recycler_datos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        inasistenciasPresenter = new InasistenciasPresenterImpl(this);

        progressDialog = new ProgressDialog(Activity_inasistencias.this);
        progressDialog.create();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();

        getAnioCurso(auth.getUid().toString());

    }

    @Override
    public void showInasistencias(ArrayList<Alumno> allAlumnos) {
        progressDialog.dismiss();

        RecyclerAdapter adapter = new RecyclerAdapter(allAlumnos);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getInasistencias(String anio, String curso) {
        inasistenciasPresenter.getInasistencias(anio,curso);
    }

    @Override
    public void showAnioCurso(String anio, String curso) {
        getInasistencias(anio,curso);
    }

    @Override
    public void getAnioCurso(String uid) {
        inasistenciasPresenter.getAnioCurso(uid);
    }
}
