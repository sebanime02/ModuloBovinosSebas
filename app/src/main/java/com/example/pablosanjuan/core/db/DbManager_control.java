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


import com.example.pablosanjuan.core.vo.ControlVO;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sebastian on 04/06/15.
 */
public class DbManager_control {



    public static final String TABLE_NAME = "Control";
    public static final String  ID = "_id";
    public static final String  ID_BOVINO = "IdBovino";
    public static final String NOMBRE = "Nombre";
    public static final String FECHA = "Fecha";
    public static final String EVENTO = "Evento";
    public static final String DIAGNOSTICO = "Diagnostico";
    public static final String TRATAMIENTO = "Tratamiento";
    public static final String PRODUCTO = "Producto";
    public static final String DOSIS = "Dosis";
    public static final String FRECUENCIA = "Frecuencia";
    public static final String OBSERVACIONES = "Observaciones";

    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + ID + " integer primary key autoincrement,"
            + ID_BOVINO + " text not null,"
            + NOMBRE + " text not null,"
            + FECHA + " text not null,"
            + EVENTO + " text not null,"
            + DIAGNOSTICO + " text not null,"
            + TRATAMIENTO + " text not null,"
            + PRODUCTO + " text not null,"
            + DOSIS + " text not null,"
            + FRECUENCIA + " text not null,"
            + OBSERVACIONES + " text not null);";




    private DbHelper helper;
    private SQLiteDatabase db;
    private Cursor dbCursor;

    public DbManager_control(Context context){
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues (String id, String nombre, String fecha, String evento, String diagnostico, String tratamiento, String producto, String dosis, String frecuencia, String observaciones){
        ContentValues valores = new ContentValues();
        valores.put(ID_BOVINO, id);
        valores.put(NOMBRE, nombre);
        valores.put(FECHA, fecha);
        valores.put(EVENTO, evento);
        valores.put(DIAGNOSTICO, diagnostico);
        valores.put(TRATAMIENTO, tratamiento);
        valores.put(PRODUCTO, producto);
        valores.put(DOSIS, dosis);
        valores.put(FRECUENCIA, frecuencia);
        valores.put(OBSERVACIONES, observaciones);
        return valores;
    }

    public void inserta(String id, String nombre, String fecha, String evento, String diagnostico, String tratamiento, String producto, String dosis, String frecuencia, String observaciones){
        Log.i("sebas", "entra a guardar");
        db.insert(TABLE_NAME, null, generarContentValues(id, nombre, fecha, evento, diagnostico, tratamiento, producto, dosis, frecuencia, observaciones));
    }

    public Cursor cargarCursorRegistro(){
        String[] columnas = new String[]{ID_BOVINO, NOMBRE, FECHA, EVENTO, DIAGNOSTICO, TRATAMIENTO, PRODUCTO, DOSIS,FRECUENCIA,OBSERVACIONES};
        return db.query(TABLE_NAME, columnas, null, null, null, null, null);
    }

    public List<ControlVO> getRegistros() {
        List<ControlVO> listaRegistros = null;
        String[] columnas = new String[]{ID_BOVINO, NOMBRE, FECHA, EVENTO,DIAGNOSTICO, TRATAMIENTO, PRODUCTO, DOSIS,FRECUENCIA, OBSERVACIONES};
        dbCursor = db.query(TABLE_NAME, columnas, null, null, null, null, null);
        if (dbCursor.getCount() > 0) {
            listaRegistros = new ArrayList<ControlVO>();
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                ControlVO registro = new ControlVO();
                registro.setId((dbCursor.getString(0)));
                registro.setNombre(dbCursor.getString(1));
                registro.setFecha(dbCursor.getString(2));
                registro.setEvento(dbCursor.getString(3));
                registro.setDiagnostico(dbCursor.getString(4));
                registro.setTratamiento(dbCursor.getString(5));
                registro.setProducto(dbCursor.getString(6));
                registro.setDosis(dbCursor.getString(7));
                registro.setFrecuencia(dbCursor.getString(8));
                registro.setObservaciones(dbCursor.getString(9));

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
