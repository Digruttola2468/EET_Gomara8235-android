package com.gomara.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gomara.R;
import com.gomara.Server.FirebaseAutentication;
import com.gomara.dialog.AlertDialogs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_Login extends AppCompatActivity {

    private EditText editEmail,editPassword;
    private Button btLogIn,btRegistrer;

    private FirebaseAutentication autentication = new FirebaseAutentication();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail       = findViewById(R.id.editEmail_login);
        editPassword    = findViewById(R.id.editPassword_login);
        btLogIn         = findViewById(R.id.bt_iniciarsesion_login);
        btRegistrer     = findViewById(R.id.bt_registrarse_login);


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
                    autentication.SignIn(Activity_Login.this,getSupportFragmentManager(),email,password);
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
        }

    }

    private boolean isEmpty(){
        return  !editEmail.getText().toString().equals("") &&
                !editPassword.getText().toString().equals("");
    }

}
