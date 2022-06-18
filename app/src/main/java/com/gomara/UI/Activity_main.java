package com.gomara.UI;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.ListaMain;
import com.gomara.adapter.RecyclerAdapterMain;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Activity_main extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Spinner sp_anio;
    private Spinner sp_curso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_main);
        sp_anio = findViewById(R.id.spinner_main_anio);
        sp_curso = findViewById(R.id.spinner_main_curso);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ArrayList<ListaMain> lista = new ArrayList<>();
        lista.add(new ListaMain(R.drawable.image_inasistencias,"Inasistencias"));
        lista.add(new ListaMain(R.drawable.image_horarios,"Ver Horarios"));
        lista.add(new ListaMain(R.drawable.comunicado_image,"Comunidados"));
        lista.add(new ListaMain(R.drawable.materias,"Materias"));
        lista.add(new ListaMain(R.drawable.libreta,"Libretas"));

        RecyclerAdapterMain adapter = new RecyclerAdapterMain(lista);
        recyclerView.setAdapter(adapter);

        //Agregamos la lista de años
        String[] anio  = getResources().getStringArray(R.array.ANIO);
        ArrayAdapter<CharSequence> adapterAnio = new ArrayAdapter<>(Activity_main.this, R.layout.dropdownitem, anio);
        sp_anio.setAdapter(adapterAnio);

        sp_anio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] curso;
                switch (parent.getItemAtPosition(position).toString()){
                    case "1":
                    case "2":
                    case "3":
                        curso = new String[]{"a","b","c","d"};
                        break;

                    case "4":
                    case "5":
                        curso = new String[]{"a", "b", "c"};
                        break;

                    case "6":
                        curso = new String[]{"a"};
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + parent.getItemAtPosition(position).toString());
                }
                ArrayAdapter<CharSequence> adapterCurso = new ArrayAdapter<>(Activity_main.this, R.layout.dropdownitem, curso);
                sp_curso.setAdapter(adapterCurso);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp_curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Anio = sp_anio.getSelectedItem().toString();
                String Curso = sp_curso.getSelectedItem().toString();

                guardarFichero(Anio,Curso);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
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
    }
}
