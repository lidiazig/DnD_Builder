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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventarioDialog extends DialogFragment {
    private EditText txtNombre, txtUnidades;
    private Button btnSave;
    private Inventario inventario;

    public InventarioDialog (Inventario inventario) {
        super();
        this.inventario = inventario;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_inventario, container, false);
        txtNombre = v.findViewById(R.id.txtNombreInventario);
        txtUnidades = v.findViewById(R.id.txtUnidadesInventario);
        btnSave = v.findViewById(R.id.btnSaveInventario);
        if(savedInstanceState!=null) {
            txtNombre.setText(inventario.getNombre());
            txtUnidades.setText(inventario.getCantidad());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventario.setNombre(txtNombre.getText().toString());
                inventario.setCantidad(txtUnidades.getText().toString());

                saveObject();
            }
        });

        return v;
    }

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
}
