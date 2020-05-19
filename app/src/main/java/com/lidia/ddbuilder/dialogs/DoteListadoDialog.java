package com.lidia.ddbuilder.dialogs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.adapters.DoteListadoAdapter;
import com.lidia.ddbuilder.pojo.Dote;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;
import com.lidia.ddbuilder.ui.fragments.DotesFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoteListadoDialog extends DialogFragment {
    private RecyclerView recyclerView;
    private ArrayList<Dote> dotes;
    private int elemento = R.layout.elemento_dialog_dote;
    private DotesFragment dotesFragment;

    public DoteListadoDialog (DotesFragment dotesFragment){
        super();
        this.dotesFragment = dotesFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_dote, container, false);

        recyclerView = v.findViewById(R.id.recyclerViewDialogDotes);
        dotes = new ArrayList<>();

        getDotes();

        // Create the AlertDialog object and return it
        return v;
    }

    private void getDotes() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Dote>> call = conexion.doGetDotes();
        call.enqueue(new Callback<ArrayList<Dote>>() {
            @Override
            public void onResponse(Call<ArrayList<Dote>> call, Response<ArrayList<Dote>> response) {
                //Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // Log.i("onSuccess", response.body().toString());

                        setDotesJSON(response.body());

                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                        DoteListadoAdapter adapter = new DoteListadoAdapter(getContext(), dotes, elemento, getFragmentManager(), dotesFragment);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Dote>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void setDotesJSON(ArrayList<Dote> response) {
        for (int i = 0; i < response.size(); i++) {
            Dote dote = new Dote();
            dote.setId(response.get(i).getId());
            dote.setNombre(response.get(i).getNombre());
            dote.setPrerrequisito(response.get(i).getPrerrequisito());
            dote.setDescripcion(response.get(i).getDescripcion());
            dotes.add(dote);
        }
    }
}
