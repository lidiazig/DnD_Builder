package com.lidia.ddbuilder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.dialogs.EquipoDialog;
import com.lidia.ddbuilder.pojo.Equipo;
import com.lidia.ddbuilder.ui.fragments.EquipoFragment;

import java.util.ArrayList;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.Elemento> {
    private Context context;
    private ArrayList<Equipo> equipo;
    private int resource;
    private FragmentManager fragmentManager;
    private EquipoFragment equipoFragment;

    public EquipoAdapter(Context context, ArrayList<Equipo> equipo, int resource, FragmentManager fragmentManager, EquipoFragment equipoFragment) {
        this.context = context;
        this.equipo = equipo;
        this.resource = resource;
        this.fragmentManager = fragmentManager;
        this.equipoFragment = equipoFragment;
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
        holder.lbNombre.setText(equipo.get(position).getNombre());
        holder.lbNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EquipoDialog equipoDialog = new EquipoDialog(equipo.get(position));
                equipoDialog.setTargetFragment(equipoFragment, 2);
                equipoDialog.getView();
                equipoDialog.show(fragmentManager, "equipo");
            }
        });
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipo.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return equipo.size();
    }

    public class Elemento extends RecyclerView.ViewHolder{
        private TextView lbNombre;
        private ImageButton btnEliminar;

        public Elemento(@NonNull View itemView) {
            super(itemView);
            lbNombre = itemView.findViewById(R.id.lbNombreElementoEquipo);
            btnEliminar = itemView.findViewById(R.id.btnEliminarEquipo);
        }
    }
}
