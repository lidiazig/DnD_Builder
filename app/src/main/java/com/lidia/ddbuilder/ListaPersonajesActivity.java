package com.lidia.ddbuilder;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lidia.ddbuilder.adapters.ListaPjAdapter;
import com.lidia.ddbuilder.dialogs.DeleteDialog;
import com.lidia.ddbuilder.pojo.PersonajeLista;
import com.lidia.ddbuilder.pojo.Token;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaPersonajesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<PersonajeLista> personajes = new ArrayList<>();
    private int elemento = R.layout.elemento_lista_pj;
    private Token token = MainActivity.token;
    public static ListaPjAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personajes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerListaPJ);

        getPersonajesUser();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaPersonajesActivity.this, PersonajeActivity.class));
            }
        });
    }

    private void getPersonajesUser() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<PersonajeLista>> call = conexion.doGetPersonajesUser(token, token);
        call.enqueue(new Callback<ArrayList<PersonajeLista>>() {
            @Override
            public void onResponse(Call<ArrayList<PersonajeLista>> call, Response<ArrayList<PersonajeLista>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        setPersonajesJSON(response.body());

                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ListaPersonajesActivity.this, 1);
                        adapter = new ListaPjAdapter(ListaPersonajesActivity.this, personajes, elemento);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PersonajeLista>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void setPersonajesJSON(ArrayList<PersonajeLista> response) {
        for (int i = 0; i < response.size(); i++) {
            PersonajeLista personaje = new PersonajeLista();
            personaje.setId(response.get(i).getId());
            personaje.setNombre(response.get(i).getNombre());
            personaje.setClase(response.get(i).getClase());
            personajes.add(personaje);
        }
    }
}
