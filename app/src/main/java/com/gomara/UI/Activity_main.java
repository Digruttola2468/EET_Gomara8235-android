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
import com.gomara.Presenter.MainPresenter;
import com.gomara.Presenter.MainPresenterImpl;
import com.gomara.Prosecer.ListaMain;
import com.gomara.adapter.RecyclerAdapterMain;
import com.gomara.dialog.AlertDialogs;
import com.gomara.dialog.ChooseDialogSignOut;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Activity_main extends AppCompatActivity implements MainView{

    private RecyclerView recyclerView;
    private TextView txtMiPerfil;

    private MainPresenter presenter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_main);
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

        presenter = new MainPresenterImpl(this);

        txtMiPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                progressDialog = new ProgressDialog(Activity_main.this);
                progressDialog.create();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                progressDialog.show();

                FirebaseAuth auth = FirebaseAuth.getInstance();
                getUser(auth.getUid());
            }
        });

    }

    @Override
    public void showUser(String mensaje) {
        progressDialog.dismiss();
        ChooseDialogSignOut dialogs = new ChooseDialogSignOut(Activity_main.this,"My profile",mensaje);
        dialogs.show(getSupportFragmentManager(),null);
    }

    @Override
    public void getUser(String uid) {
        presenter.getUser(uid);
    }
}
