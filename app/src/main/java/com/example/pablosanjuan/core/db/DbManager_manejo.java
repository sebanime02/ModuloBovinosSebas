package com.example.pablosanjuan.core.db;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.pablosanjuan.core.vo.ManejoVO;


/**
 * Created by sebastian on 07/06/15.
 */
public class DbManager_manejo {

    public static final String TABLE_NAME = "Manejo";
    public static final String  ID = "_id";
    public static final String  ID_BOVINO = "IdBovino";
    public static final String NOMBRE = "Nombre";
    public static final String FECHA = "Fecha";
    public static final String EVENTO = "Evento";

    public static final String TRATAMIENTO = "Tratamiento";
    public static final String PRODUCTO = "Producto";

    public static final String OBSERVACIONES = "Observaciones";

    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + ID + " integer primary key autoincrement,"
            + ID_BOVINO + " text not null,"
            + NOMBRE + " text not null,"
            + FECHA + " text not null,"
            + EVENTO + " text not null,"

            + TRATAMIENTO + " text not null,"
            + PRODUCTO + " text not null,"

            + OBSERVACIONES + " text not null);";

    private DbHelper helper;
    private SQLiteDatabase db;
    private Cursor dbCursor;

    public DbManager_manejo(Context context){
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues (String id, String nombre, String fecha, String evento, String tratamiento, String producto,   String observaciones){
        ContentValues valores = new ContentValues();
        valores.put(ID_BOVINO, id);
        valores.put(NOMBRE, nombre);
        valores.put(FECHA, fecha);
        valores.put(EVENTO, evento);

        valores.put(TRATAMIENTO, tratamiento);
        valores.put(PRODUCTO, producto);

        valores.put(OBSERVACIONES, observaciones);
        return valores;
    }

    public void inserta(String id, String nombre, String fecha, String evento,  String tratamiento, String producto, String observaciones){
        Log.i("sebas", "entra a guardar");
        db.insert(TABLE_NAME, null, generarContentValues(id, nombre, fecha, evento , tratamiento, producto, observaciones));
    }

    public Cursor cargarCursorRegistro(){
        String[] columnas = new String[]{ID_BOVINO, NOMBRE, FECHA, EVENTO,  TRATAMIENTO, PRODUCTO, OBSERVACIONES};
        return db.query(TABLE_NAME, columnas, null, null, null, null, null);
    }

    public List<ManejoVO> getRegistros() {
        List<ManejoVO> listaRegistros = null;
        String[] columnas = new String[]{ID_BOVINO, NOMBRE, FECHA, EVENTO, TRATAMIENTO, PRODUCTO, OBSERVACIONES};
        dbCursor = db.query(TABLE_NAME, columnas, null, null, null, null, null);
        if (dbCursor.getCount() > 0) {
            listaRegistros = new ArrayList<ManejoVO>();
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                ManejoVO registro = new ManejoVO();
                registro.setId((dbCursor.getString(0)));
                registro.setNombre(dbCursor.getString(1));
                registro.setFecha(dbCursor.getString(2));
                registro.setEvento(dbCursor.getString(3));

                registro.setTratamiento(dbCursor.getString(4));
                registro.setProducto(dbCursor.getString(5));

                registro.setObservaciones(dbCursor.getString(6));

                listaRegistros.add(registro);
                dbCursor.moveToNext();
            }
        }
        dbCursor.close();
        return listaRegistros;
    }
    public void borrarRegistros(){
        db.delete(TABLE_NAME, null, null);
    }



}
