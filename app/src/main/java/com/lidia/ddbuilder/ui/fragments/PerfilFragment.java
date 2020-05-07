package com.lidia.ddbuilder.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Alineamiento;
import com.lidia.ddbuilder.pojo.Clase;
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.pojo.Raza;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PerfilFragment extends Fragment {

    public static Personaje personaje = new Personaje();

    private ArrayList<String> clases = new ArrayList<>();
    private ArrayList<String> alineamientos = new ArrayList<>();
    private ArrayList<String> razas = new ArrayList<>();

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

        txtNombre = root.findViewById(R.id.txtNombre);
        txtNivel = root.findViewById(R.id.txtNivel);
        txtGenero = root.findViewById(R.id.txtGenero);
        txtTamano = root.findViewById(R.id.txtTamano);
        txtEdad = root.findViewById(R.id.txtEdad);
        txtIdiomas = root.findViewById(R.id.txtIdiomas);

        btnSubir = root.findViewById(R.id.btnSubir);
        btnAnterior = root.findViewById(R.id.btnImagenAnterior);
        btnSiguiente = root.findViewById(R.id.btnImagenSiguiente);

        spinnerClase = root.findViewById(R.id.spinnerClase);
        spinnerAlineamiento = root.findViewById(R.id.spinnerAlineamiento);
        spinnerRaza = root.findViewById(R.id.spinnerRaza);
        setSpinnerClase();
        setSpinnerRaza();
        setSpinnerAlineamiento();


        txtNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtNombre.getText().equals(""))
                        personaje.setNombre(txtNombre.getText().toString());
                }
            }
        });
        txtNivel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtNivel.getText().equals(""))
                        personaje.setNivel(txtNivel.getText().toString());
                }
            }
        });
        txtGenero.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtGenero.getText().equals(""))
                        personaje.setGenero(txtGenero.getText().toString());
                }
            }
        });
        txtTamano.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtTamano.getText().equals(""))
                        personaje.setTamano(txtTamano.getText().toString());
                }
            }
        });
        txtEdad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtEdad.getText().equals(""))
                        personaje.setEdad(txtEdad.getText().toString());
                }
            }
        });
       /* txtIdiomas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!txtIdiomas.getText().equals(""))
                        personaje.setIdiomas(txtIdiomas.getText().toString());
                }
            }
        });

        */

        spinnerAlineamiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                personaje.setIdAlineamiento(spinnerAlineamiento.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerRaza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                personaje.setIdRaza(spinnerRaza.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerClase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                personaje.setIdClase(spinnerClase.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    private void setSpinnerAlineamiento() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Alineamiento>> call = conexion.doGetAlineamientos();
        call.enqueue(new Callback<ArrayList<Alineamiento>>() {
            @Override
            public void onResponse(Call<ArrayList<Alineamiento>> call, Response<ArrayList<Alineamiento>> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        spinAlineamientoJSON(response.body());

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Alineamiento>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void spinAlineamientoJSON(ArrayList<Alineamiento> response) {
        alineamientos.clear();
        for (int i = 0; i < response.size(); i++) {
            alineamientos.add(response.get(i).getAlineamiento());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, alineamientos);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerAlineamiento.setAdapter(spinnerArrayAdapter);

        if (personaje.getIdAlineamiento() >= 0)
            spinnerAlineamiento.setSelection(personaje.getIdAlineamiento(),false);
    }

    private void setSpinnerRaza() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Raza>> call = conexion.doGetRazas();
        call.enqueue(new Callback<ArrayList<Raza>>() {
            @Override
            public void onResponse(Call<ArrayList<Raza>> call, Response<ArrayList<Raza>> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        spinRazaJSON(response.body());

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Raza>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void spinRazaJSON(ArrayList<Raza> response) {
        razas.clear();
        for (int i = 0; i < response.size(); i++) {
            razas.add(response.get(i).getRaza());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, razas);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerRaza.setAdapter(spinnerArrayAdapter);

        if (personaje.getIdRaza() >= 0)
            spinnerRaza.setSelection(personaje.getIdRaza(),false);
    }

    private void setSpinnerClase() {
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

    private void spinClaseJSON(ArrayList<Clase> response) {
        clases.clear();
        for (int i = 0; i < response.size(); i++) {
            clases.add(response.get(i).getClase());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, clases);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerClase.setAdapter(spinnerArrayAdapter);

        if (personaje.getIdClase() >= 0)
            spinnerClase.setSelection(personaje.getIdClase(),false);
    }
}