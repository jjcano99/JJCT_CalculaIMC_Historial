package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by juanjosecanotena on 23/2/17.
 */

public class Historial extends Activity {

    SharedPreferences preferences;
    BaseDatosUsuarios baseDatosUsuarios;
    ArrayList<RegistroIMC> registroIMCArrayList ;
    HistorialAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(getClass().getCanonicalName(), "INICIO DE ONCREATE");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.historial);

        //REFERENCIAR SELECTORES
        Button bVolver = (Button) findViewById(R.id.bHVolver);
        Button bFecha = (Button) findViewById(R.id.bHFecha);
        Button bIMC = (Button) findViewById(R.id.bHIMC);
        ListView lvHistorial =(ListView)findViewById(R.id.lvHistorial);

        //LEER PREFERENCIA idUsuario
        preferences = getSharedPreferences("Preferencias",MODE_PRIVATE);
        final int idUsuario = preferences.getInt("idUsuario",0);

        //REFERENCIAR BASE DE DATOS DE USUARIOS
        baseDatosUsuarios = new BaseDatosUsuarios(this,"miDB",null,2);

        //EXTRAEMOS LOS REGISTROS DEL USUARIO
        registroIMCArrayList=baseDatosUsuarios.seleccionarRegistrosIMC(idUsuario);

        adaptador = new HistorialAdapter(this,registroIMCArrayList);
        lvHistorial.setAdapter(adaptador);

        //EVENTOS BOTONES

        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Historial.this,MainActivity.class);
                startActivity(intent);
            }
        });

        bFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(registroIMCArrayList);
                ListView lvHistorial =(ListView)findViewById(R.id.lvHistorial);
                adaptador = new HistorialAdapter(Historial.this,registroIMCArrayList);
                lvHistorial.setAdapter(adaptador);


            }
        });

        bIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(registroIMCArrayList, new ComparaIMC());
                ListView lvHistorial =(ListView)findViewById(R.id.lvHistorial);
                adaptador = new HistorialAdapter(Historial.this,registroIMCArrayList);
                lvHistorial.setAdapter(adaptador);

            }
        });



    }



}
