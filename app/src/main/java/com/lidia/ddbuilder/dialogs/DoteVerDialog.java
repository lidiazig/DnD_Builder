package com.lidia.ddbuilder.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Dote;

public class DoteVerDialog extends DialogFragment {

    private TextView txtNombre, txtPrerrequisito, txtDescripcion, txtAdditional;
    private Dote dote;

    public DoteVerDialog(Dote dote) {
        super();
        this.dote = dote;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_ver_dote, container, false);

        txtNombre = v.findViewById(R.id.txtNombreDoteVer);
        txtPrerrequisito = v.findViewById(R.id.txtPrerrequisitoDoteVer);
        txtDescripcion = v.findViewById(R.id.txtDescripcionDoteVer);
        txtAdditional = v.findViewById(R.id.txtNotasDoteVer);

        txtNombre.setText(dote.getNombre());
        if (dote.getPrerrequisito().equals(""))
            txtPrerrequisito.setText("None");
        else
            txtPrerrequisito.setText(dote.getPrerrequisito());
        txtDescripcion.setText(dote.getDescripcion());
        txtAdditional.setText(dote.getNotas());

        // Create the AlertDialog object and return it
        return v;
    }
}
