package com.lidia.ddbuilder.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.lidia.ddbuilder.ListaPersonajesActivity;
import com.lidia.ddbuilder.MainActivity;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.adapters.ListaPjAdapter;
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.pojo.PersonajeLista;
import com.lidia.ddbuilder.pojo.Token;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteDialog{

    private Dialog dialog;
    private Button btnDelete, btnCancel;
    private ArrayList<PersonajeLista> personajes;
    private Token token = MainActivity.token;
    private ListaPjAdapter adapter = ListaPersonajesActivity.adapter;
    private int position;


    public void showDialog(final Context context, ArrayList<PersonajeLista> personajes, final int position) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_delete_pj);
        this.personajes = personajes;
        this.position = position;

        btnDelete = dialog.findViewById(R.id.btnConfirmDeletePj);
        btnCancel = dialog.findViewById(R.id.btnCancelDeletePj);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePersonaje(position);
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void deletePersonaje(final int position) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Personaje> call = conexion.doDeletePersonaje(personajes.get(position).getId(), token);
        call.enqueue(new Callback<Personaje>() {
            @Override
            public void onResponse(Call<Personaje> call, Response<Personaje> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        personajes.remove(position);
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Personaje> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }
}
