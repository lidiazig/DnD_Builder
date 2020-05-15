package com.lidia.ddbuilder.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.adapters.InventarioAdapter;
import com.lidia.ddbuilder.dialogs.InventarioDialog;
import com.lidia.ddbuilder.pojo.Inventario;

import java.util.ArrayList;

public class InventarioFragment extends Fragment {

    private final int INVENTORY = 1;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    public static ArrayList<Inventario> inventario = new ArrayList<>();
    private int elemento = R.layout.elemento_inventario;
    private InventarioAdapter adapter;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static InventarioFragment newInstance(int index) {
        InventarioFragment fragment = new InventarioFragment();
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
        View root = inflater.inflate(R.layout.fragment_inventario, container, false);

        fab = root.findViewById(R.id.fabInventario);
        recyclerView = root.findViewById(R.id.recyclerViewInventario);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InventarioDialog inventarioDialog = new InventarioDialog();
                inventarioDialog.getView();
                inventarioDialog.setTargetFragment(InventarioFragment.this, INVENTORY);
                inventarioDialog.show(getFragmentManager(), "inventario");
            }
        });

        adapter = new InventarioAdapter(getContext(), inventario, elemento, getFragmentManager(), this);

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
        if(requestCode == INVENTORY) { // 1 is an arbitrary number, can be any int
            // This is the return result of your DialogFragment
            if(resultCode == 1) { // 1 is an arbitrary number, can be any int
                adapter.notifyDataSetChanged();
            }
        }
    }
}
