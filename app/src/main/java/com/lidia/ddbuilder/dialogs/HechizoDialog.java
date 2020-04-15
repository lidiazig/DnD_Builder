package com.lidia.ddbuilder.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Hechizo;


public class HechizoDialog extends DialogFragment {

    private TextView txtNombre, txtEscuela, txtDescriptor, txtComponentes, txtTiempo, txtRango, txtObjetivos, txtDuracion, txtSalvacion, txtResistencia, txtDescripcion;
    private Hechizo hechizo;

    public HechizoDialog (Hechizo hechizo) {
        super();
        this.hechizo = hechizo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_hechizo, container, false);

        txtNombre = v.findViewById(R.id.txtHechizoNameDialog);
        txtEscuela = v.findViewById(R.id.txtHechizoSchoolDialog);
        txtDescriptor = v.findViewById(R.id.txtHechizoDescriptorDialog);
        txtComponentes = v.findViewById(R.id.txtHechizoComponentsDialog);
        txtTiempo = v.findViewById(R.id.txtHechizoCastingDialog);
        txtRango =v.findViewById(R.id.txtHechizoRangeDialog);
        txtObjetivos = v.findViewById(R.id.txtHechizoTargetDialog);
        txtDuracion = v.findViewById(R.id.txtHechizoDurationDialog);
        txtSalvacion = v.findViewById(R.id.txtHechizoSaveDialog);
        txtResistencia = v.findViewById(R.id.txtHechizoResistanceDialog);
        txtDescripcion = v.findViewById(R.id.txtHechizoDescriptionDialog);

        txtNombre.setText(hechizo.getNombre());
        txtEscuela.setText(hechizo.getEscuela());
        txtDescriptor.setText(hechizo.getDescriptor());
        txtComponentes.setText(hechizo.getComponentes());
        txtTiempo.setText(hechizo.getTiempoLanzamiento());
        txtRango.setText(hechizo.getRango());
        txtObjetivos.setText(hechizo.getObjetivos());
        txtDuracion.setText(hechizo.getDuracion());
        txtSalvacion.setText(hechizo.getSalvacion());
        txtResistencia.setText(hechizo.getResistencia());
        txtDescripcion.setText(hechizo.getDescripcion());

        // Create the AlertDialog object and return it
        return v;
    }
}