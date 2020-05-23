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

import com.lidia.ddbuilder.MainActivity;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.adapters.HechizosAdapter;
import com.lidia.ddbuilder.pojo.HechizoBase;
import com.lidia.ddbuilder.pojo.Token;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConjurosFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<HechizoBase> hechizos = new ArrayList<>();
    private int elemento = R.layout.elemento_hechizo;
    private int clase;
    private Token token = MainActivity.token;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static ConjurosFragment newInstance(int index) {
        ConjurosFragment fragment = new ConjurosFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
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
        View root = inflater.inflate(R.layout.fragment_conjuros, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewHechizos);
        hechizos = new ArrayList<>();
        clase=PerfilFragment.personaje.getIdClase();
        //Cambio la última clase porque al coincidir hechizos de mago y hechicero solo están almacenados para una clase y así no estar duplicados
        if(clase==1 || clase==2 || clase==3 || clase==6 || clase==7 || clase==9 || clase==10)
            if(clase==10)
                clase=9;
            getHechizos();

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

    private void getHechizos() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<HechizoBase>> call = conexion.doGetHechizosClase(clase+1, token);
        call.enqueue(new Callback<ArrayList<HechizoBase>>() {
            @Override
            public void onResponse(Call<ArrayList<HechizoBase>> call, Response<ArrayList<HechizoBase>> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        setHechizosJSON(response.body());
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                        HechizosAdapter adapter = new HechizosAdapter(getContext(), hechizos, elemento, getFragmentManager());

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HechizoBase>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void setHechizosJSON(ArrayList<HechizoBase> response) {
        for (int i = 0; i < response.size(); i++) {
            HechizoBase hechizoBase = new HechizoBase();
            hechizoBase.setId(response.get(i).getId());
            hechizoBase.setNombre(response.get(i).getNombre());
            hechizoBase.setNivel(response.get(i).getNivel());
            hechizos.add(hechizoBase);
        }
    }
}
