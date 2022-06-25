package com.gomara.UI;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gomara.R;
import com.gomara.Presenter.RegistrarsePresenter;
import com.gomara.Presenter.RegistrarsePresenterImpl;
import com.gomara.dialog.AlertDialogs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity_Registrarse extends AppCompatActivity implements RegistrarseView{

    private Button btVolver,btRegistrarse;
    private EditText editEmail,editNewPassword,editResetPassword,editName,editNickname;
    private Spinner sp_anio,sp_curso;

    private ProgressDialog progressDialog;
    private RegistrarsePresenter presenter;

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

        presenter = new RegistrarsePresenterImpl(this);

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
                if(isEmpy() && isEquals()){
                    String email = editEmail.getText().toString();
                    String password = editNewPassword.getText().toString();
                    String nombre = editName.getText().toString();
                    String apellido = editNickname.getText().toString();
                    String anio = sp_anio.getSelectedItem().toString();
                    String curso = sp_curso.getSelectedItem().toString();

                    progressDialog = new ProgressDialog(Activity_Registrarse.this);
                    progressDialog.create();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    progressDialog.show();

                    createUser(email,password,nombre,apellido,anio,curso);

                }else if(!isEquals())
                    Toast.makeText(Activity_Registrarse.this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();

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
                ArrayAdapter<CharSequence> adapterCurso = new ArrayAdapter<>(Activity_Registrarse.this, R.layout.dropdownitem, curso);
                sp_curso.setAdapter(adapterCurso);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private boolean isEmpy(){
        return  !editEmail.getText().equals("") &&
                !editNewPassword.getText().equals("") &&
                !editResetPassword.getText().equals("") &&
                !editName.getText().equals("") &&
                !editNickname.getText().equals("") &&
                !sp_anio.getSelectedItem().toString().equals("-") &&
                !sp_curso.getSelectedItem().toString().equals("-");
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

    @Override
    public void onSuccess(String mens) {
        progressDialog.dismiss();
        cleanEditTexts();
        AlertDialogs dialogs = new AlertDialogs("SignUp",mens);
        dialogs.show(getSupportFragmentManager(),null);
    }

    @Override
    public void onFailure(Exception e) {
        AlertDialogs dialogs = new AlertDialogs("Error",e.getMessage());
        dialogs.show(getSupportFragmentManager(),null);
    }

    @Override
    public void createUser(String email, String password, String nombre, String apellido, String anio, String curso) {
        presenter.createUser(email, password, nombre, apellido, anio, curso);
    }
}
