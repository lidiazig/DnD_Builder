package com.lidia.ddbuilder.ui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;


import com.lidia.ddbuilder.MainActivity;
import com.lidia.ddbuilder.PersonajeActivity;
import com.lidia.ddbuilder.R;
import com.lidia.ddbuilder.pojo.Alineamiento;
import com.lidia.ddbuilder.pojo.Clase;
import com.lidia.ddbuilder.pojo.Perfil;
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.pojo.Raza;
import com.lidia.ddbuilder.pojo.Token;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PerfilFragment extends Fragment {

    private static final int IMAGE_PICKER_SELECT = 1;


    private Personaje personaje = PersonajeActivity.personaje;
    private Perfil perfil = personaje.getPerfil();
    private ArrayList<String> clases = new ArrayList<>();
    private ArrayList<String> alineamientos = new ArrayList<>();
    private ArrayList<String> razas = new ArrayList<>();
    private ArrayList<Integer> imagenes = new ArrayList<>();
    private Token token = MainActivity.token;

    private ArrayList<Clase> clasesData = new ArrayList<>();
    private ArrayList<Alineamiento> alineamientosData = new ArrayList<>();
    private ArrayList<Raza> razasData = new ArrayList<>();


    private EditText txtNombre, txtNivel, txtGenero, txtTamano, txtEdad, txtIdiomas;
    private Spinner spinnerClase, spinnerRaza, spinnerAlineamiento;
    private Button btnSubir, btnAnterior, btnSiguiente;
    private ImageView imagen;
    private int contador = 0;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PerfilFragment newInstance(int index) {
        PerfilFragment fragment = new PerfilFragment();
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
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        txtNombre = root.findViewById(R.id.txtNombre);
        txtNivel = root.findViewById(R.id.txtNivel);
        txtGenero = root.findViewById(R.id.txtGenero);
        txtTamano = root.findViewById(R.id.txtTamano);
        txtEdad = root.findViewById(R.id.txtEdad);
        txtIdiomas = root.findViewById(R.id.txtIdiomas);

        imagen = root.findViewById(R.id.imagenPersonaje);
        btnSubir = root.findViewById(R.id.btnSubir);
        btnAnterior = root.findViewById(R.id.btnImagenAnterior);
        btnSiguiente = root.findViewById(R.id.btnImagenSiguiente);

        spinnerClase = root.findViewById(R.id.spinnerClase);
        spinnerAlineamiento = root.findViewById(R.id.spinnerAlineamiento);
        spinnerRaza = root.findViewById(R.id.spinnerRaza);

        setSpinnerClase();
        setSpinnerRaza();
        setSpinnerAlineamiento();
        fillImagenes();
        fillData();

        txtNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtNombre.getText().equals(""))
                        perfil.setNombre(txtNombre.getText().toString());
                }
            }
        });
        txtNivel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtNivel.getText().equals(""))
                        perfil.setNivel(txtNivel.getText().toString());
                }
            }
        });
        txtGenero.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtGenero.getText().equals(""))
                        perfil.setGenero(txtGenero.getText().toString());
                }
            }
        });
        txtTamano.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtTamano.getText().equals(""))
                        perfil.setTamano(txtTamano.getText().toString());
                }
            }
        });
        txtEdad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtEdad.getText().equals(""))
                        perfil.setEdad(txtEdad.getText().toString());
                }
            }
        });
        txtIdiomas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!txtIdiomas.getText().equals(""))
                        perfil.setIdiomas(txtIdiomas.getText().toString());
                }
            }
        });

        spinnerAlineamiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                perfil.setIdAlineamiento(getIdFromPoAlineamiento(spinnerAlineamiento.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerRaza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                perfil.setIdRaza(getIdFromPosRazas(spinnerRaza.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerClase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                perfil.setIdClase(getIdFromPosClases(spinnerClase.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivityForResult(intent, IMAGE_PICKER_SELECT);
                    }
                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            IMAGE_PICKER_SELECT);
                }
            }

        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                if (contador >= imagenes.size())
                    contador = 0;

                imagen.setImageResource(imagenes.get(contador));
                perfil.setIdImagen(imagenes.get(contador));
            }
        });
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador--;
                if (contador < 0)
                    contador = imagenes.size() - 1;

                imagen.setImageResource(imagenes.get(contador));
                perfil.setIdImagen(imagenes.get(contador));
            }
        });

        return root;
    }

    private void fillData() {
        txtNombre.setText(perfil.getNombre());
        txtNivel.setText(perfil.getNivel());
        txtGenero.setText(perfil.getGenero());
        txtTamano.setText(perfil.getTamano());
        txtEdad.setText(perfil.getEdad());
        txtIdiomas.setText(perfil.getIdiomas());

        if(perfil.getIdImagen()!=0) {
            imagen.setImageResource(perfil.getIdImagen());
            for (int i = 0; i < imagenes.size(); i++) {
                if (imagenes.get(i) == perfil.getIdImagen()) {
                    contador = i;
                    break;
                }
            }
        }

        spinnerAlineamiento.setSelection(getPosFromIdAlineamientos(perfil.getIdAlineamiento()), false);
        spinnerClase.setSelection(getPosFromIdClases(perfil.getIdClase()), false);
        spinnerRaza.setSelection(getPosFromIdRazas(perfil.getIdRaza()), false);
    }

    private void fillImagenes() {
        imagenes.clear();
        imagenes.add(R.drawable.barbarian_female);
        imagenes.add(R.drawable.barbarian_male);
        imagenes.add(R.drawable.bard_male);
        imagenes.add(R.drawable.bard_female);
        imagenes.add(R.drawable.cleric_female);
        imagenes.add(R.drawable.cleric_male);
        imagenes.add(R.drawable.druid_female);
        imagenes.add(R.drawable.druid_male);
        imagenes.add(R.drawable.dwarf_female);
        imagenes.add(R.drawable.dwarf_male);
        imagenes.add(R.drawable.fighter_female);
        imagenes.add(R.drawable.fighter_male);
        imagenes.add(R.drawable.gnome_female);
        imagenes.add(R.drawable.gnome_male);
        imagenes.add(R.drawable.halfling_female);
        imagenes.add(R.drawable.halfling_male);
        imagenes.add(R.drawable.halforc_female);
        imagenes.add(R.drawable.halforc_male);
        imagenes.add(R.drawable.monk_female);
        imagenes.add(R.drawable.monk_male);
        imagenes.add(R.drawable.paladin_female);
        imagenes.add(R.drawable.paladin_male);
        imagenes.add(R.drawable.ranger_female);
        imagenes.add(R.drawable.ranger_male);
        imagenes.add(R.drawable.rogue_female);
        imagenes.add(R.drawable.rogue_male);
        imagenes.add(R.drawable.sorcerer_female);
        imagenes.add(R.drawable.wizard_male);
    }

    public static int getCameraPhotoOrientation(Context context, String imagePath) {
        int rotate = 0;
        try {
            File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(
                    imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_PICKER_SELECT && data != null) {
            Uri uri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(uri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = 2;
// Get the original bitmap from the filepath to which you want to change orientation
// fileName ist the filepath of the image
            Bitmap cachedImage = BitmapFactory.decodeFile(picturePath, o2);
            int rotate = getCameraPhotoOrientation(getContext(), picturePath);
            Matrix matrix = new Matrix();
            matrix.postRotate(rotate);
// Here you will get the image bitmap which has changed orientation
            cachedImage = Bitmap.createBitmap(cachedImage, 0, 0, cachedImage.getWidth(), cachedImage.getHeight(), matrix, true);
            imagen.setImageBitmap(cachedImage);
        }
    }

    private void setSpinnerAlineamiento() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Alineamiento>> call = conexion.doGetAlineamientos(token);
        call.enqueue(new Callback<ArrayList<Alineamiento>>() {
            @Override
            public void onResponse(Call<ArrayList<Alineamiento>> call, Response<ArrayList<Alineamiento>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        alineamientosData = response.body();
                        spinAlineamientoJSON(response.body());

                    }
                } else {
                    Log.i("onEmptyResponse", "Returned empty response");
                    Toast.makeText(getActivity(), "NOT AUTHORIZED", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Alineamiento>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void spinAlineamientoJSON(ArrayList<Alineamiento> response) {
        alineamientos.clear();
        for (int i = 0; i < response.size(); i++) {
            alineamientos.add(response.get(i).getAlineamiento());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, alineamientos);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerAlineamiento.setAdapter(spinnerArrayAdapter);
        fillData();
    }

    private void setSpinnerRaza() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Raza>> call = conexion.doGetRazas(token);
        call.enqueue(new Callback<ArrayList<Raza>>() {
            @Override
            public void onResponse(Call<ArrayList<Raza>> call, Response<ArrayList<Raza>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        razasData = response.body();
                        spinRazaJSON(response.body());

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Raza>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void spinRazaJSON(ArrayList<Raza> response) {
        razas.clear();
        for (int i = 0; i < response.size(); i++) {
            razas.add(response.get(i).getRaza());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, razas);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerRaza.setAdapter(spinnerArrayAdapter);
        fillData();
    }

    private void setSpinnerClase() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Clase>> call = conexion.doGetClases(token);
        call.enqueue(new Callback<ArrayList<Clase>>() {
            @Override
            public void onResponse(Call<ArrayList<Clase>> call, Response<ArrayList<Clase>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        clasesData = response.body();
                        spinClaseJSON(response.body());

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Clase>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void spinClaseJSON(ArrayList<Clase> response) {
        clases.clear();
        for (int i = 0; i < response.size(); i++) {
            clases.add(response.get(i).getClase());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, clases);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerClase.setAdapter(spinnerArrayAdapter);

        fillData();
    }

    private int getIdFromPosClases(int position) {
        return clasesData.get(position).getId();
    }

    private int getIdFromPosRazas(int position) {
        return razasData.get(position).getId();
    }

    private int getIdFromPoAlineamiento(int position) {
        return alineamientosData.get(position).getId();
    }

    private int getPosFromIdClases(int id) {
        for (int i = 0; i < clasesData.size(); i++) {
            if (clasesData.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private int getPosFromIdRazas(int id) {
        for (int i = 0; i < razasData.size(); i++) {
            if (razasData.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private int getPosFromIdAlineamientos(int id) {
        for (int i = 0; i < alineamientosData.size(); i++) {
            if (alineamientosData.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}