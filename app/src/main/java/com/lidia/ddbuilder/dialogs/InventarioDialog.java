package com.lidia.ddbuilder.dialogs;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.lidia.ddbuilder.PersonajeActivity;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Inventario;

import com.lidia.ddbuilder.ui.fragments.InventarioFragment;

import java.util.ArrayList;


public class InventarioDialog extends DialogFragment {
    private EditText txtNombre, txtUnidades;
    private Button btnSave;
    private Inventario objeto;
    private ArrayList<Inventario> inventario;

    public InventarioDialog () {
        super();
        this.inventario = PersonajeActivity.personaje.getInventario();
    }

    public InventarioDialog (Inventario objeto) {
        super();
        this.inventario = PersonajeActivity.personaje.getInventario();
        this.objeto = objeto;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_inventario, container, false);
        txtNombre = v.findViewById(R.id.txtNombreInventario);
        txtUnidades = v.findViewById(R.id.txtUnidadesInventario);
        btnSave = v.findViewById(R.id.btnSaveInventario);

        if(objeto!=null) {
            txtNombre.setText(objeto.getNombre());
            txtUnidades.setText(objeto.getCantidad());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(objeto==null){
                    objeto = new Inventario();
                    inventario.add(objeto);
                }
                objeto.setNombre(txtNombre.getText().toString());
                objeto.setCantidad(txtUnidades.getText().toString());

                getTargetFragment().onActivityResult(getTargetRequestCode(), 1, getActivity().getIntent());
                dismiss();
                //saveObject();
            }
        });

        return v;
    }
}
