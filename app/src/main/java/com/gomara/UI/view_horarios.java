package com.gomara.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gomara.R;
import com.gomara.Prosecer.Horarios;
import com.ortiz.touchview.TouchImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class view_horarios extends Activity {

    //widget
    private TouchImageView img;
    private Button btBack,btInasistencias,btActivity_main;

    private String stringAnio = "",stringCurso = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_horarios);

        //synchrony
        img = findViewById(R.id.ImageViewZoom);
        btBack = findViewById(R.id.btBackViewHorarios);
        btInasistencias = findViewById(R.id.btInasistencias);
        btActivity_main = findViewById(R.id.bt_activity_main);

        //verificar el archivo
        if(!verifyEmpty()){
            LeerArchivo();  //lee el archivo
            Horarios horarios = new Horarios(stringAnio,stringCurso);  //mandamos los datos
            img.setImageResource(horarios.Image());
        }

        btActivity_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view_horarios.this,activity_main.class);
                startActivity(i);
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( view_horarios.this , activity_login.class );
                i.putExtra("stringAnio" ,stringAnio);
                i.putExtra("stringCurso",stringCurso);
                startActivity(i);
            }
        });

        btInasistencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view_horarios.this, activity_inasistencias.class);
                i.putExtra("intentAnio",stringAnio);
                i.putExtra("intentCurso",stringCurso);
                startActivity(i);
            }
        });

    }



    private void LeerArchivo(){
        try {
            BufferedReader aux = new BufferedReader(new InputStreamReader(openFileInput("Gomara.txt")));

            //Se lee el texto del archivo y se almacena en dos variables
            stringAnio = aux.readLine();
            stringCurso = aux.readLine();

        }catch(IOException e){
            Log.e("Archivo","Error al leer el archivo de la memoria");
        }
    }

    private boolean verifyEmpty(){
        try {
            BufferedReader aux = new BufferedReader(new InputStreamReader(openFileInput("Gomara.txt")));

            if(aux.readLine().equals("")) return true;  //esta vacio
            else return false;                          //esta lleno

        }catch(IOException e){
            Log.e("Archivo","Error al leer el archivo de la memoria");
            return true; //Esta vacio el archivo
        }
    }



}
