package com.example.pablosanjuan.core;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;

import com.example.pablosanjuan.core.adapters.MyAdapter;
import com.example.pablosanjuan.core.adapters.MyAdapter_manejo;

import com.example.pablosanjuan.core.db.DbManager_inventario;
import com.example.pablosanjuan.core.db.DbManager_manejo;

import com.example.pablosanjuan.core.vo.ManejoVO;
/**
 * Created by sebastian on 07/06/15.
 */
public class Fragment6 extends Fragment implements View.OnClickListener {


    private Button b_borrar6,btn_add_manejo;
    ListView lista;
    MyAdapter_manejo adapter;
    DbManager_manejo manager6;
    private List<ManejoVO> listaRegistros;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment6, container, false);

        manager6 = new DbManager_manejo(getActivity());
        lista = (ListView) rootView.findViewById(R.id.lista6);
        listaRegistros = manager6.getRegistros();
        adapter = new MyAdapter_manejo(getActivity(), listaRegistros);
        lista.setAdapter(adapter);
        btn_add_manejo = (Button) rootView.findViewById(R.id.btn_agregar_manejo);
        b_borrar6 = (Button) rootView.findViewById(R.id.btn_borrar6);
        btn_add_manejo.setOnClickListener(this);
        b_borrar6.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_agregar_manejo:
                Intent intent = new Intent(getActivity(), IngresarManejo.class);
                startActivity(intent);
                break;

            case R.id.btn_borrar6:
                manager6.borrarRegistros();
                break;
        }
    }


}
