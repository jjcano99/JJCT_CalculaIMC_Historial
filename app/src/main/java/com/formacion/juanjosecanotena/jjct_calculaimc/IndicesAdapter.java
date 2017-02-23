package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by juanjosecanotena on 17/1/17.
 */

public class IndicesAdapter extends ArrayAdapter<Indice> {

    public IndicesAdapter(Context context, ArrayList<Indice> indices){
        super(context,0,indices);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Indice indice = getItem(position);

        if ( convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_fila_rango,parent,false);
        }

        TextView tvRango=(TextView)convertView.findViewById(R.id.tvRango);
        TextView tvTextoRango=(TextView)convertView.findViewById(R.id.tvTextoRango);
        ImageView imgRango=(ImageView) convertView.findViewById(R.id.imgRango);

        String textoRango;
        if(position==0) {textoRango="< "+ Integer.toString(indice.limite+1);}
        else if (position==getCount()-1) {textoRango="> "+ Integer.toString(getItem(position-1).limite);}
        else {textoRango=Integer.toString(getItem(position-1).limite+1) + " - " + Integer.toString(indice.limite);}

        tvRango.setText(textoRango);
        tvTextoRango.setText(indice.literal);
        tvTextoRango.setTextColor(indice.color);
        imgRango.setImageResource(indice.imagen);

        return convertView;

    }

}
