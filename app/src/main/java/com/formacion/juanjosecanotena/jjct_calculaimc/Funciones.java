package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by juanjosecanotena on 9/2/17.
 */

public class Funciones {

    Funciones(){}

    public void  confirmarSalir(final Activity activity){

        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("SALIR");
        alertDialog.setMessage("¿Estás seguro de que deseas salir?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO SALIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SALIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //activity.finish();
                int id = android.os.Process.myPid();
                android.os.Process.killProcess(id);
            }
        });
        alertDialog.show();
    }

    public void cerrarSesion(Activity activity,SharedPreferences preferences){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("idUsuario", 0);
        editor.commit();
        Intent intent = new Intent( activity, Inicio.class);
        activity.startActivity(intent);
    }




}
