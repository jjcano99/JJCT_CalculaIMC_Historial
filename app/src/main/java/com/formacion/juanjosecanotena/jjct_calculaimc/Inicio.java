package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by juanjosecanotena on 31/1/17.
 */

public class Inicio extends Activity {

    //Actividad configurada como de arranque en el MANIFEST
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //LEEMOS LA PREFERENCIA idUsuario para saber si el usuario está EN SESIÓN (YA AUTENTICADO)
        SharedPreferences preferences = getSharedPreferences("Preferencias",MODE_PRIVATE);
        int idUsuario = preferences.getInt("idUsuario",0);

        if (idUsuario == 0) { //USUARIO NO AUTENTICADO

            setContentView(R.layout.inicio);

            Button bRegistrarme = (Button) findViewById(R.id.bRegistrarme);
            Button bAcceder = (Button) findViewById(R.id.bAcceder);

            //EVENTO BOTÓN ACCEDER
            bAcceder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            Intent intent = new Intent((Activity) Inicio.this, Acceso.class);
                            startActivity(intent);
                } //Fin onClick
            });

            //EVENTO BOTÓN REGISTRARME
            bRegistrarme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent((Activity) Inicio.this, Registro.class);
                    startActivity(intent);
                } //Fin onClick
            });
        }
        else{ //USUARIO AUTENTICADO - Redirigimos a MainActivity

            Intent intent = new Intent((Activity) Inicio.this, MainActivity.class);
            intent.putExtra("idUsuario", idUsuario);
            startActivity(intent);

        }
    }
}
