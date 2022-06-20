package com.gomara.UI;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.gomara.R;
import com.gomara.Prosecer.Horarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ortiz.touchview.TouchImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class View_horarios extends Activity {

    //widget
    private TouchImageView img;
    private Button btBack;
    private Spinner sp_anio,sp_curso;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_horarios);

        //synchrony
        img = findViewById(R.id.ImageViewZoom);
        btBack = findViewById(R.id.btBackViewHorarios);
        sp_anio = findViewById(R.id.spAnio_viewHorarios);
        sp_curso = findViewById(R.id.spCurso_viewHorarios);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( View_horarios.this , Activity_main.class );
                startActivity(i);
            }
        });

        //Agregamos la lista de años
        String[] anio  = getResources().getStringArray(R.array.ANIO);
        ArrayAdapter<CharSequence> adapterAnio = new ArrayAdapter<>(View_horarios.this, R.layout.dropdownitem, anio);
        sp_anio.setAdapter(adapterAnio);

        sp_anio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] curso;
                switch (parent.getItemAtPosition(position).toString()){
                    case "-":
                        curso = new String[]{"-"};
                        break;
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
                ArrayAdapter<CharSequence> adapterCurso = new ArrayAdapter<>(View_horarios.this, R.layout.dropdownitem, curso);
                sp_curso.setAdapter(adapterCurso);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp_curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String anio = sp_anio.getSelectedItem().toString();
                String curso = sp_curso.getSelectedItem().toString();

                if(!anio.equals("-") && !curso.equals("-")){
                    Horarios horarios = new Horarios(anio,curso);  //mandamos los datos (anio,curso)
                    img.setImageResource(horarios.Image());
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ProgressDialog progressDialog = new ProgressDialog(View_horarios.this);
        progressDialog.create();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        db.collection("User").document(auth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();

                    String anio = task.getResult().get("anio").toString();
                    String curso = task.getResult().get("curso").toString();

                    Horarios horarios = new Horarios(anio,curso);  //mandamos los datos (anio,curso)
                    img.setImageResource(horarios.Image());
                }

            }
        });

    }


}
