package com.lidia.ddbuilder.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.adapters.HabilidadesAdapter;
import com.lidia.ddbuilder.pojo.Habilidad;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HabilidadesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Habilidad> habilidades = PerfilFragment.personaje.getHabilidades();
    private int elemento = R.layout.elemento_habilidades;
    private HabilidadesAdapter adapter;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static HabilidadesFragment newInstance(int index) {
        HabilidadesFragment fragment = new HabilidadesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_habilidades, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewHabilidades);
        if (habilidades.size() <= 0)
            getHabilidades();
        else {
            setAdapter();
        }
        /*
        final TextView textView = root.findViewById(R.id.txtNombre);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

         */
        return root;
    }

    private void getHabilidades() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Habilidad>> call = conexion.doGetHabilidades();
        call.enqueue(new Callback<ArrayList<Habilidad>>() {
            @Override
            public void onResponse(Call<ArrayList<Habilidad>> call, Response<ArrayList<Habilidad>> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        setHabilidadesJSON(response.body());
                        setAdapter();
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Habilidad>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void setAdapter(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        adapter = new HabilidadesAdapter(getContext(), habilidades, elemento, getFragmentManager());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setHabilidadesJSON(ArrayList<Habilidad> response) {
        for (int i = 0; i < response.size(); i++) {
            Habilidad habilidad = new Habilidad();
            habilidad.setId(response.get(i).getId());
            habilidad.setNombre(response.get(i).getNombre());
            habilidad.setCaracteristica(response.get(i).getCaracteristica());
            habilidad.setPenalizacion(response.get(i).isPenalizacion());
            habilidad.setSoloEntrenamiento(response.get(i).isSoloEntrenamiento());
            habilidades.add(habilidad);
        }
    }
}
