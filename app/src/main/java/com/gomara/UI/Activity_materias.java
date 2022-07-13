package com.gomara.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Presenter.MateriasPresenter;
import com.gomara.Presenter.MateriasPresenterImpl;
import com.gomara.Prosecer.Materias;
import com.gomara.adapter.RecyclerAdapterMaterias;
import com.gomara.dialog.DialogFechaEvaluacion;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Activity_materias extends AppCompatActivity implements MateriasView{

    private RecyclerView recyclerView;
    private Button btVolver,btFechaEvaluacion;

    private MateriasPresenter presenter;
    private ProgressDialog progressDialog;

    private ArrayList<String> materias;
    private String anio,curso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        btVolver = findViewById(R.id.btVolver_materias);
        recyclerView = findViewById(R.id.recyclerView_materias);
        btFechaEvaluacion = findViewById(R.id.btFechaEvaluacion_materias);

        presenter = new MateriasPresenterImpl(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

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
            finish();
        });
        btFechaEvaluacion.setOnClickListener( (v) -> {
            DialogFechaEvaluacion fechaEvaluacion = new DialogFechaEvaluacion(Activity_materias.this,materias,anio,curso);
            fechaEvaluacion.show(getSupportFragmentManager(),null);
        });

        //REALTIME
        /*
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Cursos/" + anio + curso + "/Materias").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.e("ERROR",error.getMessage());
                    return;
                }
                for (DocumentChange documents : value.getDocumentChanges()) {
                    switch (documents.getType()){
                        case ADDED:
                        case MODIFIED:
                            String nombre = (String) documents.getDocument().getData().get("materia");
                            String fechas = (String) documents.getDocument().getData().get("evaluacion");
                            String temas = (String) documents.getDocument().getData().get("temas");

                            Log.d("TAG",nombre + fechas + temas);

                            break;


                        case REMOVED:
                            break;
                    }
                }

            }
        });*/

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
        this.anio = anio;
        this.curso = curso;
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
