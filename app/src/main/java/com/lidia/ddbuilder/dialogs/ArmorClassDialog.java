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
import com.lidia.ddbuilder.pojo.ArmorClass;
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.ui.fragments.CaracteristicasFragment;


public class ArmorClassDialog extends DialogFragment {
    private EditText txtArmor, txtShield, txtDex, txtSize, txtNatural, txtDeflection, txtMisc;
    private Button btnSave;
    private ArmorClass armorClass;
    private Personaje personaje = PersonajeActivity.personaje;

    public ArmorClassDialog() {
        super();
        this.armorClass = personaje.getArmorClass();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_ac, container, false);

        txtArmor = v.findViewById(R.id.txtArmorDialog);
        txtShield = v.findViewById(R.id.txtShieldDialog);
        txtDex = v.findViewById(R.id.txtDexDialog);
        txtSize = v.findViewById(R.id.txtSizeDialog);
        txtNatural = v.findViewById(R.id.txtNaturalDialog);
        txtDeflection = v.findViewById(R.id.txtDeflectionDialog);
        txtMisc = v.findViewById(R.id.txtMiscDialog);
        btnSave = v.findViewById(R.id.btnSaveAC);

        if (armorClass.getArmor() != 0)
            txtArmor.setText("" + armorClass.getArmor());
        if (armorClass.getShield() != 0)
            txtShield.setText("" + armorClass.getShield());
        if (armorClass.getDex() != 0)
            txtDex.setText("" + armorClass.getDex());
        if (armorClass.getSize() != 0)
            txtSize.setText("" + armorClass.getSize());
        if (armorClass.getNaturalArmor() != 0)
            txtNatural.setText("" + armorClass.getNaturalArmor());
        if (armorClass.getDeflection() != 0)
            txtDeflection.setText("" + armorClass.getDeflection());
        if (armorClass.getMisc() != 0)
            txtMisc.setText("" + armorClass.getMisc());


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtArmor.getText().toString().equals(""))
                    armorClass.setArmor(Integer.parseInt(txtArmor.getText().toString()));
                else
                    armorClass.setArmor(0);

                if (!txtShield.getText().toString().equals(""))
                    armorClass.setShield(Integer.parseInt(txtShield.getText().toString()));
                else
                    armorClass.setShield(0);

                if (!txtDex.getText().toString().equals(""))
                    armorClass.setDex(Integer.parseInt(txtDex.getText().toString()));
                else
                    armorClass.setDex(0);

                if (!txtSize.getText().toString().equals(""))
                    armorClass.setSize(Integer.parseInt(txtSize.getText().toString()));
                else
                    armorClass.setSize(0);

                if (!txtNatural.getText().toString().equals(""))
                    armorClass.setNaturalArmor(Integer.parseInt(txtNatural.getText().toString()));
                else
                    armorClass.setNaturalArmor(0);

                if (!txtDeflection.getText().toString().equals(""))
                    armorClass.setDeflection(Integer.parseInt(txtDeflection.getText().toString()));
                else
                    armorClass.setDeflection(0);

                if (!txtMisc.getText().toString().equals(""))
                    armorClass.setMisc(Integer.parseInt(txtMisc.getText().toString()));
                else
                    armorClass.setMisc(0);


                getTargetFragment().onActivityResult(getTargetRequestCode(), 1, getActivity().getIntent());
                dismiss();
            }
        });

        // Create the AlertDialog object and return it
        return v;
    }
}
