package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by juanjosecanotena on 31/1/17.
 */

public class Registro extends Activity {

    Usuario usuario;
    String nombre;
    String apellidos;
    String usuarioLogin;
    String password;
    BaseDatosUsuarios baseDatosUsuarios;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        //SELECTORES
        final Button bGuardar = (Button) findViewById(R.id.bGuardar);
        final EditText etNombre = (EditText) findViewById(R.id.etNombre);
        final EditText etApellidos = (EditText) findViewById(R.id.etApellidos);
        final EditText etUsuario = (EditText) findViewById(R.id.etUsuario);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvUsuarioNovalido = (TextView) findViewById(R.id.tvUsuarioNoValido);

        //INICIALIZACION
        tvUsuarioNovalido.setVisibility(View.INVISIBLE);
        baseDatosUsuarios = new BaseDatosUsuarios(this,"miDB",null,1);

        //EVENTO BOTON GUARDAR
        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Extraer campos
                nombre= etNombre.getText().toString();
                apellidos= etApellidos.getText().toString();
                usuarioLogin= etUsuario.getText().toString();
                password= etPassword.getText().toString();

                //Ocultar el teclado numérico
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(bGuardar.getWindowToken(), 0);

                if(nombre !="" && usuarioLogin !="" && password !=""){

                        //Comprobamos si existe ese usuario

                        usuario = new Usuario();
                        usuario=baseDatosUsuarios.seleccionarUsuario(usuarioLogin);

                        if (usuario != null){
                            tvUsuarioNovalido.setText("Usuario Existente");
                            tvUsuarioNovalido.setVisibility(View.VISIBLE);

                        }
                        else {
                            //Insertamos el usuario en la Base de Datos
                            usuario = new Usuario();
                            usuario.setNombre(nombre);
                            usuario.setApellidos(apellidos);
                            usuario.setUsuario(usuarioLogin);
                            usuario.setPassword(password);
                            int idUsuario = baseDatosUsuarios.insertarUsuario(usuario);
                            usuario.setId(idUsuario);

                            //Escribimos su Id en la las Preferencias
                            SharedPreferences preferences = getSharedPreferences("Preferencias",MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("idUsuario",idUsuario);
                            editor.commit();

                            //Redirigimos hacia MainActivity
                            Intent intent = new Intent((Activity) Registro.this, MainActivity.class);
                            startActivity(intent);

                        }
                }
                else {
                    tvUsuarioNovalido.setText("Rellena todos los campos");
                    tvUsuarioNovalido.setVisibility(View.VISIBLE);

                }//Fin If

            }//Fin OnClick

        });//Fin bGuardar
    }
}
