package com.lidia.ddbuilder.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.pojo.Salvacion;

public class CaracteristicasFragment extends Fragment {

    private EditText txtFue, txtDes, txtCon, txtInt, txtSab, txtCar, txtTempFue, txtTempDes, txtTempCon, txtTempInt, txtTempSab, txtTempCar,
            txtDoteIniciativa, txtPgMax, txtHeridas, txtNoLetal, txtRedDano, txtVelocidad, txtResCon, txtBaseAttack, txtSizePresa, txtMiscPresa;
    private TextView lbModFue, lbModDes, lbModCon, lbModInt, lbModSab, lbModCar, lbIniciativa, lbDexIniciativa, lbCa, lbToque, lbDesprevenido, lbAtaqueBase,
            lbPresa, lbBAPresa, lbStrPresa, lbFortaleza, lbReflejos, lbVoluntad;

    private Personaje personaje = PerfilFragment.personaje;
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
        lbDexIniciativa = root.findViewById(R.id.lbDesIniciativa);
        lbCa = root.findViewById(R.id.lbArmorClass);
        lbToque = root.findViewById(R.id.lbToque);
        lbDesprevenido = root.findViewById(R.id.lbDesprevenido);
        lbAtaqueBase = root.findViewById(R.id.lbAtaqueBase);
        txtBaseAttack = root.findViewById(R.id.txtMaxBaseAttack);
        lbPresa = root.findViewById(R.id.lbPresa);
        lbBAPresa = root.findViewById(R.id.lbBAPresa);
        lbStrPresa = root.findViewById(R.id.lbStrPresa);
        txtSizePresa = root.findViewById(R.id.txtSizePresa);
        txtMiscPresa = root.findViewById(R.id.txtMiscPresa);
        lbFortaleza = root.findViewById(R.id.lbFortaleza);
        lbReflejos = root.findViewById(R.id.lbReflejos);
        lbVoluntad = root.findViewById(R.id.lbVoluntad);

        txtFue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtFue.getText().equals(""))
                        try {
                            lbModFue.setText(calculoMod(Integer.parseInt(txtFue.getText().toString())));
                            lbStrPresa.setText(lbModFue.getText().toString() + " + ");
                            lbPresa.setText("+"+Grapple() + " = ");
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });
        txtDes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtDes.getText().equals(""))
                        try {
                            lbModDes.setText(calculoMod(Integer.parseInt(txtDes.getText().toString())));
                            lbDexIniciativa.setText("= " + lbModDes.getText().toString() + " + ");
                            lbIniciativa.setText("+"+Iniciativa() + " = ");
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });
        txtCon.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtCon.getText().equals(""))
                        try {
                            lbModCon.setText(calculoMod(Integer.parseInt(txtCon.getText().toString())));
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }

                }
            }
        });
        txtInt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtInt.getText().equals(""))
                        try {
                            lbModInt.setText(calculoMod(Integer.parseInt(txtInt.getText().toString())));
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }

                }
            }
        });
        txtSab.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtSab.getText().equals(""))
                        try {
                            lbModSab.setText(calculoMod(Integer.parseInt(txtSab.getText().toString())));
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }

                }
            }
        });
        txtCar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtCar.getText().equals(""))
                        try {
                            lbModCar.setText(calculoMod(Integer.parseInt(txtCar.getText().toString())));
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
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

        txtDoteIniciativa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtDoteIniciativa.getText().equals(""))
                        try {
                            lbIniciativa.setText("+"+Iniciativa() + "");
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });

        txtBaseAttack.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtBaseAttack.getText().equals(""))
                        try {
                            lbAtaqueBase.setText(BaseAttack());
                            lbBAPresa.setText("= +" + txtBaseAttack.getText().toString());
                            lbPresa.setText("+"+Grapple() + " = ");
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });

        txtMiscPresa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtMiscPresa.getText().equals(""))
                        try {
                            lbPresa.setText("+" + Grapple());
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });

        txtSizePresa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtSizePresa.getText().equals(""))
                        try {
                            lbPresa.setText("+" + Grapple());
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
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

    private String BaseAttack() {
        String ba = "";
        int baseAttack = Integer.parseInt(txtBaseAttack.getText().toString());
        if (baseAttack < 6)
            ba = "+" + txtBaseAttack.getText().toString();
        if (baseAttack >= 6 && baseAttack < 11)
            ba = "+" + txtBaseAttack.getText().toString() + "/+" + (baseAttack - 5);
        if (baseAttack >= 11 && baseAttack < 16)
            ba = "+" + txtBaseAttack.getText().toString() + "/+" + (baseAttack - 5) + "/+" + (baseAttack - 10);
        if (baseAttack >= 16)
            ba = "+" + txtBaseAttack.getText().toString() + "/+" + (baseAttack - 5) + "/+" + (baseAttack) + "/+" + (baseAttack - 15);

        return ba;
    }

    private int Grapple() {
        int presa = 0;
        int ba = 0;
        int fue = 0;
        int size = 0;
        int misc = 0;

        try {
            ba = Integer.parseInt(txtBaseAttack.getText().toString());
            fue = Integer.parseInt(lbModFue.getText().toString());
            size = Integer.parseInt(txtSizePresa.getText().toString());
            misc = Integer.parseInt(txtMiscPresa.getText().toString());
        } catch (NumberFormatException nfe) {
            Log.i("error", "" + nfe);
        }

        presa = ba + fue + size + misc;
        return presa;
    }

    private int Iniciativa() {
        int iniciativa = 0;
        int dex = 0;
        int feat = 0;
        try {
            dex = Integer.parseInt(lbModDes.getText().toString());
            feat = Integer.parseInt(txtDoteIniciativa.getText().toString());
        } catch (NumberFormatException nfe) {
            Log.i("error", "" + nfe);
        }

        iniciativa = dex + feat;
        return iniciativa;
    }

    private void Armor() {
        ArmorClassDialog armorClassDialog = new ArmorClassDialog();
        armorClassDialog.getView();
        armorClassDialog.setTargetFragment(this, ARMOR);
        armorClassDialog.show(getFragmentManager(), "armor");
    }

    private void Salvaciones() {
        SalvacionesDialog salvacionesDialog = new SalvacionesDialog();
        salvacionesDialog.getView();
        salvacionesDialog.setTargetFragment(this, SALVACIONES);
        salvacionesDialog.show(getFragmentManager(), "salvaciones");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ARMOR) { // 1 is an arbitrary number, can be any int
            // This is the return result of your DialogFragment
            if (resultCode == 1) { // 1 is an arbitrary number, can be any int
                lbCa.setText(armorClass.getAc() + "");
                lbToque.setText(armorClass.getTouch() + "");
                lbDesprevenido.setText(armorClass.getFlatfooted() + "");
            }
        }
        if (requestCode == SALVACIONES) {
            if (resultCode == 1) {
                lbFortaleza.setText(fortaleza.getTotal() + "");

                lbReflejos.setText(reflejos.getTotal() + "");
                lbVoluntad.setText(voluntad.getTotal() + "");
            }
        }
    }
}
