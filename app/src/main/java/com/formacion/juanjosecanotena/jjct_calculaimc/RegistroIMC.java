package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.icu.text.SimpleDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by juanjosecanotena on 22/2/17.
 */

public class RegistroIMC implements Comparable<RegistroIMC>{

    int idRegistro;
    int idUsuario;
    String fecha;
    String estado;
    float imc;
    float peso;
    float altura;

    public RegistroIMC(int idRegistro, int idUsuario, String fecha, String estado, float imc, float peso, float altura) {
        this.idRegistro = idRegistro;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.estado = estado;
        this.imc = imc;
        this.peso = peso;
        this.altura = altura;
    }

    public RegistroIMC() {
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }


    @Override
    public int compareTo(RegistroIMC o) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");

        try {
           Date date1 = dateFormat.parse(this.getFecha());
           Date date2 = dateFormat.parse(o.getFecha());

            return date1.compareTo(date2);

        }catch ( ParseException e ) {
            e.printStackTrace();
            return 0;
        }

    }
}
