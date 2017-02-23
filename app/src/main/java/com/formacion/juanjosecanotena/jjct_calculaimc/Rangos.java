package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


/**
 * Created by juanjosecanotena on 17/1/17.
 */

public class Rangos extends Activity {

    String peso;
    String altura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(getClass().getCanonicalName(), "INICIO DE ONCREATE");

        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        peso = extras.getString("PESO");
        altura = extras.getString("ALTURA");


        setContentView(R.layout.activity_rangos);

        Button bVolver = (Button) findViewById(R.id.bVolver);

        ListView lvRangos =(ListView)findViewById(R.id.lvRangos);

        IndicesAdapter adaptador = new IndicesAdapter(this,Resultados.indices);

        lvRangos.setAdapter(adaptador);

        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Rangos.this,Resultados.class);
                intent.putExtra("PESO", peso);
                intent.putExtra("ALTURA", altura);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putCharSequence("PESO", peso);
        savedInstanceState.putCharSequence("ALTURA", altura);

    }

}
