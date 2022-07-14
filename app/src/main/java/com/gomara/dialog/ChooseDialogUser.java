package com.gomara.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.gomara.UI.Activity_Login;
import com.gomara.UI.Activity_settingUser;
import com.google.firebase.auth.FirebaseAuth;

public class ChooseDialogUser extends DialogFragment {

    private String title;
    private String mensaje;
    private Context context;

    public ChooseDialogUser(Context context,String title, String mensaje) {
        this.title = title;
        this.mensaje = mensaje;
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(mensaje);

        builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(context, Activity_settingUser.class);
                startActivity(intent);
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
