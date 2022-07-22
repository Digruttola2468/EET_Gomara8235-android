package com.gomara.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.gomara.R;
import com.gomara.Presenter.ViewHorariosPresenter;
import com.gomara.Presenter.ViewHorariosPresenterImpl;
import com.gomara.Prosecer.Horarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ortiz.touchview.TouchImageView;


public class View_horarios extends Activity implements ViewHorariosView{

    //widget
    private TouchImageView img;
    private Button btBack,btActualizarHorario;
    private Spinner sp_anio,sp_curso;

    private ProgressDialog progressDialog;
    private ViewHorariosPresenter viewHorariosPresenter;

    private String anioString,cursoString;
    private Uri path;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_horarios);

        //synchrony
        img = findViewById(R.id.ImageViewZoom);
        btBack = findViewById(R.id.btBackViewHorarios);
        sp_anio = findViewById(R.id.spAnio_viewHorarios);
        sp_curso = findViewById(R.id.spCurso_viewHorarios);
        btActualizarHorario = findViewById(R.id.btActualizarHorario_horarios);

        btActualizarHorario.setVisibility(View.GONE);

        viewHorariosPresenter = new ViewHorariosPresenterImpl(this);

        progressDialog = new ProgressDialog(View_horarios.this);
        progressDialog.create();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        getAnioCurso(auth.getUid());
        getIsPreseptor(auth.getUid());

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
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long l) {

                String anio = sp_anio.getSelectedItem().toString();
                String curso = sp_curso.getSelectedItem().toString();

                if(!anio.equals("-") && !curso.equals("-")){
                    cursoString = curso;
                    anioString = anio;

                    Horarios horarios = new Horarios(anio,curso);  //mandamos los datos (anio,curso)
                    String path = horarios.Image();
                    getImage(path);

                    i = 0;
                    btActualizarHorario.setText("Actualizar Horario");

                    getSizeAllMaterias(anio,curso);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( View_horarios.this , Activity_main.class );
                startActivity(i);
                finish();
            }
        });

        btActualizarHorario.setOnClickListener( view -> {

            if(i == 0)
                cargarImagenes();

            if(i == 1){
                updateImage(anioString,cursoString,path);
                i = 0;
            }

        });
    }

    private void cargarImagenes() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(i.createChooser(i,"Seleccione app"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            path = data.getData();
            //view.setImageURI(path);

            i++;
            btActualizarHorario.setText("Guardar");

            Glide.with(View_horarios.this)
                    .load(path)                      //URI
                    .into(img);                    //ImageView
        }
    }


    @Override
    public void showAnioCurso(String anio, String curso) {
        progressDialog.dismiss();

        anioString = anio;
        cursoString = curso;

        Horarios horarios = new Horarios(anio,curso);  //mandamos los datos (anio,curso)
        String path = horarios.Image();
        getImage(path);


        getSizeAllMaterias(anio,curso);
    }

    @Override
    public void getAnioCurso(String uid) {
        viewHorariosPresenter.getAnioCurso(uid);
    }

    @Override
    public void getSizeAllMaterias(String anio, String curso) {
        viewHorariosPresenter.getSizeAllMaterias(anio,curso);
    }

    @Override
    public void showSizeAllMaterias(int size) {
        Toast.makeText(this, "Materias: " + size, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getIsPreseptor(String uid) {
        viewHorariosPresenter.getIsPreseptor(uid);
    }

    @Override
    public void showIsPreseptor(boolean isPreseptor) {
        if(isPreseptor)
            btActualizarHorario.setVisibility(View.VISIBLE);
    }

    @Override
    public void getImage(String path) {
        viewHorariosPresenter.getImage(path);
    }

    @Override
    public void updateImage(String anio, String curso, Uri uri) {
        progressDialog.show();
        viewHorariosPresenter.updateImage(anio, curso, uri);
    }

    @Override
    public void showImage(Uri uri) {
        Glide.with(View_horarios.this)
                .load(uri)                      //URI
                .into(img);
    }

    @Override
    public void onSuccess(String mens) {
        Toast.makeText(this, mens, Toast.LENGTH_SHORT).show();
        btActualizarHorario.setText("Actualizar Horario");
        progressDialog.dismiss();
    }

    @Override
    public void onFailure(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
