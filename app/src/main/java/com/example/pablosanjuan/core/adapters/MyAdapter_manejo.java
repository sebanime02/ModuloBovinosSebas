package com.example.pablosanjuan.core.adapters;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.vo.InventarioVO;
import com.example.pablosanjuan.core.vo.ManejoVO;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by sebastian on 07/06/15.
 */
public class MyAdapter_manejo extends  BaseAdapter {


    private List<ManejoVO> listaManejoVOs;
    private LayoutInflater layoutInflater;
    private List<InventarioVO> listaInventarioVOs;

    public MyAdapter_manejo
            (Activity a, List<ManejoVO> listaManejoVOs) {
        super();
        this.listaManejoVOs = new ArrayList<ManejoVO>();
        if (listaManejoVOs != null){
            this.listaManejoVOs = listaManejoVOs;
        }
        layoutInflater = a.getLayoutInflater();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listaManejoVOs.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listaManejoVOs.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // TODO Auto-generated method stub

        view = layoutInflater.inflate(R.layout.elemento_manejo, null);

        String id = listaManejoVOs.get(position).getId();

        String nombre = listaManejoVOs.get(position).getNombre();
        // String nombre = listaControlVOs.get(position).getNombre();
        String fecha = listaManejoVOs.get(position).getFecha();
        String evento = listaManejoVOs.get(position).getEvento();


        TextView tv_registro1 = (TextView) view.findViewById(R.id.registromanejo1);
        TextView tv_registro2 = (TextView) view.findViewById(R.id.registromanejo2);
        TextView tv_registro3 = (TextView) view.findViewById(R.id.registromanejo3);
        TextView tv_registro4 = (TextView) view.findViewById(R.id.registromanejo4);

        tv_registro1.setText("Id: "+id);
        tv_registro2.setText(nombre);
        tv_registro3.setText("Evento de Manejo: "+evento);
        tv_registro4.setText(fecha);


        return view;
    }



}
