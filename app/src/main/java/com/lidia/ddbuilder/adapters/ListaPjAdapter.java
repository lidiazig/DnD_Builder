package com.lidia.ddbuilder.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.ListaPersonajesActivity;
import com.lidia.ddbuilder.MainActivity;
import com.lidia.ddbuilder.PersonajeActivity;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.dialogs.DeleteDialog;
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.pojo.PersonajeLista;
import com.lidia.ddbuilder.pojo.Token;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaPjAdapter extends RecyclerView.Adapter<ListaPjAdapter.Elemento> {

    private Context context;
    private ArrayList<PersonajeLista> personajes;
    private int resource;
    private ListaPjAdapter adapter;
    private Token token = MainActivity.token;


    public ListaPjAdapter(Context context, ArrayList<PersonajeLista> personajes, int resource) {
        this.context = context;
        this.personajes = personajes;
        this.resource = resource;

    }

    @NonNull
    @Override
    public ListaPjAdapter.Elemento onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(context).inflate(resource, null);
        ListaPjAdapter.Elemento elemento = new ListaPjAdapter.Elemento(fila);
        return elemento;
    }


    @Override
    public void onBindViewHolder(@NonNull final ListaPjAdapter.Elemento holder, final int position) {
        holder.txtNombre.setText(personajes.get(position).getNombre());
        holder.txtClase.setText(personajes.get(position).getClase());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDialog deleteDialog = new DeleteDialog();
                deleteDialog.showDialog(context, personajes, position);
            }
        });

        holder.txtNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PersonajeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",personajes.get(position).getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }


    public class Elemento extends RecyclerView.ViewHolder {
        private TextView txtNombre;
        private TextView txtClase;
        private ImageButton btnDelete;

        public Elemento(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreListaPj);
            txtClase = itemView.findViewById(R.id.txtClaseListaPj);
            btnDelete = itemView.findViewById(R.id.btnDeleteListaPj);
        }

    }
}
