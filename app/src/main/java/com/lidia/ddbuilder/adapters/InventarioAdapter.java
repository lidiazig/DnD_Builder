package com.lidia.ddbuilder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.dialogs.InventarioDialog;
import com.lidia.ddbuilder.pojo.Inventario;
import com.lidia.ddbuilder.ui.fragments.InventarioFragment;

import java.util.ArrayList;


public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.Elemento> {
    private Context context;
    private ArrayList<Inventario> inventario;
    private int resource;
    private FragmentManager fragmentManager;
    private InventarioFragment inventarioFragment;

    public InventarioAdapter(Context context, ArrayList<Inventario> inventario, int resource, FragmentManager fragmentManager, InventarioFragment inventarioFragment) {
        this.context = context;
        this.inventario = inventario;
        this.resource = resource;
        this.fragmentManager = fragmentManager;
        this.inventarioFragment = inventarioFragment;
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
        holder.lbNombre.setText(inventario.get(position).getNombre());
        holder.lbUnidades.setText(inventario.get(position).getCantidad());
        holder.lbNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InventarioDialog inventarioDialog = new InventarioDialog(inventario.get(position));
                inventarioDialog.setTargetFragment(inventarioFragment, 2);
                inventarioDialog.getView();
                inventarioDialog.show(fragmentManager, "inventario");
                //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventario.remove(position);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return inventario.size();
    }

    public class Elemento extends RecyclerView.ViewHolder{

        private TextView lbNombre, lbUnidades;
        private Button btnEliminar;

        public Elemento(@NonNull View itemView) {
            super(itemView);
            lbNombre = itemView.findViewById(R.id.lbNombreInventarioFila);
            lbUnidades = itemView.findViewById(R.id.lbUnidadesInventarioFila);
            btnEliminar = itemView.findViewById(R.id.btnEliminarInventarioFila);
        }
    }
}
