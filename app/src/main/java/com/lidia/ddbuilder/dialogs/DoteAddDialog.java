package com.lidia.ddbuilder.dialogs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Dote;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;
import com.lidia.ddbuilder.ui.fragments.DotesFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoteAddDialog extends DialogFragment {

    private Dote dote;
    private ArrayList<Dote> dotesPJ;

    private TextView txtNombre, txtPrerrequisite, txtDescription;
    private EditText txtAdditional;
    private Button btnSave;

    public DoteAddDialog(Dote dote) {
        super();
        this.dote = dote;
        this.dotesPJ = DotesFragment.dotes;
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
        View v = inflater.inflate(R.layout.dialog_add_dote, container, false);

        txtAdditional = v.findViewById(R.id.txtAdditionalDoteAdd);
        txtNombre = v.findViewById(R.id.lbNombreDoteAdd);
        txtPrerrequisite = v.findViewById(R.id.lbPrerrequisiteDoteAdd);
        txtDescription = v.findViewById(R.id.lbDescriptionDoteAdd);
        btnSave = v.findViewById(R.id.btnAddDote);

        txtNombre.setText(dote.getNombre());
        if (dote.getPrerrequisito().equals(""))
            txtPrerrequisite.setText("None");
        else
            txtPrerrequisite.setText(dote.getPrerrequisito());
        txtDescription.setText(dote.getDescripcion());


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dote.setNotas(txtAdditional.getText().toString());
                dotesPJ.add(dote);
                getTargetFragment().onActivityResult(getTargetRequestCode(), 1, getActivity().getIntent());
                dismiss();
            }
        });
        // Create the AlertDialog object and return it
        return v;
    }

}
