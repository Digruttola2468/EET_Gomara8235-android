package com.gomara.UI;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Presenter.MainPresenter;
import com.gomara.Presenter.MainPresenterImpl;
import com.gomara.Prosecer.ListaMain;
import com.gomara.adapter.RecyclerAdapterMain;
import com.gomara.dialog.ChooseDialogSignOut;
import com.google.firebase.auth.FirebaseAuth;
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

        presenter = new MainPresenterImpl(this);

        ArrayList<ListaMain> lista = new ArrayList<>();
        lista.add(new ListaMain(R.drawable.image_inasistencias,"Inasistencias"));
        lista.add(new ListaMain(R.drawable.image_horarios,"Ver Horarios"));
        lista.add(new ListaMain(R.drawable.comunicado_image,"Comunidados"));
        lista.add(new ListaMain(R.drawable.materias,"Materias"));

        RecyclerAdapterMain adapter = new RecyclerAdapterMain(lista);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

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
