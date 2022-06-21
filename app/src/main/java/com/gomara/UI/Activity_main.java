package com.gomara.UI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.ListaMain;
import com.gomara.adapter.RecyclerAdapterMain;
import com.gomara.dialog.AlertDialogs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Activity_main extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView txtMiPerfil;
    private Button btCerrarSesion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_main);
        btCerrarSesion = findViewById(R.id.btCerrarSesion_main);
        txtMiPerfil = findViewById(R.id.txtMiPerfil_main);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ArrayList<ListaMain> lista = new ArrayList<>();
        lista.add(new ListaMain(R.drawable.image_inasistencias,"Inasistencias"));
        lista.add(new ListaMain(R.drawable.image_horarios,"Ver Horarios"));
        lista.add(new ListaMain(R.drawable.comunicado_image,"Comunidados"));
        lista.add(new ListaMain(R.drawable.materias,"Materias"));
        //lista.add(new ListaMain(R.drawable.libreta,"Libretas"));

        RecyclerAdapterMain adapter = new RecyclerAdapterMain(lista);
        recyclerView.setAdapter(adapter);

        btCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();

                Intent i = new Intent(Activity_main.this, Activity_Login.class);
                startActivity(i);
            }
        });

        txtMiPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseFirestore cloud = FirebaseFirestore.getInstance();

                //
                ProgressDialog progressDialog = new ProgressDialog(Activity_main.this);
                progressDialog.create();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                progressDialog.show();

                cloud.collection("User").document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            String nombre = task.getResult().getString("nombre");
                            String email = task.getResult().getString("email");
                            String apellido = task.getResult().getString("apellido");
                            String anio = task.getResult().getString("anio");
                            String curso = task.getResult().getString("curso");

                            progressDialog.dismiss();

                            String mensaje =
                                    "Nombre: " + nombre + "\n" +
                                    "Apellido: " + apellido + "\n" +
                                    "Email: " + email + "\n" +
                                    "Año: " + anio + " Curso: " + curso.toUpperCase();

                            AlertDialogs dialogs = new AlertDialogs("My profile",mensaje);
                            dialogs.show(getSupportFragmentManager(),null);

                        }
                    }
                });



            }
        });

    }
}
