package com.gomara.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Presenter.ComunicadoPresenter;
import com.gomara.Presenter.ComunicadoPresenterImp;
import com.gomara.Prosecer.Comunicado;
import com.gomara.adapter.RecyclerAdapterComunicados;
import com.gomara.dialog.DialogComunicado;
import com.gomara.dialog.DialogFechaEvaluacion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Activity_comunicados extends AppCompatActivity implements ComunicadoView {

    private RecyclerView recyclerView;
    private Button btVolver,btAgregarComunicado;

    private ComunicadoPresenter couponPresenter = null;
    private ProgressDialog progressDialog;

    private String anio = "";
    private String curso = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicados);
        getSupportActionBar().hide();

        couponPresenter = new ComunicadoPresenterImp(this);

        recyclerView = findViewById(R.id.recyclerView_comunicados);
        btVolver = findViewById(R.id.btVolver_comunicados);
        btAgregarComunicado = findViewById(R.id.btAgregarComunicado_comunicados);

        btAgregarComunicado.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(Activity_comunicados.this);
        progressDialog.create();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        getUser(auth.getUid());

        btVolver.setOnClickListener(view -> {
            Intent i = new Intent(Activity_comunicados.this,Activity_main.class);
            startActivity(i);
            finish();
        });

        btAgregarComunicado.setOnClickListener( view -> {
            DialogComunicado fechaEvaluacion = new DialogComunicado(anio,curso,Activity_comunicados.this);
            fechaEvaluacion.show(getSupportFragmentManager(),null);
            
            if(fechaEvaluacion.isAdded()){
                Toast.makeText(Activity_comunicados.this, "Agregaste Algo", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void showUser(String anio, String curso) {
        this.anio  = anio;
        this.curso = curso;
        getComunicado(anio,curso);
    }

    //Controler
    @Override
    public void showComunicado(ArrayList<Comunicado> coupon) {
        RecyclerAdapterComunicados adapter = new RecyclerAdapterComunicados(coupon);
        recyclerView.setAdapter(adapter);
        progressDialog.dismiss();

        if(coupon.isEmpty()){
            Toast.makeText(this, "Vacio", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void isPreseptor(boolean isPreseptor) {
        if(isPreseptor)
            btAgregarComunicado.setVisibility(View.VISIBLE);
    }

    @Override
    public void getUser(String uid) {
        couponPresenter.getUser(uid);
    }

    @Override
    public void getComunicado(String anio,String curso) {
        couponPresenter.getCoupons(anio,curso);
    }
}
