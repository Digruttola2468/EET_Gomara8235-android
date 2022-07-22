package com.gomara.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gomara.R;
import com.gomara.Presenter.ActivitySettingUserPresenter;
import com.gomara.Presenter.ActivitySettingUserPresenterImpl;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_settingUser extends AppCompatActivity implements ActivitySettingUserView{

    private TextView txtNombre,txtApellido,txtEmail;
    private EditText editNombre,editApellido,editEmail;
    private Button btGuardar,btSignOut;

    private String nombreActual;
    private String apellidoActual;
    private String emailActual;

    private ActivitySettingUserPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settinguser);

        txtNombre = findViewById(R.id.txtNombre_settingUser);
        txtApellido = findViewById(R.id.txtApellido_settingUser);
        txtEmail = findViewById(R.id.txtEmail_settingUser);
        editNombre = findViewById(R.id.editNombre_settingUser);
        editApellido = findViewById(R.id.editApellido_settingUser);
        editEmail = findViewById(R.id.editTextEmail_settingUser);
        btGuardar = findViewById(R.id.btEditar_settingUser);
        btSignOut = findViewById(R.id.btSignOut_settingUser);

        presenter = new ActivitySettingUserPresenterImpl(this);

        getUserActual();

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = editNombre.getText().toString();
                String apellido = editApellido.getText().toString();
                String email = editEmail.getText().toString();

                if(nombre.equals("") && apellido.equals("") && email.equals("")){
                    Toast.makeText(Activity_settingUser.this, "Campos Vacios", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(nombre.equals(""))
                    nombre = nombreActual;

                if (apellido.equals(""))
                    apellido = apellidoActual;

                if(email.equals(""))
                    email = emailActual;


                getUpdateUser(nombre,apellido,email);
            }
        });

        btSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                Intent i = new Intent(Activity_settingUser.this,Activity_Login.class);
                startActivity(i);
            }
        });

    }

    private void vaciarCampos(){
        editNombre.setText("");
        editApellido.setText("");
        editEmail.setText("");
    }

    @Override
    public void getUpdateUser(String nombre, String apellido,String email) {
        presenter.getUpdateUser(nombre, apellido,email);
    }

    @Override
    public void getUserActual() {
        presenter.getUserActual();
    }

    @Override
    public void onSuccessGetUserActual(String nombre, String apellido,String email) {
        nombreActual = nombre;
        apellidoActual = apellido;
        emailActual = email;

        txtNombre.setText("Nombre Actual: " + nombre);
        txtApellido.setText( "Apellido Actual: " + apellido);
        txtEmail.setText("Email Actual: " + email);
    }

    @Override
    public void onFailureGetUserActual(String mens) {
        Toast.makeText(this, mens, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onSuccess(String mens) {
        Toast.makeText(this, mens, Toast.LENGTH_SHORT).show();
        vaciarCampos();

        //Nos movemos al Activity_main
        Intent i = new Intent(Activity_settingUser.this,Activity_main.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onFailure(String mens) {
        Toast.makeText(this, mens, Toast.LENGTH_SHORT).show();
    }

}
