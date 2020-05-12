package com.lidia.ddbuilder.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Salvacion;
import com.lidia.ddbuilder.ui.fragments.CaracteristicasFragment;

public class SalvacionesDialog extends DialogFragment {
    private EditText txtForBase, txtFortAbility, txtFortMagic, txtFortMisc, txtRefBase, txtRefAbility, txtRefMagic, txtRefMisc, txtVolBase, txtVolAbility, txtVolMagic, txtVolMisc;
    private Button btnSave;
    private Salvacion fortaleza, reflejos, voluntad;

    public SalvacionesDialog() {
        super();
        this.fortaleza = CaracteristicasFragment.fortaleza;
        this.reflejos = CaracteristicasFragment.reflejos;
        this.voluntad = CaracteristicasFragment.voluntad;
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
        View v = inflater.inflate(R.layout.dialog_salvaciones, container, false);

        txtForBase = v.findViewById(R.id.txtFortalezaBase);
        txtFortAbility = v.findViewById(R.id.txtFortalezaAbility);
        txtFortMagic = v.findViewById(R.id.txtFortalezaMagic);
        txtFortMisc = v.findViewById(R.id.txtFortalezaMisc);
        txtRefBase = v.findViewById(R.id.txtReflejosBase);
        txtRefAbility = v.findViewById(R.id.txtReflejosAbility);
        txtRefMagic = v.findViewById(R.id.txtReflejosMagic);
        txtRefMisc = v.findViewById(R.id.txtReflejosMisc);
        txtVolBase = v.findViewById(R.id.txtVoluntadBase);
        txtVolAbility = v.findViewById(R.id.txtVoluntadAbility);
        txtVolMagic = v.findViewById(R.id.txtVoluntadMagic);
        txtVolMisc = v.findViewById(R.id.txtVoluntadMisc);
        btnSave = v.findViewById(R.id.btnSaveSalvaciones);

        if (fortaleza.getBase() != 0)
            txtForBase.setText("" + fortaleza.getBase());
        if (fortaleza.getAbility() != 0)
            txtFortAbility.setText("" + fortaleza.getAbility());
        if (fortaleza.getMagic() != 0)
            txtFortMagic.setText("" + fortaleza.getMagic());
        if (fortaleza.getMisc() != 0)
            txtFortMisc.setText("" + fortaleza.getMisc());

        if (reflejos.getBase() != 0)
            txtRefBase.setText("" + reflejos.getBase());
        if (reflejos.getAbility() != 0)
            txtRefAbility.setText("" + reflejos.getAbility());
        if (reflejos.getMagic() != 0)
            txtRefMagic.setText("" + reflejos.getMagic());
        if (reflejos.getMisc() != 0)
            txtRefMisc.setText("" + reflejos.getMisc());

        if (voluntad.getBase() != 0)
            txtVolBase.setText("" + voluntad.getBase());
        if (voluntad.getAbility() != 0)
            txtVolAbility.setText("" + voluntad.getAbility());
        if (voluntad.getMagic() != 0)
            txtVolMagic.setText("" + voluntad.getMagic());
        if (voluntad.getMisc() != 0)
            txtVolMisc.setText("" + voluntad.getMisc());


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtForBase.getText().toString().equals(""))
                    fortaleza.setBase(Integer.parseInt(txtForBase.getText().toString()));
                if (!txtFortAbility.getText().toString().equals(""))
                    fortaleza.setAbility(Integer.parseInt(txtFortAbility.getText().toString()));
                if (!txtFortMagic.getText().toString().equals(""))
                    fortaleza.setMagic(Integer.parseInt(txtFortMagic.getText().toString()));
                if (!txtFortMisc.getText().toString().equals(""))
                    fortaleza.setMisc(Integer.parseInt(txtFortMisc.getText().toString()));

                if (!txtRefBase.getText().toString().equals(""))
                    reflejos.setBase(Integer.parseInt(txtRefBase.getText().toString()));
                if (!txtRefAbility.getText().toString().equals(""))
                    reflejos.setAbility(Integer.parseInt(txtRefAbility.getText().toString()));
                if (!txtRefMagic.getText().toString().equals(""))
                    reflejos.setMagic(Integer.parseInt(txtRefMagic.getText().toString()));
                if (!txtRefMisc.getText().toString().equals(""))
                    reflejos.setMisc(Integer.parseInt(txtRefMisc.getText().toString()));

                if (!txtVolBase.getText().toString().equals(""))
                    voluntad.setBase(Integer.parseInt(txtVolBase.getText().toString()));
                if (!txtVolAbility.getText().toString().equals(""))
                    voluntad.setAbility(Integer.parseInt(txtVolAbility.getText().toString()));
                if (!txtVolMagic.getText().toString().equals(""))
                    voluntad.setMagic(Integer.parseInt(txtVolMagic.getText().toString()));
                if (!txtVolMisc.getText().toString().equals(""))
                    voluntad.setMisc(Integer.parseInt(txtVolMisc.getText().toString()));

                int totalf = fortaleza.getBase() + fortaleza.getAbility() + fortaleza.getMagic() + fortaleza.getMisc();
                int totalr = reflejos.getBase() + reflejos.getAbility() + reflejos.getMagic() + reflejos.getMisc();
                int totalv = voluntad.getBase() + voluntad.getAbility() + voluntad.getMagic() + voluntad.getMisc();

                fortaleza.setTotal(totalf);
                reflejos.setTotal(totalr);
                voluntad.setTotal(totalv);

                getTargetFragment().onActivityResult(getTargetRequestCode(), 1, getActivity().getIntent());
                dismiss();
            }
        });

        // Create the AlertDialog object and return it
        return v;
    }
}
