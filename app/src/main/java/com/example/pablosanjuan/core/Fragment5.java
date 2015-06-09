package com.example.pablosanjuan.core;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import com.example.pablosanjuan.core.adapters.MyAdapter_control;
import com.example.pablosanjuan.core.db.DbManager_control;
import com.example.pablosanjuan.core.vo.InventarioVO;
import com.example.pablosanjuan.core.vo.ControlVO;
import java.util.List;

/**
 * Created by Pablo Sanjuan on 28/05/2015.
 */
public class Fragment5 extends Fragment implements View.OnClickListener{

    private Button btn_add_control5,b_borrar5;
    ListView lista;
    MyAdapter_control adapter5;
    DbManager_control manager5;
    private List<ControlVO> listaRegistros;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment5, container, false);

        manager5 = new DbManager_control(getActivity());
        lista = (ListView) rootView.findViewById(R.id.lista5);
        listaRegistros = manager5.getRegistros();
        adapter5 = new MyAdapter_control(getActivity(), listaRegistros);
        lista.setAdapter(adapter5);
        btn_add_control5 = (Button) rootView.findViewById(R.id.btn_agregar_control);
        b_borrar5 = (Button) rootView.findViewById(R.id.btn_borrar5);
        btn_add_control5.setOnClickListener(this);
        b_borrar5.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_agregar_control:
                Intent intent = new Intent(getActivity(), IngresarControl.class);
                startActivity(intent);
                break;

            case R.id.btn_borrar5:
                manager5.borrarRegistros();
                break;
        }
    }
}