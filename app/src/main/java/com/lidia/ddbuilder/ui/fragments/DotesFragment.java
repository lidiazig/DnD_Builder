package com.lidia.ddbuilder.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lidia.ddbuilder.PersonajeActivity;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.adapters.DoteAdapter;
import com.lidia.ddbuilder.dialogs.DoteAddDialog;
import com.lidia.ddbuilder.dialogs.DoteListadoDialog;
import com.lidia.ddbuilder.pojo.Dote;

import java.util.ArrayList;

public class DotesFragment extends Fragment {

    private final int LISTADO_DOTE = 1;
    private FloatingActionButton fab;

    private RecyclerView recyclerView;
    private ArrayList<Dote> dotes = PersonajeActivity.personaje.getDotes();
    private int elemento = R.layout.elemento_dote;
    public static DoteAdapter adapter;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static DotesFragment newInstance(int index) {
        DotesFragment fragment = new DotesFragment();
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
        View root = inflater.inflate(R.layout.fragment_dotes, container, false);
        fab = root.findViewById(R.id.fabDotes);
        recyclerView = root.findViewById(R.id.recyclerViewDotes);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoteListadoDialog doteListadoDialog = new DoteListadoDialog(DotesFragment.this);
                doteListadoDialog.getView();
                doteListadoDialog.setTargetFragment(DotesFragment.this, LISTADO_DOTE);
                doteListadoDialog.show(getFragmentManager(), "adddote");
            }
        });

        adapter = new DoteAdapter(getContext(), dotes, elemento, getFragmentManager());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LISTADO_DOTE) { // 1 is an arbitrary number, can be any int
            // This is the return result of your DialogFragment
            if (resultCode == 1) { // 1 is an arbitrary number, can be any int
                adapter.notifyDataSetChanged();
            }
        }
    }
}
