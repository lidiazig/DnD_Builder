package com.lidia.ddbuilder.dialogs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Inventario;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;
import com.lidia.ddbuilder.ui.fragments.InventarioFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventarioDialog extends DialogFragment {
    private EditText txtNombre, txtUnidades;
    private Button btnSave;
    private Inventario objeto;
    private ArrayList<Inventario> inventario;

    public InventarioDialog () {
        super();
        this.inventario = InventarioFragment.inventario;
    }

    public InventarioDialog (Inventario objeto) {
        super();
        this.inventario = InventarioFragment.inventario;
        this.objeto = objeto;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_inventario, container, false);
        txtNombre = v.findViewById(R.id.txtNombreInventario);
        txtUnidades = v.findViewById(R.id.txtUnidadesInventario);
        btnSave = v.findViewById(R.id.btnSaveInventario);

        if(objeto!=null) {
            txtNombre.setText(objeto.getNombre());
            txtUnidades.setText(objeto.getCantidad());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(objeto==null){
                    objeto = new Inventario();
                    inventario.add(objeto);
                }
                objeto.setNombre(txtNombre.getText().toString());
                objeto.setCantidad(txtUnidades.getText().toString());

                getTargetFragment().onActivityResult(getTargetRequestCode(), 1, getActivity().getIntent());
                dismiss();
                //saveObject();
            }
        });

        return v;
    }
/*
    private void saveObject() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Inventario> call = conexion.doSaveObject(inventario);
        call.enqueue(new Callback<Inventario>() {
            @Override
            public void onResponse(Call<Inventario> call, Response<Inventario> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Inventario> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

 */
}
