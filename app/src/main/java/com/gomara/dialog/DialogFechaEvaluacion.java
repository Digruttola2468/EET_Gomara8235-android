package com.gomara.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.gomara.R;
import com.gomara.Presenter.DialogFechaEvaluacionImpl;
import com.gomara.Presenter.DialogFechaEvaluacionPresenter;
import com.gomara.UI.Activity_materias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DialogFechaEvaluacion extends DialogFragment implements DialogFechaEvaluacionView {

    private Spinner spMateras;
    private EditText editFechaEvaluacion,editTemasEvaluacion;

    private ArrayList<String> materias;
    private String anio,curso;
    private Context context;
    public DialogFechaEvaluacion(Context context, ArrayList<String> materias, String anio, String curso){
        this.materias = materias;
        this.anio = anio;
        this.curso = curso;
        this.context = context;
    }

    private DialogFechaEvaluacionPresenter evaluacion;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getLayoutInflater().inflate(R.layout.dialog_fecha_evaluacion,null);
        builder.setTitle("Agregar Fecha Evaluacion");

        spMateras = view.findViewById(R.id.spMaterias_fechaEvaluacion);
        editFechaEvaluacion = view.findViewById(R.id.editText_fecha_evaluacion);
        editTemasEvaluacion = view.findViewById(R.id.editTextTemas_fechaEvaluacion);

        evaluacion = new DialogFechaEvaluacionImpl(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                materias);

        spMateras.setAdapter(adapter);

        builder.setView(view)
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            String fecha = editFechaEvaluacion.getText().toString();
                            String temas = editTemasEvaluacion.getText().toString();
                            String materia = spMateras.getSelectedItem().toString();

                            setFechaEvaluacion(anio,curso,materia,fecha,temas);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }


    @Override
    public void setFechaEvaluacion(String anio, String curso, String materia, String fecha, String temas) {
        evaluacion.setFechaEvaluacion(anio,curso,materia,fecha,temas);
    }

    @Override
    public void onSuccess(String mens) {
        Toast.makeText(context, mens, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String mens) {
        Toast.makeText(context, mens, Toast.LENGTH_SHORT).show();
    }
}
