package com.lidia.ddbuilder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Hechizo;

import java.util.ArrayList;

public class HechizosAdapter extends RecyclerView.Adapter<HechizosAdapter.Elemento> {

    private Context context;
    private ArrayList<String> hechizos;
    private int resource;

    public HechizosAdapter(Context context, ArrayList<String> hechizos, int resource) {
        this.context = context;
        this.hechizos = hechizos;
        this.resource = resource;
    }

    @NonNull
    @Override
    public Elemento onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(context).inflate(resource, null);
        Elemento elemento = new Elemento(fila);
        return elemento;
    }

    @Override
    public void onBindViewHolder(@NonNull Elemento holder, int position) {
        holder.txtNombre.setText(hechizos.get(position));
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
