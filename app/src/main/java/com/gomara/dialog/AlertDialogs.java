package com.gomara.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;


public class AlertDialogs extends DialogFragment {

    String mensaje ;
    String title;
    public AlertDialogs(String title,String mensaje){
        this.mensaje = mensaje;
        this.title = title;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(mensaje);
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
