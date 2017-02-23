package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by juanjosecanotena on 31/1/17.
 */

public class BaseDatosUsuarios extends SQLiteOpenHelper {

  //  static ArrayList<RegistroIMC> registroIMCArrayList;

    private final String sqlCreacionTablaUsuarios =
            "CREATE TABLE USUARIOS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "usuario TEXT, " +
                    "password TEXT, " +
                    "nombre TEXT, " +
                    "apellidos TEXT, " +
                    "ultimoPeso REAL, " +
                    "altura REAL)";

    private final String sqlCreacionTablaHistorial =
            "CREATE TABLE REGISTROS (" +
                    "idRegistro INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "idUsuario INTEGER, " +
                    "fecha TEXT, " +
                    "estado TEXT, " +
                    "imc REAL, " +
                    "peso REAL, " +
                    "altura REAL)";


    public BaseDatosUsuarios(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreacionTablaHistorial);
        db.execSQL(sqlCreacionTablaUsuarios);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void cerrarBaseDatos(SQLiteDatabase database){

        database.close();
    }

    public int insertarUsuario(Usuario usuario){

        int idUsuario=0;
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("INSERT INTO USUARIOS (usuario, password, nombre, apellidos, ultimoPeso, altura)" +
                " VALUES (" +
                "'"+usuario.getUsuario()+"',"+
                "'"+usuario.getPassword()+"',"+
                "'"+usuario.getNombre()+"',"+
                "'"+usuario.getApellidos()+"',"+
                Float.toString(usuario.getUltimoPeso())+","+
                Float.toString(usuario.getAltura())+")");

        idUsuario=seleccionarUsuario(usuario.getUsuario()).getId();

        this.cerrarBaseDatos(database);

        return idUsuario;
    }

    public void insertarRegistroIMC(RegistroIMC registroIMC){

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("INSERT INTO REGISTROS (idUsuario , fecha, estado, imc, peso, altura)" +
                " VALUES (" +
                Integer.toString(registroIMC.getIdUsuario())+","+
                "'"+registroIMC.getFecha()+"',"+
                "'"+registroIMC.getEstado()+"',"+
                Float.toString(registroIMC.getImc())+","+
                Float.toString(registroIMC.getPeso())+","+
                Float.toString(registroIMC.getAltura())+")");

        this.cerrarBaseDatos(database);
    }



    public void actualizarUsuario(Usuario usuario){

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("UPDATE USUARIOS SET " +
                "usuario='"+usuario.getUsuario()+"',"+
                "password='"+usuario.getPassword()+"',"+
                "nombre='"+usuario.getNombre()+"',"+
                "apellidos='"+usuario.getApellidos()+"',"+
                "ultimoPeso="+Float.toString(usuario.getUltimoPeso())+","+
                "altura="+Float.toString(usuario.getAltura())+
                " WHERE id="+Integer.toString(usuario.getId()));
        this.cerrarBaseDatos(database);

    }

    public Usuario seleccionarUsuario(int idUsuario){

        Usuario usuario=new Usuario();
        String consulta = "SELECT id,usuario,password,nombre,apellidos,ultimoPeso,altura FROM USUARIOS WHERE id="+Integer.toString(idUsuario);
        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta,null);

        if (cursor != null && cursor.getCount() >0){
            cursor.moveToFirst();
            usuario.setId(cursor.getInt(0));
            usuario.setUsuario(cursor.getString(1));
            usuario.setPassword(cursor.getString(2));
            usuario.setNombre(cursor.getString(3));
            usuario.setApellidos(cursor.getString(4));
            usuario.setUltimoPeso(cursor.getFloat(5));
            usuario.setAltura(cursor.getFloat(6));
            this.cerrarBaseDatos(basedatos);
            return usuario;
        }
        else {
            this.cerrarBaseDatos(basedatos);
            return null;
        }
    }

    public Usuario seleccionarUsuario(String usuarioLogin){

        Usuario usuario=new Usuario();
        String consulta = "SELECT id,usuario,password,nombre,apellidos,ultimoPeso,altura FROM USUARIOS WHERE usuario='"+usuarioLogin+"'";
        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta,null);

        if (cursor != null && cursor.getCount() >0){

            cursor.moveToFirst();
            usuario.setId(cursor.getInt(0));
            usuario.setUsuario(cursor.getString(1));
            usuario.setPassword(cursor.getString(2));
            usuario.setNombre(cursor.getString(3));
            usuario.setApellidos(cursor.getString(4));
            usuario.setUltimoPeso(cursor.getFloat(5));
            usuario.setAltura(cursor.getFloat(6));
            this.cerrarBaseDatos(basedatos);
            return usuario;
        }
        else{
            this.cerrarBaseDatos(basedatos);
            return null;
        }
    }

    public void bajaUsuario(int idUsuario){

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM USUARIOS WHERE id="+Integer.toString(idUsuario));
        database.execSQL("DELETE FROM REGISTROS WHERE idUsuario="+Integer.toString(idUsuario));
        this.cerrarBaseDatos(database);
    }

    public ArrayList<RegistroIMC> seleccionarRegistrosIMC(int idUsuario){

        ArrayList<RegistroIMC> registroIMCArrayList = new ArrayList<RegistroIMC>();
        String consulta="SELECT fecha, estado, imc, peso, altura FROM REGISTROS WHERE idUsuario="+Integer.toString(idUsuario);
        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta,null);

        int nReg=cursor.getCount();

        if (cursor != null && nReg >0){

            cursor.moveToFirst();

            int pos=0;

            do {
                registroIMCArrayList.add(new RegistroIMC(0,0,cursor.getString(0),cursor.getString(1),cursor.getFloat(2),cursor.getFloat(3),cursor.getFloat(4)));
                cursor.moveToNext();
                pos++;
                }
            while (pos < nReg);

            this.cerrarBaseDatos(basedatos);
            return registroIMCArrayList;
        }
        else{
            this.cerrarBaseDatos(basedatos);
            return null;
        }

    }


}
