package com.lidia.ddbuilder.dialogs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Dote;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoteAddDialog extends DialogFragment {

    private RadioButton rbDote;
    private ArrayList<Dote> dotes;

    public DoteAddDialog (ArrayList<Dote> dotes){
        super();
        this.dotes = dotes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_add_dote, container, false);

        rbDote = v.findViewById(R.id.rbDoteAdd);

        // Create the AlertDialog object and return it
        return v;
    }
/*
    private void getDotes() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Dote>> call = conexion.doGetDotes();
        call.enqueue(new Callback<ArrayList<Dote>>() {
            @Override
            public void onResponse(Call<ArrayList<Dote>> call, Response<ArrayList<Dote>> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        setDotesJSON(response.body());
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
            dotes.add(dote);
            rbDote.setText(dote.getNombre());
        }
    }

 */
}
