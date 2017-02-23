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

public class Acceso extends Activity {

    String usuarioLogin;
    String password;
    BaseDatosUsuarios baseDatosUsuarios;
    Usuario usuario;
    int nIntento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceso);

        //SELECTORES
        final EditText etUsuario = (EditText) findViewById(R.id.etUsuarioAcceso);
        final EditText etPassword = (EditText) findViewById(R.id.etPasswordAcceso);
        final TextView tvUsuarioPasswordIncorrecto = (TextView) findViewById(R.id.tvUsuarioPasswordIncorrecto);
        final TextView tvIntento = (TextView) findViewById(R.id.tvIntento);
        final Button bGuardar = (Button) findViewById(R.id.bEntrar);

        //INICIALIZACIÓN
        tvUsuarioPasswordIncorrecto.setVisibility(View.INVISIBLE);
        baseDatosUsuarios = new BaseDatosUsuarios(this,"miDB",null,1);
        nIntento=1;
        tvIntento.setText("ACCESO - Primer intento");

        //EVENTO BOTÓN ENTRAR
        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nIntento <= 3) {

                    //PONER EL NÚMERO DE INTENTO
                    nIntento++;
                    tvIntento.setText( (nIntento==2)? "ACCESO - Segundo intento" :"ACCESO - Tercer intento" );

                    //EXTRAER USUARIO Y PASSWORD
                    usuarioLogin = etUsuario.getText().toString();
                    password = etPassword.getText().toString();

                    //OCULTAR EL TECLADO
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(bGuardar.getWindowToken(), 0);


                    if (usuarioLogin != "" && password != "") {

                        //Buscamos el usuarioLogin en la Base de Datos
                        usuario = new Usuario();
                        usuario = baseDatosUsuarios.seleccionarUsuario(usuarioLogin);

                        if (usuario != null) { //Existe ese usuarioLogin

                            //Comprobamos que la password es correcta
                            String usuarioPassword = usuario.getPassword();

                            if (usuarioPassword.equals(password)) { //Password correcta

                                //Escribimos su Id en la las Preferencias
                                SharedPreferences preferences = getSharedPreferences("Preferencias", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("idUsuario", usuario.getId());
                                editor.commit();

                                //Redirigimos hacia MainActivity
                                Intent intent = new Intent((Activity) Acceso.this, MainActivity.class);
                                startActivity(intent);

                            } else { //Password incorrecta

                                tvUsuarioPasswordIncorrecto.setText("Password incorrecta");
                                tvUsuarioPasswordIncorrecto.setVisibility(View.VISIBLE);

                            } //Fin if password correcta
                        } else { //No existe ese usuarioLogin

                            tvUsuarioPasswordIncorrecto.setText("No existe ese usuario");
                            tvUsuarioPasswordIncorrecto.setVisibility(View.VISIBLE);

                        }//Fin if existe usuarioLogin
                    } else {

                        tvUsuarioPasswordIncorrecto.setText("Rellena todos los campos");
                        tvUsuarioPasswordIncorrecto.setVisibility(View.VISIBLE);

                    }//Fin If rellenar campos
                }
                else{
                    //3 intentos fallidos -> Redirigimos hacia Inicio
                    Intent intent = new Intent((Activity) Acceso.this, Inicio.class);
                    startActivity(intent);
                }//if nIntento

                }//Fin OnClick
            });//Fin bGuardar

        etUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) v).setText("");
                tvUsuarioPasswordIncorrecto.setVisibility(View.INVISIBLE);
            }
        });
        etPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) v).setText("");
                tvUsuarioPasswordIncorrecto.setVisibility(View.INVISIBLE);
            }
        });

    }//Fin onCreate
}//Fin class
