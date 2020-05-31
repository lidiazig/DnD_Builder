package com.lidia.ddbuilder.adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.PersonajeActivity;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Habilidad;
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.ui.fragments.PerfilFragment;


import java.util.ArrayList;
import java.util.Hashtable;


public class HabilidadesAdapter extends RecyclerView.Adapter<HabilidadesAdapter.Elemento> {
    private Context context;
    private ArrayList<Habilidad> habilidades;
    private int resource;
    private FragmentManager fragmentManager;
    private Personaje personaje;

    public HabilidadesAdapter(Context context, ArrayList<Habilidad> habilidades, int resource, FragmentManager fragmentManager) {
        this.context = context;
        this.habilidades = habilidades;
        this.resource = resource;
        this.fragmentManager = fragmentManager;
        this.personaje = PersonajeActivity.personaje;
    }

    @NonNull
    @Override
    public Elemento onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(context).inflate(resource, null);
        Elemento elemento = new Elemento(fila);
        return elemento;
    }

    @Override
    public void onBindViewHolder(@NonNull final Elemento holder, final int position) {
        holder.txtNombre.setText(habilidades.get(position).getNombre());

        holder.txtCaracteristica.setText(habilidades.get(position).getCaracteristica());

        if (habilidades.get(position).isSoloEntrenamiento() == 1)
            holder.txtTrained.setText("Yes");
        else
            holder.txtTrained.setText("No");

        if (habilidades.get(position).isPenalizacion() == 1) {
            holder.txtPenalty.setEnabled(true);
            holder.txtPenalty.setVisibility(View.VISIBLE);

        } else {
            holder.txtPenalty.setEnabled(false);
            holder.txtPenalty.setVisibility(View.INVISIBLE);
        }

        fillHabilidad(habilidades.get(position), holder);

        holder.txtRanks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!holder.txtRanks.getText().equals(""))
                        try {
                            Habilidad h = habilidades.get(position);
                            h.setRangos(Integer.parseInt(holder.txtRanks.getText().toString()));

                            fillHabilidad(h, holder);
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });

        holder.txtMods.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!holder.txtMods.getText().equals(""))
                        try {
                            Habilidad h = habilidades.get(position);
                            h.setModVarios(Integer.parseInt(holder.txtMods.getText().toString()));

                            fillHabilidad(h, holder);
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });

        holder.txtPenalty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!holder.txtPenalty.getText().equals(""))
                        try {
                            Habilidad h = habilidades.get(position);
                            h.setPenalizador(Integer.parseInt(holder.txtPenalty.getText().toString()));

                            fillHabilidad(h, holder);
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });
    }

    private int modCaracteristica(String caracteristica) {
        int num = 0;
        switch (caracteristica) {
            case "STR":
                num = personaje.getCaracteristicas().getFuerza();
                break;
            case "DEX":
                num = personaje.getCaracteristicas().getDestreza();
                break;
            case "CON":
                num = personaje.getCaracteristicas().getConstitucion();
                break;
            case "INT":
                num = personaje.getCaracteristicas().getInteligencia();
                break;
            case "WIS":
                num = personaje.getCaracteristicas().getSabiduria();
                break;
            case "CHA":
                num = personaje.getCaracteristicas().getCarisma();
                break;
        }

       return personaje.getCaracteristicas().getModificador(num);
    }

    private void fillHabilidad(Habilidad habilidad, Elemento holder) {
        int caracteristica = modCaracteristica(habilidad.getCaracteristica());
        int mod = 0;
        int rangos = habilidad.getRangos();
        int mods = habilidad.getModVarios();
        int penalty = habilidad.getPenalizador();

        mod = caracteristica + rangos + mods + penalty;

        holder.txtRanks.setText(rangos+"");
        holder.txtMods.setText(mods+"");
        holder.txtPenalty.setText(penalty+"");

        if (mod >= 0)
            holder.txtMod.setText("+"+mod);
        else
            holder.txtMod.setText(mod+"");
    }


    @Override
    public int getItemCount() {
        return habilidades.size();
    }

    public class Elemento extends RecyclerView.ViewHolder {
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
