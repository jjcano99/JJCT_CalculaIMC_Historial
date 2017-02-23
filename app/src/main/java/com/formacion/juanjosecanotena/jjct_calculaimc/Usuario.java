package com.formacion.juanjosecanotena.jjct_calculaimc;

/**
 * Created by juanjosecanotena on 31/1/17.
 */

public class Usuario {

    private int id;
    private String usuario;
    private String password;
    private String nombre;
    private String apellidos;
    private float ultimoPeso;
    private float altura;

    public Usuario(String usuario, String password, String nombre, String apellidos, float ultimoPeso, float altura) {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ultimoPeso = ultimoPeso;
        this.altura = altura;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public float getUltimoPeso() {
        return ultimoPeso;
    }

    public void setUltimoPeso(float ultimoPeso) {
        this.ultimoPeso = ultimoPeso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
}
