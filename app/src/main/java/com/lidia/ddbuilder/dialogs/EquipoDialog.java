package com.lidia.ddbuilder.dialogs;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.DialogFragment;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Arma;
import com.lidia.ddbuilder.pojo.Armadura;
import com.lidia.ddbuilder.pojo.Equipo;
import com.lidia.ddbuilder.ui.fragments.EquipoFragment;

import java.util.ArrayList;

public class EquipoDialog extends DialogFragment {
    private LinearLayout arma1, arma2, arma3, arma4, armadura1, armadura2, armadura3, armadura4;

    private RadioButton rbArma, rbArmadura;
    private RadioGroup radioGroup;
    private EditText txtNombre, txtBonusArma, txtDano, txtCritico, txtRango, txtTipoArma, txtMunicion,
            txtTipoArmadura, txtBonusArmadura, txtDex, txtPenalty, txtSpell, txtSpeed, txtPeso, txtPropiedades;
    private Button btnSave;

    private Equipo objeto;
    private ArrayList<Equipo> equipo;

    public EquipoDialog() {
        super();
        this.equipo = EquipoFragment.equipo;
    }

    public EquipoDialog(Equipo objeto) {
        super();
        this.equipo = EquipoFragment.equipo;
        this.objeto = objeto;
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
        final View v = inflater.inflate(R.layout.dialog_equipo, container, false);

        arma1 = v.findViewById(R.id.linearArma1);
        arma2 = v.findViewById(R.id.linearArma2);
        arma3 = v.findViewById(R.id.linearArma3);
        arma4 = v.findViewById(R.id.linearArma4);

        armadura1 = v.findViewById(R.id.linearArmadura1);
        armadura2 = v.findViewById(R.id.linearArmadura2);
        armadura3 = v.findViewById(R.id.linearArmadura3);
        armadura4 = v.findViewById(R.id.linearArmadura4);

        txtNombre = v.findViewById(R.id.txtNombreEquipo);
        txtBonusArma = v.findViewById(R.id.txtBonusArma);
        txtDano = v.findViewById(R.id.txtDanoArma);
        txtCritico = v.findViewById(R.id.txtCriticoArma);
        txtRango = v.findViewById(R.id.txtRangoArma);
        txtTipoArma = v.findViewById(R.id.txtTipoArma);
        txtMunicion = v.findViewById(R.id.txtMunicionArma);

        txtTipoArmadura = v.findViewById(R.id.txtTipoArmadura);
        txtBonusArmadura = v.findViewById(R.id.txtBonusArmadura);
        txtDex = v.findViewById(R.id.txtDexArmadura);
        txtPenalty = v.findViewById(R.id.txtPenaltyArmadura);
        txtSpell = v.findViewById(R.id.txtSpellArmadura);
        txtSpeed = v.findViewById(R.id.txtVelocidadArmadura);
        txtPeso = v.findViewById(R.id.txtPesoArmadura);
        txtPropiedades = v.findViewById(R.id.txtPropiedadesEquipo);

        rbArma = v.findViewById(R.id.rbArma);
        rbArmadura = v.findViewById(R.id.rbArmadura);
        radioGroup = v.findViewById(R.id.radioGroupEquipo);
        btnSave = v.findViewById(R.id.btnSaveEquipo);

        if (objeto != null) {
            rbArma.setEnabled(false);
            rbArmadura.setEnabled(false);
            txtNombre.setText(objeto.getNombre());
            txtPropiedades.setText(objeto.getPropiedades());

            if (objeto instanceof Arma) {
                rbArma.setChecked(true);

                txtBonusArma.setText(((Arma) objeto).getAtaque() + "");
                txtDano.setText(((Arma) objeto).getDano());
                txtCritico.setText(((Arma) objeto).getCritico());
                txtRango.setText(((Arma) objeto).getRango() + "");
                txtTipoArma.setText(((Arma) objeto).getTipoDano());
                txtMunicion.setText(((Arma) objeto).getMunicion() + "");
            } else {
                rbArmadura.setChecked(true);
                activateArmadura();

                txtTipoArmadura.setText(((Armadura) objeto).getTipo());
                txtBonusArmadura.setText(((Armadura) objeto).getBonus() + "");
                txtDex.setText(((Armadura) objeto).getMaxDex() + "");
                txtPenalty.setText(((Armadura) objeto).getPenalty() + "");
                txtSpell.setText(((Armadura) objeto).getSpellFailure());
                txtSpeed.setText(((Armadura) objeto).getVelocidad() + "");
                txtPeso.setText(((Armadura) objeto).getPeso() + "");
            }
        } else {
            rbArmadura.setEnabled(true);
            rbArma.setEnabled(true);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbArma:
                        activateArma();
                        break;
                    case R.id.rbArmadura:
                        activateArmadura();
                        break;
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rbArma.isChecked()) {
                    if (objeto == null) {
                        objeto = new Arma("", "");
                        equipo.add(objeto);
                    }
                    Arma arma = (Arma) objeto;

                    if (!txtBonusArma.getText().toString().equals(""))
                        arma.setAtaque(Integer.parseInt(txtBonusArma.getText().toString()));

                    arma.setDano(txtDano.getText().toString());
                    arma.setCritico(txtCritico.getText().toString());
                    if (!txtRango.getText().toString().equals(""))
                        arma.setRango(Integer.parseInt(txtRango.getText().toString()));

                    arma.setTipoDano(txtTipoArma.getText().toString());
                    if (!txtMunicion.getText().toString().equals(""))
                        arma.setMunicion(Integer.parseInt(txtMunicion.getText().toString()));

                    arma.setTipoObjeto(0);

                } else {
                    if (objeto == null) {
                        objeto = new Armadura("", "");
                        equipo.add(objeto);
                    }
                    Armadura armadura = (Armadura) objeto;

                    armadura.setTipo(txtTipoArmadura.getText().toString());
                    if (!txtBonusArmadura.getText().toString().equals(""))
                        armadura.setBonus(Integer.parseInt(txtBonusArmadura.getText().toString()));

                    if (!txtDex.getText().toString().equals(""))
                        armadura.setMaxDex(Integer.parseInt(txtDex.getText().toString()));

                    if (!txtPenalty.getText().toString().equals(""))
                        armadura.setPenalty(Integer.parseInt(txtPenalty.getText().toString()));

                    armadura.setSpellFailure(txtSpell.getText().toString());
                    if (!txtSpeed.getText().toString().equals(""))
                        armadura.setVelocidad(Integer.parseInt(txtSpeed.getText().toString()));

                    if (!txtPeso.getText().toString().equals(""))
                        armadura.setPeso(Integer.parseInt(txtPeso.getText().toString()));

                    armadura.setTipoObjeto(1);
                }

                objeto.setNombre(txtNombre.getText().toString());
                objeto.setPropiedades(txtPropiedades.getText().toString());

                getTargetFragment().onActivityResult(getTargetRequestCode(), 1, getActivity().getIntent());

                dismiss();
            }
        });


        // Create the AlertDialog object and return it
        return v;
    }

    private void activateArma() {
        rbArma.setTypeface(Typeface.DEFAULT_BOLD);
        rbArmadura.setTypeface(Typeface.DEFAULT);
        arma1.setVisibility(View.VISIBLE);
        arma2.setVisibility(View.VISIBLE);
        arma3.setVisibility(View.VISIBLE);
        arma4.setVisibility(View.VISIBLE);

        armadura1.setVisibility(View.GONE);
        armadura2.setVisibility(View.GONE);
        armadura3.setVisibility(View.GONE);
        armadura4.setVisibility(View.GONE);
    }

    private void activateArmadura() {
        rbArmadura.setTypeface(Typeface.DEFAULT_BOLD);
        rbArma.setTypeface(Typeface.DEFAULT);
        arma1.setVisibility(View.GONE);
        arma2.setVisibility(View.GONE);
        arma3.setVisibility(View.GONE);
        arma4.setVisibility(View.GONE);

        armadura1.setVisibility(View.VISIBLE);
        armadura2.setVisibility(View.VISIBLE);
        armadura3.setVisibility(View.VISIBLE);
        armadura4.setVisibility(View.VISIBLE);
    }
}
