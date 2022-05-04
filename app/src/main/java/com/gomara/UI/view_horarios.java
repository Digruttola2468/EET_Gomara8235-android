package com.gomara.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gomara.R;
import com.ortiz.touchview.TouchImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class view_horarios extends Activity {

    //widget
    private TouchImageView img;
    private Button btBack,btInasistencias;
    private String stringAnio = "",stringCurso = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_horarios);

        //synchrony
        img = findViewById(R.id.ImageViewZoom);
        btBack = findViewById(R.id.btBackViewHorarios);
        btInasistencias = findViewById(R.id.btInasistencias);

        //verificar el archivo
        if(verifyEmpty()){
            LeerArchivo();//lee el archivo
            Horarios horarios = new Horarios( stringCurso,stringAnio);  //mandamos los datos

            if(horarios.Image() == 1) Toast.makeText(view_horarios.this,"NO EXISTE", Toast.LENGTH_LONG).show();
            else img.setImageResource(horarios.Image());
        }else {
            Intent i = new Intent( view_horarios.this , activity_login.class );startActivity(i);
        }

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( view_horarios.this , activity_login.class );
                startActivity(i);
            }
        });

        btInasistencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view_horarios.this, activity_inasistencias.class);
                startActivity(i);
            }
        });

    }



    public void LeerArchivo(){
        try {
            BufferedReader aux = new BufferedReader(new InputStreamReader(openFileInput("Gomara.txt")));

            //Se lee el texto del archivo y se almacena en dos variables
            stringAnio = aux.readLine();
            stringCurso = aux.readLine();

            Toast.makeText(this,"Anio: " + stringAnio + "   " + "Curso: " + stringCurso , Toast.LENGTH_LONG).show();

        }catch(IOException e){
            Log.e("Archivo","Error al leer el archivo de la memoria");
        }
    }

    public boolean verifyEmpty(){
        try {
            BufferedReader aux = new BufferedReader(new InputStreamReader(openFileInput("archivo.txt")));

            if(aux.readLine().equals("")) return true;  //esta vacio
            else return false;                          //esta lleno

        }catch(IOException e){
            Log.e("Archivo","Error al leer el archivo de la memoria");
            return true; //Esta vacio el archivo
        }
    }


static class Horarios {

    private String curso, anio;

    Horarios(String curso, String anio) {
        this.anio = anio;
        this.curso = curso;
    }

    Horarios() {
    }

    protected boolean isCorrect(String anio, String curso) {

        String allAnios[] = {"1", "2", "3", "4", "5", "6"};
        String allCursos[] = {"a", "b", "c", "d"};

        boolean isCorrectAnio = false;
        boolean isCorrectCurso = false;

        //Recorrer todos los anios
        for (int i = 0; i < allAnios.length; i++)
            if (anio.equals(allAnios[i])) isCorrectAnio = true;


        //Recorrer todos los cursos
        for (int i = 0; i < allCursos.length; i++)
            if (curso.equals(allCursos[i])) isCorrectCurso = true;


        if (isCorrectAnio && isCorrectCurso) return true;
        else return false;

    }

    protected int Image() {

        if (anio.equals("1")) {
            switch (curso) {
                case "a":
                    return R.drawable.horario_1a;
                case "b":
                    return R.drawable.horario_1b;
                case "c":
                    return R.drawable.horario_1c;
                case "d":
                    return R.drawable.horario_1d;
            }
        }

        if (anio.equals("2")) {
            switch (curso) {
                case "a":
                    return R.drawable.horario_2a;
                case "b":
                    return R.drawable.horario_2b;
                case "c":
                    return R.drawable.horario_2c;
                case "d":
                    return R.drawable.horario_2d;
            }
        }

        if (anio.equals("3")) {
            switch (curso) {
                case "a":
                    return R.drawable.horario_3a;
                case "b":
                    return R.drawable.horario_3b;
                case "c":
                    return R.drawable.horario_3c;
                case "d":
                    return R.drawable.horario_3d;
            }
        }

        if (anio.equals("4")) {
            switch (curso) {
                case "a":
                    return R.drawable.horario_4a;
                case "b":
                    return R.drawable.horario_4b;
                case "c":
                    return R.drawable.horario_4c;
                case "d":
                    return 1;
            }
        }

        if (anio.equals("5")) {

            switch (curso) {
                case "a":
                    return R.drawable.horario_5a;
                case "b":
                    return R.drawable.horario_5b;
                case "c":
                    return R.drawable.horario_5c;
                case "d":
                    return 1;
            }

        }

        if (anio.equals("6")) {

            switch (curso) {
                case "a":
                    return R.drawable.horario_6a;
            }

        }
        return 1;
    }

    }

}
