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
import com.gomara.Server.FirebaseAutentication;
import com.gomara.dialog.AlertDialogs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

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
                if(isEmpy() && isEquals()){
                    String email = editEmail.getText().toString();
                    String password = editNewPassword.getText().toString();
                    String nombre = editName.getText().toString();
                    String apellido = editNickname.getText().toString();
                    String anio = sp_anio.getSelectedItem().toString();
                    String curso = sp_curso.getSelectedItem().toString();

                    /*
                    String mensaje =
                            "Email: " + email + "\n" +
                            "Password: " + password + "\n" +
                            "Nombre: " + nombre + "\n" +
                            "Apellido: " + apellido + "\n" +
                            "Anio: " + anio + "\n" +
                            "Curso: " + curso;

                    AlertDialogs dialogs = new AlertDialogs("New User",mensaje);
                    dialogs.show(getSupportFragmentManager(),"TAG");*/

                    ProgressDialog progressDialog = new ProgressDialog(Activity_Registrarse.this);
                    progressDialog.create();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    progressDialog.show();

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d("TAG","Email: " + email + "  Nombre: " + nombre + "  Anio: " + anio + "  Curso: " + curso);

                                Map<String,String> mapeo = new HashMap<>();
                                mapeo.put("email",email);
                                mapeo.put("nombre",nombre);
                                mapeo.put("apellido",apellido);
                                mapeo.put("anio",anio);
                                mapeo.put("curso",curso);

                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("User").document(task.getResult().getUser().getUid()).set(mapeo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressDialog.dismiss();

                                        AlertDialogs dialogs = new AlertDialogs("SignUp","Se agrego correctamente");
                                        dialogs.show(getSupportFragmentManager(),null);

                                        cleanEditTexts();
                                    }
                                });

                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            AlertDialogs dialogs = new AlertDialogs("Error",e.getMessage());
                            dialogs.show(getSupportFragmentManager(),null);
                        }
                    });
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

}
