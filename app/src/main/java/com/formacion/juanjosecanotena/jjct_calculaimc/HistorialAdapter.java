package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanjosecanotena on 23/2/17.
 */

public class HistorialAdapter extends ArrayAdapter<RegistroIMC> {

    public HistorialAdapter(Context context, ArrayList<RegistroIMC> registros) {
        super(context, 0, registros);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //return super.getView(position, convertView, parent);

        RegistroIMC registroIMC = getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.layout_fila_historial,parent,false);
        }
        TextView tvFHFecha=(TextView)convertView.findViewById(R.id.tvFHFecha);
        TextView tvFHPeso=(TextView)convertView.findViewById(R.id.tvFHPeso);
        TextView tvFHIMC=(TextView)convertView.findViewById(R.id.tvFHIMC);
       // TextView tvFHEstado=(TextView)convertView.findViewById(R.id.tvFHEstado);

        tvFHFecha.setText(registroIMC.fecha);
        tvFHPeso.setText(String.format("%.1f",registroIMC.peso));
        tvFHIMC.setText(String.format("%.1f",registroIMC.imc));
       // tvFHEstado.setText(registroIMC.estado);

        return convertView;
    }
}
