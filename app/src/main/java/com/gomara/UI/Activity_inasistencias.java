package com.gomara.UI;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.Alumno;
import com.gomara.Server.ServerFireBase;
import com.gomara.adapter.RecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Activity_inasistencias extends Activity {

    private RecyclerView recyclerView;
    private ServerFireBase serverFireBase = new ServerFireBase();
    private String stringAnio;
    private String stringCurso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inasistencias);

        recyclerView = findViewById(R.id.recycler_datos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LeerArchivo();
        serverFireBase.leerInasistencias(recyclerView,stringAnio,stringCurso );
    }

    public void LeerArchivo(){
        try {
            BufferedReader aux = new BufferedReader(new InputStreamReader(openFileInput("Gomara.txt")));

            //Se lee el texto del archivo y se almacena en dos variables
            stringAnio = aux.readLine();
            stringCurso = aux.readLine();

        }catch(IOException e){
            Log.e("Archivo","Error al leer el archivo de la memoria");
        }
    }
}
