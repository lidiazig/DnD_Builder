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

import com.lidia.ddbuilder.PersonajeActivity;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.dialogs.ArmorClassDialog;
import com.lidia.ddbuilder.dialogs.SalvacionesDialog;
import com.lidia.ddbuilder.pojo.ArmorClass;
import com.lidia.ddbuilder.pojo.Caracteristicas;
import com.lidia.ddbuilder.pojo.DatosAdicionales;
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.pojo.Salvacion;
import com.lidia.ddbuilder.pojo.Vida;

import java.util.ArrayList;

public class CaracteristicasFragment extends Fragment {

    private EditText txtFue, txtDes, txtCon, txtInt, txtSab, txtCar, txtDoteIniciativa, txtPgMax, txtHeridas,
            txtNoLetal, txtRedDano, txtVelocidad, txtResCon, txtBaseAttack, txtSizePresa, txtMiscPresa;
    private TextView lbModFue, lbModDes, lbModCon, lbModInt, lbModSab, lbModCar, lbIniciativa, lbCa, lbToque, lbDesprevenido, lbAtaqueBase,
            lbPresa, lbFortaleza, lbReflejos, lbVoluntad;

    private Personaje personaje = PersonajeActivity.personaje;
    private Caracteristicas caracteristicas = personaje.getCaracteristicas();
    private DatosAdicionales datosAdicionales = personaje.getDatosAdicionales();
    private Vida vida = personaje.getVida();
    private ArmorClass armorClass = personaje.getArmorClass();

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
        txtBaseAttack = root.findViewById(R.id.txtMaxBaseAttack);
        lbPresa = root.findViewById(R.id.lbPresa);
        txtSizePresa = root.findViewById(R.id.txtSizePresa);
        txtMiscPresa = root.findViewById(R.id.txtMiscPresa);
        lbFortaleza = root.findViewById(R.id.lbFortaleza);
        lbReflejos = root.findViewById(R.id.lbReflejos);
        lbVoluntad = root.findViewById(R.id.lbVoluntad);

        fillCaracteristicas();
        fillData();
        fillBaseAttack();
        fillGrapple();
        fillIniciativa();
        fillArmorClass();
        fillSalvaciones();

        txtFue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtFue.getText().equals(""))
                        try {
                            caracteristicas.setFuerza(Integer.parseInt(txtFue.getText().toString()));
                            fillCaracteristicas();
                            fillGrapple();
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
                            caracteristicas.setDestreza(Integer.parseInt(txtDes.getText().toString()));
                            fillCaracteristicas();
                            fillIniciativa();
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
                            caracteristicas.setConstitucion(Integer.parseInt(txtCon.getText().toString()));
                            fillCaracteristicas();
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
                            caracteristicas.setInteligencia(Integer.parseInt(txtInt.getText().toString()));
                            fillCaracteristicas();
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
                            caracteristicas.setSabiduria(Integer.parseInt(txtSab.getText().toString()));
                            fillCaracteristicas();
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
                            caracteristicas.setCarisma(Integer.parseInt(txtCar.getText().toString()));
                            fillCaracteristicas();
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
                            datosAdicionales.setFeatIniciativa(Integer.parseInt(txtDoteIniciativa.getText().toString()));
                            fillIniciativa();
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
                            datosAdicionales.setBaseAttack(Integer.parseInt(txtBaseAttack.getText().toString()));
                            fillBaseAttack();
                            fillGrapple();
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
                            datosAdicionales.setMiscPresa(Integer.parseInt(txtMiscPresa.getText().toString()));
                            fillGrapple();
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
                            datosAdicionales.setSize(Integer.parseInt(txtSizePresa.getText().toString()));
                            fillGrapple();
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });
        txtPgMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtPgMax.getText().equals(""))
                        try {
                            vida.setPgMax(Integer.parseInt(txtPgMax.getText().toString()));
                            fillData();
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });

        txtHeridas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtHeridas.getText().equals(""))
                        try {
                            vida.setPgHeridas(Integer.parseInt(txtHeridas.getText().toString()));
                            fillData();
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });

        txtNoLetal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtNoLetal.getText().equals(""))
                        try {
                            vida.setDanoNoLetal(Integer.parseInt(txtNoLetal.getText().toString()));
                            fillData();
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });

        txtVelocidad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtVelocidad.getText().equals(""))
                        try {
                            datosAdicionales.setVelocidad(Integer.parseInt(txtVelocidad.getText().toString()));
                            fillData();
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });

        txtResCon.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtResCon.getText().equals(""))
                        try {
                            datosAdicionales.setResistenciaConjuros(Integer.parseInt(txtResCon.getText().toString()));
                            fillData();
                        } catch (NumberFormatException nfe) {
                            Log.i("error", "" + nfe);
                        }
                }
            }
        });

        txtRedDano.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtRedDano.getText().equals(""))
                        try {
                            datosAdicionales.setRedDano(txtRedDano.getText().toString());
                            fillData();
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

    private void fillData() {
        if (vida.getPgMax() == 0)
            txtPgMax.setText("");
        else
            txtPgMax.setText(vida.getPgMax() + "");

        if (vida.getPgHeridas() == 0)
            txtHeridas.setText("");
        else
            txtHeridas.setText(vida.getPgHeridas() + "");

        if (vida.getDanoNoLetal() == 0)
            txtNoLetal.setText("");
        else
            txtNoLetal.setText(vida.getDanoNoLetal() + "");

        if (datosAdicionales.getVelocidad() == 0)
            txtVelocidad.setText("");
        else
            txtVelocidad.setText(datosAdicionales.getVelocidad() + "");

        if (datosAdicionales.getResistenciaConjuros() == 0)
            txtResCon.setText("");
        else
            txtResCon.setText(datosAdicionales.getResistenciaConjuros() + "");

        txtRedDano.setText(datosAdicionales.getRedDano());
    }

    private void fillCaracteristicas() {
        txtFue.setText(caracteristicas.getFuerza() + "");
        txtDes.setText(caracteristicas.getDestreza() + "");
        txtCon.setText(caracteristicas.getConstitucion() + "");
        txtInt.setText(caracteristicas.getInteligencia() + "");
        txtSab.setText(caracteristicas.getSabiduria() + "");
        txtCar.setText(caracteristicas.getCarisma() + "");

        lbModFue.setText(caracteristicas.getModificadorStr(caracteristicas.getFuerza()));
        lbModDes.setText(caracteristicas.getModificadorStr(caracteristicas.getDestreza()));
        lbModCon.setText(caracteristicas.getModificadorStr(caracteristicas.getConstitucion()));
        lbModInt.setText(caracteristicas.getModificadorStr(caracteristicas.getInteligencia()));
        lbModSab.setText(caracteristicas.getModificadorStr(caracteristicas.getSabiduria()));
        lbModCar.setText(caracteristicas.getModificadorStr(caracteristicas.getCarisma()));
    }

    private void fillBaseAttack() {
        txtBaseAttack.setText(datosAdicionales.getBaseAttack()+"");
        String ba = "";
        int baseAttack = datosAdicionales.getBaseAttack();

        if (baseAttack < 6)
            ba = "+" + baseAttack;
        if (baseAttack >= 6 && baseAttack < 11)
            ba = "+" + baseAttack + "/+" + (baseAttack - 5);
        if (baseAttack >= 11 && baseAttack < 16)
            ba = "+" + baseAttack + "/+" + (baseAttack - 5) + "/+" + (baseAttack - 10);
        if (baseAttack >= 16)
            ba = "+" + baseAttack + "/+" + (baseAttack - 5) + "/+" + (baseAttack - 10) + "/+" + (baseAttack - 15);

        lbAtaqueBase.setText(ba);
    }

    private void fillGrapple() {
        txtSizePresa.setText(datosAdicionales.getSize()+"");
        txtMiscPresa.setText(datosAdicionales.getMiscPresa()+"");

        int presa;
        int ba = datosAdicionales.getBaseAttack();
        int fue = caracteristicas.getModificador(caracteristicas.getFuerza());
        int size = datosAdicionales.getSize();
        int misc = datosAdicionales.getMiscPresa();

        presa = ba + fue + size + misc;

        lbPresa.setText("+" + presa);
    }

    private void fillIniciativa() {
        txtDoteIniciativa.setText(datosAdicionales.getFeatIniciativa()+"");

        int iniciativa;
        int dex = caracteristicas.getModificador(caracteristicas.getDestreza());
        int feat = datosAdicionales.getFeatIniciativa();

        iniciativa = dex + feat;

        lbIniciativa.setText("+" + iniciativa + "");
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
                fillArmorClass();
            }
        }
        if (requestCode == SALVACIONES) {
            if (resultCode == 1) {
                fillSalvaciones();
            }
        }
    }

    private void fillArmorClass() {
        lbCa.setText(armorClass.getAc() + "");
        lbToque.setText(armorClass.getTouch() + "");
        lbDesprevenido.setText(armorClass.getFlatfooted() + "");
    }

    private void fillSalvaciones() {
        ArrayList<Salvacion> salvaciones = personaje.getSalvaciones();

        lbFortaleza.setText(salvaciones.get(0).getTotal() + "");
        lbReflejos.setText(salvaciones.get(1).getTotal() + "");
        lbVoluntad.setText(salvaciones.get(2).getTotal() + "");
    }
}
