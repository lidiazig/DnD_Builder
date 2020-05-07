package com.lidia.ddbuilder.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Habilidad;


import java.util.ArrayList;


public class HabilidadesAdapter extends RecyclerView.Adapter<HabilidadesAdapter.Elemento> {
    private Context context;
    private ArrayList<Habilidad> habilidades;
    private int resource;
    private FragmentManager fragmentManager;

    public HabilidadesAdapter(Context context, ArrayList<Habilidad> habilidades, int resource, FragmentManager fragmentManager) {
        this.context = context;
        this.habilidades = habilidades;
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
        holder.txtNombre.setText(habilidades.get(position).getNombre());
        holder.txtMod.setText(habilidades.get(position).getModHabilidad());
        holder.txtCaracteristica.setText(habilidades.get(position).getCaracteristica());
        if(habilidades.get(position).isSoloEntrenamiento())
            holder.txtTrained.setText("Yes");
        else
            holder.txtTrained.setText("No");
        holder.txtRanks.setText(habilidades.get(position).getRangos());
        if(habilidades.get(position).isPenalizacion()) {
            holder.txtPenalty.isEnabled();
            holder.txtPenalty.setText(habilidades.get(position).getPenalizador());
        }
        holder.txtMods.setText(habilidades.get(position).getModVarios());
    }



    @Override
    public int getItemCount() {
        return habilidades.size();
    }

    public class Elemento extends RecyclerView.ViewHolder{
        private TextView txtNombre, txtMod, txtCaracteristica, txtTrained;
        private EditText txtRanks, txtPenalty, txtMods;

        public Elemento(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.lbNombreHabilidad);
            txtMod = itemView.findViewById(R.id.lbModHabilidad);
            txtCaracteristica = itemView.findViewById(R.id.lbAbilityHabilidad);
            txtTrained = itemView.findViewById(R.id.lbTrainedHabilidad);
            txtRanks = itemView.findViewById(R.id.txtRanksHabilidad);
            txtPenalty = itemView.findViewById(R.id.txtPenaltyHabilidad);
            txtMods = itemView.findViewById(R.id.txtModsHabilidad);
        }
    }
}
