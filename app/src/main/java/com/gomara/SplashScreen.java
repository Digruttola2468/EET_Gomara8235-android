package com.gomara;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gomara.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplashScreen extends Activity {

    private ImageView img2;
    private String stringAnio,stringCurso;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_sceen);

        img2 = findViewById(R.id.imgLogoGomaraSplash);

        img2.setImageResource(R.drawable.gomara);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Si el archivo creado con los datos exite y los recupera , entrar a View_horarios.xml
                if(verifyEmpty()){
                    LeerArchivo();
                    Intent i = new Intent(SplashScreen.this,view_horarios.class);
                    i.putExtra("intentAnio",stringAnio);
                    i.putExtra("intentCurso",stringCurso);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashScreen.this , activity_login.class);
                    startActivity(i);
                    finish();
                }
            }
        },2000);

    }

    //verificar si esta vacio el archivo
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

    //Lee el archivo
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

}
