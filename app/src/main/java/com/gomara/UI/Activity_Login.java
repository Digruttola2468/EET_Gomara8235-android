package com.gomara.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gomara.R;
import com.gomara.Presenter.ActivityLoginPresenter;
import com.gomara.Presenter.ActivityLoginPresenterImpl;
import com.gomara.dialog.AlertDialogs;
import com.gomara.dialog.ChooseDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_Login extends AppCompatActivity implements ActivityLoginView{

    private EditText editEmail,editPassword;
    private Button btLogIn,btRegistrer;
    private ProgressDialog progressDialog;
    private ActivityLoginPresenter presenter;

    private int conta = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail       = findViewById(R.id.editEmail_login);
        editPassword    = findViewById(R.id.editPassword_login);
        btLogIn         = findViewById(R.id.bt_iniciarsesion_login);
        btRegistrer     = findViewById(R.id.bt_registrarse_login);

        presenter = new ActivityLoginPresenterImpl(this);

        btRegistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Login.this,Activity_Registrarse.class);
                startActivity(i);
            }
        });

        btLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty()){
                    String email = editEmail.getText().toString();
                    String password = editPassword.getText().toString();

                    progressDialog = new ProgressDialog(Activity_Login.this);
                    progressDialog.create();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    progressDialog.show();

                    IniciarSesion(email,password);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if(user != null){
            Intent intent = new Intent(Activity_Login.this,Activity_main.class);
            startActivity(intent);
            finish();
        }

    }

    private boolean isEmpty(){
        return  !editEmail.getText().toString().equals("") &&
                !editPassword.getText().toString().equals("");
    }



    @Override
    public void onSuccess(String userId) {
        progressDialog.dismiss();
        Intent i = new Intent(Activity_Login.this, Activity_main.class);
        i.putExtra("User",userId);
        startActivity(i);
        finish();
    }

    @Override
    public void onFailure(Exception e) {
        progressDialog.dismiss();
        conta++;
        if(conta == 10){
            conta = 0;
            ChooseDialog dialog = new ChooseDialog(Activity_Login.this,"","Desea Registrarse?");
            dialog.show(getSupportFragmentManager(),null);
        }else {
            AlertDialogs dialogs = new AlertDialogs("Error", "El email o la contraseña son Incorrectas");
            dialogs.show(getSupportFragmentManager(), "TAG");
        }
    }

    @Override
    public void IniciarSesion(String email, String password) {
        presenter.IniciarSesion(email,password);
    }
}
