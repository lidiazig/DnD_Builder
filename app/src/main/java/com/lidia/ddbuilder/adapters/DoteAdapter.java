package com.lidia.ddbuilder.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.dialogs.DoteVerDialog;
import com.lidia.ddbuilder.pojo.Dote;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoteAdapter extends RecyclerView.Adapter<DoteAdapter.Elemento> {
    private Context context;
    private ArrayList<Dote> dotes;
    private int resource;
    private FragmentManager fragmentManager;

    public DoteAdapter(Context context, ArrayList<Dote> dotes, int resource, FragmentManager fragmentManager) {
        this.context = context;
        this.dotes = dotes;
        this.resource = resource;
        this.fragmentManager = fragmentManager;
    }
    @NonNull
    @Override
    public DoteAdapter.Elemento onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(context).inflate(resource, null);
        DoteAdapter.Elemento elemento = new DoteAdapter.Elemento(fila);
        return elemento;
    }

    @Override
    public void onBindViewHolder(@NonNull Elemento holder, final int position) {
        holder.txtNombre.setText(dotes.get(position).getNombre());
        holder.txtNombre.setOnClickListener(new View.OnClickListener() {
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
        private TextView txtNombre;

        public Elemento(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtDoteFila);
        }
    }

    private void getDote(int position) {
        Dote dote = new Dote();
        dote.setNombre(dotes.get(position).getNombre());
        dote.setPrerrequisito(dotes.get(position).getPrerrequisito());
        dote.setDescripcion(dotes.get(position).getDescripcion());
        dote.setNotas(dotes.get(position).getNotas());

        DoteVerDialog doteVerDialog = new DoteVerDialog(dote);
        doteVerDialog.getView();
        doteVerDialog.show(fragmentManager, "dotever");
    }
/*
    private void getDote(int position) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Dote> call = conexion.doGetDote(dotes.get(position).getId());
        call.enqueue(new Callback<Dote>() {
            @Override
            public void onResponse(Call<Dote> call, Response<Dote> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        DoteVerDialog doteVerDialog = new DoteVerDialog(response.body());
                        doteVerDialog.getView();
                        doteVerDialog.show(fragmentManager, "dotever");
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

 */
}
