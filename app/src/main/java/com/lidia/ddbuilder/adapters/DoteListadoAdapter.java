package com.lidia.ddbuilder.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.MainActivity;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.dialogs.DoteAddDialog;
import com.lidia.ddbuilder.pojo.Dote;
import com.lidia.ddbuilder.pojo.Token;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;
import com.lidia.ddbuilder.ui.fragments.DotesFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoteListadoAdapter extends RecyclerView.Adapter<DoteListadoAdapter.Elemento>{

    private Context context;
    private ArrayList<Dote> dotes;
    private int resource;
    private FragmentManager fragmentManager;
    private DotesFragment dotesFragment;
    private final int LISTADO_DOTE = 1;
    private Token token = MainActivity.token;

    public DoteListadoAdapter(Context context, ArrayList<Dote> dotes, int resource, FragmentManager fragmentManager, DotesFragment dotesFragment) {
        this.context = context;
        this.resource = resource;
        this.dotes = dotes;
        this.fragmentManager = fragmentManager;
        this.dotesFragment = dotesFragment;
    }
    @NonNull
    @Override
    public Elemento onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(context).inflate(resource, null);
        Elemento elemento = new Elemento(fila);
        return elemento;
    }

    @Override
    public void onBindViewHolder(@NonNull Elemento holder, final int position) {
        holder.lbNombre.setText(dotes.get(position).getNombre());
        holder.lbNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDote(position);
                //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dotes.size();
    }

    public class Elemento extends RecyclerView.ViewHolder{
        private TextView lbNombre;

        public Elemento(@NonNull View itemView) {
            super(itemView);
            lbNombre = itemView.findViewById(R.id.txtNombreDoteDialog);
        }
    }
    private void getDote(int position) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Dote> call = conexion.doGetDote(dotes.get(position).getId(), token);
        call.enqueue(new Callback<Dote>() {
            @Override
            public void onResponse(Call<Dote> call, Response<Dote> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        DoteAddDialog doteAddDialog = new DoteAddDialog(response.body());
                        doteAddDialog.getView();
                        doteAddDialog.setTargetFragment(dotesFragment, LISTADO_DOTE);
                        doteAddDialog.show(fragmentManager, "doteadd");
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Dote> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

}
