package com.gomara.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private TextView txtNombre,txtCurso,txtAnio;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button btCerrarSesion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_main);
        txtNombre = findViewById(R.id.txtNombre_main);
        txtCurso = findViewById(R.id.txtCurso_main);
        txtAnio = findViewById(R.id.txtAnio_main);
        btCerrarSesion = findViewById(R.id.btCerrarSesion_main);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ArrayList<ListaMain> lista = new ArrayList<>();
        lista.add(new ListaMain(R.drawable.image_inasistencias,"Inasistencias"));
        lista.add(new ListaMain(R.drawable.image_horarios,"Ver Horarios"));
        lista.add(new ListaMain(R.drawable.comunicado_image,"Comunidados"));
        lista.add(new ListaMain(R.drawable.materias,"Materias"));
        lista.add(new ListaMain(R.drawable.libreta,"Libretas"));

        RecyclerAdapterMain adapter = new RecyclerAdapterMain(lista);
        recyclerView.setAdapter(adapter);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        db.collection("User").document(auth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    String nombre = task.getResult().getString("nombre");
                    String curso = task.getResult().getString("curso");
                    String anio = task.getResult().getString("anio");

                    Log.d("Tag","nombre: " + nombre + " Curso: " + curso + "anio: " + anio);

                    txtNombre.setText(nombre);
                    txtCurso.setText(curso);
                    txtAnio.setText(anio);
                }
            }
        });



        btCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();

                Intent i = new Intent(Activity_main.this, Activity_Login.class);
                startActivity(i);
            }
        });

    }



    /*
    public void guardarFichero(String anio, String curso){
        try {

            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("Gomara.txt", Context.MODE_PRIVATE));

            //Lee el campo de texto y lo escribe en el fichero
            archivo.write(anio);
            archivo.write("\n");
            archivo.write(curso);

            archivo.close();


        }catch(IOException e){
            Log.e("Archivo","Error al escribir el archivo a la memoria");
        }
    }*/
}
