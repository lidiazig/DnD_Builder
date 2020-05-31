package com.lidia.ddbuilder.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidia.ddbuilder.PersonajeActivity;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.adapters.HabilidadesAdapter;
import com.lidia.ddbuilder.pojo.Habilidad;
import com.lidia.ddbuilder.pojo.Personaje;

import java.util.ArrayList;


public class HabilidadesFragment extends Fragment {

    private RecyclerView recyclerView;
    private Personaje personaje = PersonajeActivity.personaje;
    private ArrayList<Habilidad> habilidades = personaje.getHabilidades();
    private int elemento = R.layout.elemento_habilidades;
    private HabilidadesAdapter adapter;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static HabilidadesFragment newInstance(int index) {
        HabilidadesFragment fragment = new HabilidadesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.notifyDataSetChanged();
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
        View root = inflater.inflate(R.layout.fragment_habilidades, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewHabilidades);

        setAdapter();

        return root;
    }

    private void setAdapter() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        adapter = new HabilidadesAdapter(getContext(), habilidades, elemento, getFragmentManager());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
