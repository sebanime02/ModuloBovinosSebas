package com.example.pablosanjuan.core;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class IngresarBovino extends ActionBarActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup rdgGrupo;
    private String var_genero="";
    private String var_fecha="";
    private EditText e_id, e_nomb;
    String[] bovino;
    int eleccion;
    private Calendar calendar;
    private int year, month, day;
    private Button btn_fecha;
    private ImageButton imgbtn;
    private String name2 = "";
    private String foto_rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresar_bovino);


        e_id = (EditText) findViewById(R.id.edt_id_bovino);
        e_nomb = (EditText) findViewById(R.id.edt_nombre_bovino);
        btn_fecha = (Button) findViewById(R.id.btn_fecha);
        rdgGrupo = (RadioGroup) findViewById(R.id.rdgGrupo);
        imgbtn = (ImageButton) findViewById(R.id.foto_bovino);

        rdgGrupo.setOnCheckedChangeListener(this);
        registerForContextMenu(imgbtn);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        if (checkedId == R.id.rdgMacho) {
            var_genero = "Macho";
        } else if (checkedId == R.id.rdgHembra) {
            var_genero = "Hembra";
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_siguiente:
                if(validar(e_id.getText().toString(),e_nomb.getText().toString(),var_fecha,var_genero)==false){
                    Toast.makeText(this,"Debe Llenar Todos Los Campos",Toast.LENGTH_LONG).show();
                }else {
                    bovino = new String[4];
                    Intent ir_reg = new Intent(this, IngresarBovino2.class);

                        bovino[0] = e_id.getText().toString();
                        bovino[1] = e_nomb.getText().toString();
                        bovino[2] = var_fecha;
                        bovino[3] = var_genero;

                    ir_reg.putExtra("bovino", bovino);
                    startActivity(ir_reg);
                }
                break;
            case R.id.btn_fecha:
                showDialog(999);
                break;
            case R.id.foto_bovino:

                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            DatePickerDialog dialogDate = new DatePickerDialog(this, myDateListener, year, month, day);
            dialogDate.getDatePicker().setMaxDate(new Date().getTime());
            return dialogDate;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        var_fecha = (new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year)).toString();
        btn_fecha.setText("Fecha Nacimiento: " + var_fecha);
    }

    public Boolean validar(String id, String nombre, String fecha, String genero) {
        if (id.equals("") || nombre.equals("") || fecha.equals("") || genero.equals("")) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nueva_foto:
                Intent intent_foto =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri output = Uri.fromFile(new File(name2));
                intent_foto.putExtra(MediaStore.EXTRA_OUTPUT, output);
                eleccion = 1;
                startActivityForResult(intent_foto, eleccion);
                return true;

            case R.id.opc_galeria:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                eleccion = 2;
                startActivityForResult(intent, eleccion);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                   ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//			name2 = Environment.getExternalStorageDirectory() + "/test.jpg";
        getMenuInflater().inflate(R.menu.menu_foto_perfil, menu);
    }

    @Override protected void onActivityResult(int requestCode,  int resultCode, Intent data) {
        if (requestCode == 1) {
            foto_rq = name2;
            imgbtn.setImageBitmap(BitmapFactory.decodeFile(foto_rq));

            new MediaScannerConnection.MediaScannerConnectionClient() {
                private MediaScannerConnection msc = null; {
                    msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
                }
                public void onMediaScannerConnected() {
                    msc.scanFile(foto_rq, null);
                }
                public void onScanCompleted(String path, Uri uri) {
                    msc.disconnect();
                }
            };

            name2 = foto_rq;

        }else if (requestCode == 2){
            if(data == null){
                Toast.makeText(getApplicationContext()	,"No se eligio la foto!", Toast.LENGTH_SHORT).show();
            }else{
                Uri selectedImage = data.getData();
                name2 = selectedImage.toString();
                imgbtn.setImageURI(selectedImage);
            }
        }
    }
}