package com.gomara.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Activity_comunicados extends AppCompatActivity implements ComunicadoView {

    private RecyclerView recyclerView;
    private Button btVolver;

    private ComunicadoPresenter couponPresenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicados);
        getSupportActionBar().hide();

        couponPresenter = new ComunicadoPresenterImp(this);

        recyclerView = findViewById(R.id.recyclerView_comunicados);
        btVolver = findViewById(R.id.btVolver_comunicados);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ProgressDialog progressDialog = new ProgressDialog(Activity_comunicados.this);
        progressDialog.create();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_comunicados.this,Activity_main.class);
                startActivity(i);
            }
        });

        db.collection("User").document(auth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    String anio = task.getResult().get("anio").toString();
                    String curso = task.getResult().get("curso").toString();

                    getComunicado(anio,curso);
                }
            }
        });


    }

    //Controler
    @Override
    public void showComunicado(ArrayList<Comunicado> coupon) {
        RecyclerAdapterComunicados adapter = new RecyclerAdapterComunicados(coupon);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void getComunicado(String anio,String curso) {
        couponPresenter.getCoupons(anio,curso);
    }
}
