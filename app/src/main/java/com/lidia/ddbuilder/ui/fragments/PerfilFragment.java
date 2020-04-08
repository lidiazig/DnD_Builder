package com.lidia.ddbuilder.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Clase;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PerfilFragment extends Fragment {

    private ArrayList<Clase> clases;
    private ArrayList<String> nombres = new ArrayList<String>();

    private EditText txtNombre, txtNivel, txtGenero, txtTamano, txtEdad, txtIdiomas;
    private Spinner spinnerClase, spinnerRaza, spinnerAlineamiento;
    private Button btnSubir, btnAnterior, btnSiguiente;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PerfilFragment newInstance(int index) {
        PerfilFragment fragment = new PerfilFragment();
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
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        spinnerClase = root.findViewById(R.id.spinnerClase);
        setSpinnerClase();
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

    private void setSpinnerClase(){
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Clase>> call = conexion.doGetClases();
        call.enqueue(new Callback<ArrayList<Clase>>() {
            @Override
            public void onResponse(Call<ArrayList<Clase>> call, Response<ArrayList<Clase>> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinClaseJSON(response.body());

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Clase>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void spinClaseJSON(ArrayList<Clase> response){
                for (int i = 0; i < response.size(); i++){
                    nombres.add(response.get(i).getClase());
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, nombres);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spinnerClase.setAdapter(spinnerArrayAdapter);

    }
}