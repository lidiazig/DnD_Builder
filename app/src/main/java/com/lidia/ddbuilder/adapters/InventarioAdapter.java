package com.lidia.ddbuilder.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.dialogs.InventarioDialog;
import com.lidia.ddbuilder.pojo.Inventario;

import java.util.ArrayList;


public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.Elemento> {
    private Context context;
    private ArrayList<Inventario> inventario;
    private int resource;
    private FragmentManager fragmentManager;

    public InventarioAdapter(Context context, ArrayList<Inventario> inventario, int resource, FragmentManager fragmentManager) {
        this.context = context;
        this.inventario = inventario;
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
        holder.txtNombre.setText(inventario.get(position).getNombre());
        holder.txtUnidades.setText(inventario.get(position).getCantidad());
        holder.txtNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inventario i = new Inventario();
                i.setNombre(inventario.get(position).getNombre());
                i.setCantidad(inventario.get(position).getCantidad());

                InventarioDialog inventarioDialog = new InventarioDialog(i);
                inventarioDialog.getView();
                inventarioDialog.show(fragmentManager, "inventario");
                //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return inventario.size();
    }

    public class Elemento extends RecyclerView.ViewHolder{

        private TextView txtNombre, txtUnidades;
        private Button btnEliminar;

        public Elemento(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.lbNombreInventarioFila);
            txtUnidades = itemView.findViewById(R.id.lbUnidadesInventarioFila);
            btnEliminar = itemView.findViewById(R.id.btnEliminarInventarioFila);
        }
    }
}
