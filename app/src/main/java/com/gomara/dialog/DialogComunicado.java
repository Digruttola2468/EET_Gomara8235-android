package com.gomara.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.gomara.R;
import com.gomara.Presenter.DialogComunicadoPresenter;
import com.gomara.Presenter.DialogComunicadoPresenterImpl;

public class DialogComunicado extends DialogFragment implements DialogComunicadoView{

    private EditText editTitle,editContenido;
    private String anio,curso;
    private Context context;

    private DialogComunicadoPresenter presenter;

    public DialogComunicado(String anio, String curso, Context context) {
        this.anio = anio;
        this.curso = curso;
        this.context = context;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getLayoutInflater().inflate(R.layout.dialog_comunicado,null);
        builder.setTitle("Agregar Comunicado");

        editTitle = view.findViewById(R.id.editTitle_comunicado);
        editContenido = view.findViewById(R.id.editContenido_comunicado);

        presenter = new DialogComunicadoPresenterImpl(this);

        builder.setView(view)
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = editTitle.getText().toString();
                        String contenido = editContenido.getText().toString();
                        
                        if(!title.equals("") && !contenido.equals(""))
                            addComunicado(title,contenido,anio,curso);
                        else
                            Toast.makeText(context, "Completar los campos", Toast.LENGTH_SHORT).show();
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
    public void onSuccess(String mens) {
        Toast.makeText(context, mens, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String mens) {
        Toast.makeText(context, mens, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addComunicado(String title, String contenido, String anio, String curso) {
        presenter.addComunicado(title, contenido, anio, curso);
    }
}
