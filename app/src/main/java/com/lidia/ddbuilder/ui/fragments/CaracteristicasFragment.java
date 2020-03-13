package com.lidia.ddbuilder.ui.fragments;

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

public class CaracteristicasFragment extends Fragment {

    private EditText txtFue, txtDes, txtCon, txtInt, txtSab, txtCar, txtTempFue, txtTempDes, txtTempCon, txtTempInt, txtTempSab, txtTempCar,
    txtDoteIniciativa, txtPgMax, txtHeridas, txtNoLetal, txtRedDano, txtVelocidad, txtResCon;
    private TextView lbModFue, lbModDes, lbModCon, lbModInt, lbModSab, lbModCar, lbIniciativa, lbCa, lbToque, lbDesprevenido, lbAtaqueBase,
    lbPresa, lbFortaleza, lbReflejos, lbVoluntad;

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
}
