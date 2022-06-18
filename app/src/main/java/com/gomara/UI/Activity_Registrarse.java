package com.gomara.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gomara.R;

public class Activity_Registrarse extends AppCompatActivity {

    private Button btVolver,btRegistrarse;
    private EditText editEmail,editNewPassword,editResetPassword,editName,editNickname;
    private Spinner sp_anio,sp_curso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        editEmail = findViewById(R.id.editEmail_registrarse);
        editNewPassword = findViewById(R.id.editNewPassword_registrarse);
        editResetPassword = findViewById(R.id.editResetPassword_registrarse);
        editName = findViewById(R.id.editName_registrarse);
        editNickname = findViewById(R.id.editApellido_registrarse);

        btVolver = findViewById(R.id.btVolver_registrarse);
        btRegistrarse = findViewById(R.id.btRegistrarse_registrarse);

        sp_anio = findViewById(R.id.spinner_anio_registrarse);
        sp_curso = findViewById(R.id.sp_curso_registrarse);

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Registrarse.this,Activity_Login.class);
                startActivity(i);
            }
        });

        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpy() && isEquals()){
                    Toast.makeText(Activity_Registrarse.this, "Correcto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Agregamos la lista de años
        String[] anio  = getResources().getStringArray(R.array.ANIO);
        ArrayAdapter<CharSequence> adapterAnio = new ArrayAdapter<>(Activity_Registrarse.this, R.layout.dropdownitem, anio);
        sp_anio.setAdapter(adapterAnio);

        sp_anio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] curso;
                switch (parent.getItemAtPosition(position).toString()){
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
                ArrayAdapter<CharSequence> adapterCurso = new ArrayAdapter<>(Activity_Registrarse.this, R.layout.dropdownitem, curso);
                sp_curso.setAdapter(adapterCurso);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp_curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Anio = sp_anio.getSelectedItem().toString();
                String Curso = sp_curso.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    private boolean isEmpy(){
        return editEmail.getText().equals("") &&
                editNewPassword.getText().equals("") &&
                editResetPassword.getText().equals("") &&
                editName.getText().equals("") &&
                editNickname.getText().equals("");
    }

    private boolean isEquals(){
        String stringNewPasswod = editNewPassword.getText().toString();
        String stringResetPassword = editResetPassword.getText().toString();
        Log.d("TAG","NEW: " + stringNewPasswod + "  Reset: " + stringResetPassword);
        return stringNewPasswod.equals(stringResetPassword);
    }

    private void cleanEditTexts(){
        editEmail.setText("");
        editNewPassword.setText("");
        editResetPassword.setText("");
        editName.setText("");
        editNickname.setText("");
    }

}
