package com.lidia.ddbuilder.adapters;

import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.dialogs.HechizoDialog;
import com.lidia.ddbuilder.pojo.Hechizo;
import com.lidia.ddbuilder.pojo.HechizoBase;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HechizosAdapter extends RecyclerView.Adapter<HechizosAdapter.Elemento> {

    private Context context;
    private ArrayList<HechizoBase> hechizos;
    private int resource;
    private FragmentManager fragmentManager;

    public HechizosAdapter(Context context, ArrayList<HechizoBase> hechizos, int resource, FragmentManager fragmentManager) {
        this.context = context;
        this.hechizos = hechizos;
        this.resource = resource;
        this.fragmentManager = fragmentManager;
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
        holder.txtNombre.setText(hechizos.get(position).getNombre());
        holder.txtNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHechizo(position);
                //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getHechizo(int position) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Hechizo> call = conexion.doGetHechizo(hechizos.get(position).getId());
        call.enqueue(new Callback<Hechizo>() {
            @Override
            public void onResponse(Call<Hechizo> call, Response<Hechizo> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        HechizoDialog hechizoDialog = new HechizoDialog(response.body());
                        hechizoDialog.getView();
                        hechizoDialog.show(fragmentManager, "hechizo");
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Hechizo> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return hechizos.size();
    }

    public class Elemento extends RecyclerView.ViewHolder{
        private TextView txtNombre;

        public Elemento(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtConjuroFila);
        }
    }
}
