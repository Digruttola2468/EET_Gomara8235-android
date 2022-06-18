package com.gomara.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gomara.R;

public class Activity_Login extends AppCompatActivity {

    private EditText editEmail,editPassword;
    private Button btLogIn,btRegistrer;

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
                if(!isEmpty()){
                    Toast.makeText(Activity_Login.this, "Correcto", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean isEmpty(){
        return  editEmail.getText().equals("") &&
                editPassword.getText().equals("");
    }

}
