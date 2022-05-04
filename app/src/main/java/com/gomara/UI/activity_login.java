package com.gomara.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gomara.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class activity_login extends AppCompatActivity {

    //Widgets
    private ImageView iconoGomara;
    private Spinner spAnio , spCurso;
    private Button    btLogin;


    private String stringAnio = "",stringCurso = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //widgets synchronize
        spAnio = findViewById(R.id.spAnio);
        spCurso = findViewById(R.id.spCurso);
        iconoGomara = findViewById(R.id.iconGomaraLogin);
        btLogin = findViewById(R.id.btLogin);

        btLogin.setText("Entrar");

        //Gomara school Icon
        iconoGomara.setImageResource(R.drawable.gomara);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get of the fields
                String Anio = spAnio.getSelectedItem().toString();
                String Curso = spCurso.getSelectedItem().toString();

                //if we get Username and password different of the null
                if (!Anio.equals("") && !Curso.equals("")) {

                    if (new view_horarios.Horarios().isCorrect(Anio, Curso)) {
                        //Guardar en su dispositivo sus Datos para luego entrar directamente al viewHorarios
                        guardarFichero(Anio,Curso);
                        Intent i = new Intent(activity_login.this , view_horarios.class);
                        i.putExtra("intentAnio",Anio);
                        i.putExtra("intentCurso",Curso);
                        startActivity(i);
                    } else
                        Toast.makeText(activity_login.this, "NO EXISTE", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(activity_login.this, "Campos Vacios", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] anio = getResources().getStringArray(R.array.ANIO);
        String[] curso = getResources().getStringArray(R.array.CURSO);

        ArrayAdapter<CharSequence> adapterAnio = new ArrayAdapter<>(activity_login.this, R.layout.dropdownitem, anio);
        ArrayAdapter<CharSequence> adapterCurso = new ArrayAdapter<>(activity_login.this, R.layout.dropdownitem, curso);

        spAnio.setAdapter(adapterAnio);
        spCurso.setAdapter(adapterCurso);
    }

    public void guardarFichero(String anio, String curso){
        try {

            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("Gomara.txt", Context.MODE_PRIVATE));

            //Lee el campo de texto y lo escribe en el fichero
            archivo.write(anio);
            archivo.write("\n");
            archivo.write(curso);

            archivo.close();

            LeerArchivo();

        }catch(IOException e){
            Log.e("Archivo","Error al escribir el archivo a la memoria");
        }
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
