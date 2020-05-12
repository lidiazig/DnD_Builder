package com.lidia.ddbuilder.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.dialogs.ArmorClassDialog;
import com.lidia.ddbuilder.dialogs.SalvacionesDialog;
import com.lidia.ddbuilder.pojo.ArmorClass;
import com.lidia.ddbuilder.pojo.Caracteristicas;
import com.lidia.ddbuilder.pojo.Salvacion;

public class CaracteristicasFragment extends Fragment {

    private EditText txtFue, txtDes, txtCon, txtInt, txtSab, txtCar, txtTempFue, txtTempDes, txtTempCon, txtTempInt, txtTempSab, txtTempCar,
            txtDoteIniciativa, txtPgMax, txtHeridas, txtNoLetal, txtRedDano, txtVelocidad, txtResCon;
    private TextView lbModFue, lbModDes, lbModCon, lbModInt, lbModSab, lbModCar, lbIniciativa, lbCa, lbToque, lbDesprevenido, lbAtaqueBase,
            lbPresa, lbFortaleza, lbReflejos, lbVoluntad;

    private Caracteristicas caracteristicas;
    public static ArmorClass armorClass = new ArmorClass();
    public static Salvacion fortaleza = new Salvacion();
    public static Salvacion reflejos = new Salvacion();
    public static Salvacion voluntad = new Salvacion();

    private final int ARMOR = 1;
    private final int SALVACIONES = 2;
    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static CaracteristicasFragment newInstance(int index) {
        CaracteristicasFragment fragment = new CaracteristicasFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_caracteristicas, container, false);

        txtFue = root.findViewById(R.id.txtFuerza);
        txtDes = root.findViewById(R.id.txtDestreza);
        txtCon = root.findViewById(R.id.txtConstitucion);
        txtInt = root.findViewById(R.id.txtInteligencia);
        txtSab = root.findViewById(R.id.txtSabiduria);
        txtCar = root.findViewById(R.id.txtCarisma);

        lbModFue = root.findViewById(R.id.lbModFue);
        lbModDes = root.findViewById(R.id.lbModDes);
        lbModCon = root.findViewById(R.id.lbModCon);
        lbModInt = root.findViewById(R.id.lbModInt);
        lbModSab = root.findViewById(R.id.lbModSab);
        lbModCar = root.findViewById(R.id.lbModCar);

        txtTempFue = root.findViewById(R.id.txtTempFue);
        txtTempDes = root.findViewById(R.id.txtTempDes);
        txtTempCon = root.findViewById(R.id.txtTempCon);
        txtTempInt = root.findViewById(R.id.txtTempInt);
        txtTempSab = root.findViewById(R.id.txtTempSab);
        txtTempCar = root.findViewById(R.id.txtTempCar);

        txtDoteIniciativa = root.findViewById(R.id.txtDoteIniciativa);
        txtPgMax = root.findViewById(R.id.txtPgMax);
        txtHeridas = root.findViewById(R.id.txtHeridas);
        txtNoLetal = root.findViewById(R.id.txtNoLetal);
        txtRedDano = root.findViewById(R.id.txtRedDano);
        txtVelocidad = root.findViewById(R.id.txtVelocidad);
        txtResCon = root.findViewById(R.id.txtResistenciaConjuros);

        lbIniciativa = root.findViewById(R.id.lbIniciativa);
        lbCa = root.findViewById(R.id.lbArmorClass);
        lbToque = root.findViewById(R.id.lbToque);
        lbDesprevenido = root.findViewById(R.id.lbDesprevenido);
        lbAtaqueBase = root.findViewById(R.id.lbAtaqueBase);
        lbPresa = root.findViewById(R.id.lbPresa);
        lbFortaleza = root.findViewById(R.id.lbFortaleza);
        lbReflejos = root.findViewById(R.id.lbReflejos);
        lbVoluntad = root.findViewById(R.id.lbVoluntad);

        txtFue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtFue.getText().equals(""))
                        lbModFue.setText(calculoMod(Integer.parseInt(txtFue.getText().toString())));
                }
            }
        });
        txtDes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtDes.getText().equals(""))
                        lbModDes.setText(calculoMod(Integer.parseInt(txtDes.getText().toString())));
                }
            }
        });
        txtCon.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtCon.getText().equals(""))
                        lbModCon.setText(calculoMod(Integer.parseInt(txtCon.getText().toString())));
                }
            }
        });
        txtInt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtInt.getText().equals(""))
                        lbModInt.setText(calculoMod(Integer.parseInt(txtInt.getText().toString())));
                }
            }
        });
        txtSab.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtSab.getText().equals(""))
                        lbModSab.setText(calculoMod(Integer.parseInt(txtSab.getText().toString())));
                }
            }
        });
        txtCar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtCar.getText().equals(""))
                        lbModCar.setText(calculoMod(Integer.parseInt(txtCar.getText().toString())));
                }
            }
        });

        lbCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Armor();
            }
        });

        lbToque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Armor();
            }
        });

        lbDesprevenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Armor();
            }
        });

        lbFortaleza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salvaciones();
            }
        });

        lbReflejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salvaciones();
            }
        });

        lbVoluntad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salvaciones();
            }
        });

        /*
        final TextView textView = root.findViewById(R.id.txtNombre);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

         */
        return root;
    }

    private String calculoMod(int num) {
        int mod = (num - 10) / 2;
        if (mod >= 0)
            return "+" + mod;
        else
            return "" + mod;
    }

    private void Armor() {
        ArmorClassDialog armorClassDialog = new ArmorClassDialog();
        armorClassDialog.getView();
        armorClassDialog.setTargetFragment(this, ARMOR);
        armorClassDialog.show(getFragmentManager(), "armor");
    }

    private void Salvaciones(){
        SalvacionesDialog salvacionesDialog = new SalvacionesDialog();
        salvacionesDialog.getView();
        salvacionesDialog.setTargetFragment(this, SALVACIONES);
        salvacionesDialog.show(getFragmentManager(), "salvaciones");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ARMOR) { // 1 is an arbitrary number, can be any int
            // This is the return result of your DialogFragment
            if(resultCode == 1) { // 1 is an arbitrary number, can be any int
                lbCa.setText(armorClass.getAc() + "");
                lbToque.setText(armorClass.getTouch() + "");
                lbDesprevenido.setText(armorClass.getFlatfooted() + "");
            }
        }
        if(requestCode==SALVACIONES){
            if(resultCode==1) {
                lbFortaleza.setText(fortaleza.getTotal() + "");
                lbReflejos.setText(reflejos.getTotal() + "");
                lbVoluntad.setText(voluntad.getTotal() + "");
            }
        }
    }
}
