package com.gomara.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.gomara.UI.Activity_Registrarse;

public class ChooseDialog extends DialogFragment {

    private String mensaje ;
    private String title;
    private Context context;

    public ChooseDialog(Context context, String title, String mensaje){
        this.mensaje = mensaje;
        this.title = title;
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Registrarse", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent e = new Intent(context, Activity_Registrarse.class);
                context.startActivity(e);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
