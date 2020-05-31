package com.lidia.ddbuilder;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lidia.ddbuilder.dialogs.HomeDialog;
import com.lidia.ddbuilder.pojo.Arma;
import com.lidia.ddbuilder.pojo.Armadura;
import com.lidia.ddbuilder.pojo.ArmorClass;
import com.lidia.ddbuilder.pojo.Caracteristicas;
import com.lidia.ddbuilder.pojo.DatosAdicionales;
import com.lidia.ddbuilder.pojo.Dote;
import com.lidia.ddbuilder.pojo.Equipo;
import com.lidia.ddbuilder.pojo.Habilidad;
import com.lidia.ddbuilder.pojo.Inventario;
import com.lidia.ddbuilder.pojo.Perfil;
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.pojo.Salvacion;
import com.lidia.ddbuilder.pojo.Token;
import com.lidia.ddbuilder.pojo.Vida;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;
import com.lidia.ddbuilder.ui.fragments.SectionsPagerAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonajeActivity extends AppCompatActivity {

    private ImageButton btnHome, btnSave;
    public static Personaje personaje = new Personaje();
    private Token token = MainActivity.token;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);
        btnHome = findViewById(R.id.btnHome);
        btnSave = findViewById(R.id.btnSave);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            id = bundle.getInt("id");
            personaje.setId(id);
            getPersonaje(id);
        }

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            switch (i) {
                case 0:
                    tabLayout.getTabAt(i).setIcon(R.drawable.iconprofile);
                    break;
                case 1:
                    tabLayout.getTabAt(i).setIcon(R.drawable.iconcaracteristicas);
                    break;
                case 2:
                    tabLayout.getTabAt(i).setIcon(R.drawable.iconskills);
                    break;
                case 3:
                    tabLayout.getTabAt(i).setIcon(R.drawable.iconequipo);
                    break;
                case 4:
                    tabLayout.getTabAt(i).setIcon(R.drawable.iconfeats);
                    break;
                case 5:
                    tabLayout.getTabAt(i).setIcon(R.drawable.iconinventory);
                    break;
                case 6:
                    tabLayout.getTabAt(i).setIcon(R.drawable.iconspells);
                    break;
            }
        }

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeDialog homeDialog = new HomeDialog();
                homeDialog.showDialog(PersonajeActivity.this);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personaje.getPerfil().getId() != 0)
                    deletePersonaje(personaje.getPerfil().getId());
                else
                    savePersonaje();

                Toast.makeText(PersonajeActivity.this, "CHARACTER SAVED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deletePersonaje(int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Perfil> call = conexion.doDeletePersonaje(id, token);
        call.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        savePersonaje();
                        Log.i("onSuccess", response.body().toString());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void saveArmor() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArmorClass> call = conexion.doSaveArmor(personaje.getArmorClass());
        call.enqueue(new Callback<ArmorClass>() {
            @Override
            public void onResponse(Call<ArmorClass> call, Response<ArmorClass> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArmorClass> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void saveDatos() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<DatosAdicionales> call = conexion.doSaveDatos(personaje.getDatosAdicionales());
        call.enqueue(new Callback<DatosAdicionales>() {
            @Override
            public void onResponse(Call<DatosAdicionales> call, Response<DatosAdicionales> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DatosAdicionales> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void saveVida() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Vida> call = conexion.doSaveVida(personaje.getVida());
        call.enqueue(new Callback<Vida>() {
            @Override
            public void onResponse(Call<Vida> call, Response<Vida> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Vida> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void saveSalvaciones() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Salvacion>> call = conexion.doSaveSalvaciones(personaje.getSalvaciones());
        call.enqueue(new Callback<ArrayList<Salvacion>>() {
            @Override
            public void onResponse(Call<ArrayList<Salvacion>> call, Response<ArrayList<Salvacion>> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Salvacion>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void saveInventario() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Inventario>> call = conexion.doSaveInventario(personaje.getInventario());
        call.enqueue(new Callback<ArrayList<Inventario>>() {
            @Override
            public void onResponse(Call<ArrayList<Inventario>> call, Response<ArrayList<Inventario>> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inventario>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void saveHabilidades() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Habilidad>> call = conexion.doSaveHabilidades(personaje.getHabilidades());
        call.enqueue(new Callback<ArrayList<Habilidad>>() {
            @Override
            public void onResponse(Call<ArrayList<Habilidad>> call, Response<ArrayList<Habilidad>> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Habilidad>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void saveEquipo() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Equipo>> call = conexion.doSaveEquipo(personaje.getEquipo());
        call.enqueue(new Callback<ArrayList<Equipo>>() {
            @Override
            public void onResponse(Call<ArrayList<Equipo>> call, Response<ArrayList<Equipo>> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Equipo>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void saveDotes() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Dote>> call = conexion.doSaveDotes(personaje.getDotes());
        call.enqueue(new Callback<ArrayList<Dote>>() {
            @Override
            public void onResponse(Call<ArrayList<Dote>> call, Response<ArrayList<Dote>> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Dote>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void saveCaracteristicas() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);
        Caracteristicas c = personaje.getCaracteristicas();
        c.setIdPersonaje(personaje.getPerfil().getId());
        Call<Caracteristicas> call = conexion.doSaveCaracteristicas(c);
        call.enqueue(new Callback<Caracteristicas>() {
            @Override
            public void onResponse(Call<Caracteristicas> call, Response<Caracteristicas> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Caracteristicas> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void savePersonaje() {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        personaje.getPerfil().setIdUsuario(token.getIdUser());
        Call<Integer> call = conexion.doSavePersonaje(personaje.getPerfil());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("asdasdasd", response.body().toString());
                        personaje.setId(response.body());

                        saveCaracteristicas();
                        saveArmor();
                        saveDatos();
                        saveDotes();
                        saveEquipo();
                        saveHabilidades();
                        saveInventario();
                        saveSalvaciones();
                        saveVida();
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void getPersonaje(final int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Perfil> call = conexion.doGetPersonaje(id, token);
        call.enqueue(new Callback<Perfil>() {
            @Override
            public void onResponse(Call<Perfil> call, Response<Perfil> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        Perfil perfil = personaje.getPerfil();
                        perfil.setIdUsuario(response.body().getIdUsuario());
                        perfil.setNombre(response.body().getNombre());
                        perfil.setNivel(response.body().getNivel());
                        perfil.setIdClase(response.body().getIdClase());
                        perfil.setIdRaza(response.body().getIdRaza());
                        perfil.setIdAlineamiento(response.body().getIdAlineamiento());
                        perfil.setGenero(response.body().getGenero());
                        perfil.setTamano(response.body().getTamano());
                        perfil.setEdad(response.body().getEdad());
                        perfil.setIdiomas(response.body().getIdiomas());
                        perfil.setIdImagen(response.body().getIdImagen());

                        personaje.setPerfil(perfil);

                        getCaracteristicas(id);
                        getHabilidades(id);
                        getArmorClass(id);
                        getDotes(id);
                        getDatos(id);
                        getEquipo(id);
                        getInventario(id);
                        getSalvaciones(id);
                        getVida(id);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Perfil> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void getCaracteristicas(int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Caracteristicas> call = conexion.doGetCaracteristicas(id, token);
        call.enqueue(new Callback<Caracteristicas>() {
            @Override
            public void onResponse(Call<Caracteristicas> call, Response<Caracteristicas> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        Caracteristicas caracteristicas = personaje.getCaracteristicas();
                        caracteristicas.setIdPersonaje(response.body().getIdPersonaje());
                        caracteristicas.setFuerza(response.body().getFuerza());
                        caracteristicas.setDestreza(response.body().getDestreza());
                        caracteristicas.setConstitucion(response.body().getConstitucion());
                        caracteristicas.setInteligencia(response.body().getInteligencia());
                        caracteristicas.setSabiduria(response.body().getSabiduria());
                        caracteristicas.setCarisma(response.body().getCarisma());

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Caracteristicas> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void getArmorClass(int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArmorClass> call = conexion.doGetArmor(id, token);
        call.enqueue(new Callback<ArmorClass>() {
            @Override
            public void onResponse(Call<ArmorClass> call, Response<ArmorClass> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        ArmorClass armorClass = personaje.getArmorClass();
                        armorClass.setIdPersonaje(response.body().getIdPersonaje());
                        armorClass.setArmor(response.body().getArmor());
                        armorClass.setShield(response.body().getShield());
                        armorClass.setDex(response.body().getDex());
                        armorClass.setSize(response.body().getSize());
                        armorClass.setNaturalArmor(response.body().getNaturalArmor());
                        armorClass.setDeflection(response.body().getDeflection());
                        armorClass.setMisc(response.body().getMisc());

                        personaje.setArmorClass(armorClass);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArmorClass> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void getDatos(int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<DatosAdicionales> call = conexion.doGetDatos(id, token);
        call.enqueue(new Callback<DatosAdicionales>() {
            @Override
            public void onResponse(Call<DatosAdicionales> call, Response<DatosAdicionales> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        DatosAdicionales datos = personaje.getDatosAdicionales();
                        datos.setIdPersonaje(response.body().getIdPersonaje());
                        datos.setBaseAttack(response.body().getBaseAttack());
                        datos.setFeatIniciativa(response.body().getFeatIniciativa());
                        datos.setMiscPresa(response.body().getMiscPresa());
                        datos.setRedDano(response.body().getRedDano());
                        datos.setResistenciaConjuros(response.body().getResistenciaConjuros());
                        datos.setSize(response.body().getSize());
                        datos.setVelocidad(response.body().getVelocidad());

                        personaje.setDatosAdicionales(datos);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DatosAdicionales> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void getDotes(int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Dote>> call = conexion.doGetDotesPj(id, token);
        call.enqueue(new Callback<ArrayList<Dote>>() {
            @Override
            public void onResponse(Call<ArrayList<Dote>> call, Response<ArrayList<Dote>> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        setDotesJSON(response.body());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Dote>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void setDotesJSON(ArrayList<Dote> response) {
        ArrayList<Dote> dotes = personaje.getDotes();
        dotes.clear();
        for (int i = 0; i < response.size(); i++) {
            Dote dote = new Dote();
            dote.setId(response.get(i).getId());
            dote.setNombre(response.get(i).getNombre());
            dote.setPrerrequisito(response.get(i).getPrerrequisito());
            dote.setDescripcion(response.get(i).getDescripcion());
            dote.setNotas(response.get(i).getNotas());
            dotes.add(dote);
        }
        personaje.setDotes(dotes);
    }


    private void getEquipo(int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Equipo>> call = conexion.doGetEquipoPj(id, token);
        call.enqueue(new Callback<ArrayList<Equipo>>() {
            @Override
            public void onResponse(Call<ArrayList<Equipo>> call, Response<ArrayList<Equipo>> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        setEquipoJSON(response.body());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Equipo>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void setEquipoJSON(ArrayList<Equipo> response) {
        ArrayList<Equipo> equipo = personaje.getEquipo();
        equipo.clear();
        for (int i = 0; i < response.size(); i++) {
            if (response.get(i).getTipoObjeto() == 0)
                setArma(response.get(i).getIdObjeto(), equipo);
            else
                setArmadura(response.get(i).getIdObjeto(), equipo);
        }
        personaje.setEquipo(equipo);
    }

    private void setArma(int id, final ArrayList<Equipo> equipo) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Arma> call = conexion.doGetArma(id, token);
        call.enqueue(new Callback<Arma>() {
            @Override
            public void onResponse(Call<Arma> call, Response<Arma> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        Arma arma = new Arma("", "");
                        arma.setNombre(response.body().getNombre());
                        arma.setAtaque(response.body().getAtaque());
                        arma.setDano(response.body().getDano());
                        arma.setCritico(response.body().getCritico());
                        arma.setRango(response.body().getRango());
                        arma.setTipoDano(response.body().getTipoDano());
                        arma.setMunicion(response.body().getMunicion());
                        arma.setPropiedades(response.body().getPropiedades());

                        equipo.add(arma);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Arma> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void setArmadura(int id, final ArrayList<Equipo> equipo) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Armadura> call = conexion.doGetArmadura(id, token);
        call.enqueue(new Callback<Armadura>() {
            @Override
            public void onResponse(Call<Armadura> call, Response<Armadura> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        Armadura armadura = new Armadura("", "");
                        armadura.setNombre(response.body().getNombre());
                        armadura.setBonus(response.body().getBonus());
                        armadura.setMaxDex(response.body().getMaxDex());
                        armadura.setPenalty(response.body().getPenalty());
                        armadura.setSpellFailure(response.body().getSpellFailure());
                        armadura.setVelocidad(response.body().getVelocidad());
                        armadura.setPeso(response.body().getPeso());
                        armadura.setPropiedades(response.body().getPropiedades());

                        equipo.add(armadura);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Armadura> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void getHabilidades(int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Habilidad>> call = conexion.doGetHabilidadesPj(personaje.getPerfil().getId(),token);
        call.enqueue(new Callback<ArrayList<Habilidad>>() {
            @Override
            public void onResponse(Call<ArrayList<Habilidad>> call, Response<ArrayList<Habilidad>> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        personaje.setHabilidades(response.body());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Habilidad>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void getInventario(int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Inventario>> call = conexion.doGetInventario(id, token);
        call.enqueue(new Callback<ArrayList<Inventario>>() {
            @Override
            public void onResponse(Call<ArrayList<Inventario>> call, Response<ArrayList<Inventario>> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        setInventarioJSON(response.body());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inventario>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void setInventarioJSON(ArrayList<Inventario> response) {
        ArrayList<Inventario> inventario = personaje.getInventario();
        inventario.clear();
        for (int i = 0; i < response.size(); i++) {
            Inventario objeto = new Inventario();
            objeto.setNombre(response.get(i).getNombre());
            objeto.setCantidad(response.get(i).getCantidad());
            inventario.add(objeto);
        }
        personaje.setInventario(inventario);
    }

    private void getSalvaciones(int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<ArrayList<Salvacion>> call = conexion.doGetSalvaciones(id, token);
        call.enqueue(new Callback<ArrayList<Salvacion>>() {
            @Override
            public void onResponse(Call<ArrayList<Salvacion>> call, Response<ArrayList<Salvacion>> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        setSalvacionesJSON(response.body());
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Salvacion>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    private void setSalvacionesJSON(ArrayList<Salvacion> response) {
        ArrayList<Salvacion> salvaciones = personaje.getSalvaciones();
        salvaciones.clear();
        for (int i = 0; i < response.size(); i++) {
            Salvacion salvacion = new Salvacion(response.get(i).getTipo());
            salvacion.setBase(response.get(i).getBase());
            salvacion.setAbility(response.get(i).getAbility());
            salvacion.setMagic(response.get(i).getMagic());
            salvacion.setMisc(response.get(i).getMisc());
            salvaciones.add(salvacion);
        }
    }

    private void getVida(int id) {
        RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

        Call<Vida> call = conexion.doGetVida(id, token);
        call.enqueue(new Callback<Vida>() {
            @Override
            public void onResponse(Call<Vida> call, Response<Vida> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        Vida vida = personaje.getVida();
                        vida.setPgMax(response.body().getPgMax());
                        vida.setPgHeridas(response.body().getPgHeridas());
                        vida.setDanoNoLetal(response.body().getDanoNoLetal());

                        personaje.setVida(vida);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Vida> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    public static void resetPersonaje(){
        personaje = new Personaje();
    }
}