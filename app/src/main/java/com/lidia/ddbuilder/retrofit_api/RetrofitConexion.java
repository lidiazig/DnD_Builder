package com.lidia.ddbuilder.retrofit_api;


import com.lidia.ddbuilder.pojo.Alineamiento;
import com.lidia.ddbuilder.pojo.Clase;
import com.lidia.ddbuilder.pojo.Dote;
import com.lidia.ddbuilder.pojo.Habilidad;
import com.lidia.ddbuilder.pojo.Hechizo;
import com.lidia.ddbuilder.pojo.HechizoBase;
import com.lidia.ddbuilder.pojo.Inventario;
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.pojo.Raza;
import com.lidia.ddbuilder.pojo.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitConexion {

    @POST("/api/personaje")
    Call<Personaje> doCreatePersonaje(@Body Personaje personaje);

    @GET("/api/personaje/{id}")
    Call<ArrayList<Personaje>> doGetPersonajesUser(@Path("id") int id);

    @GET("/api/alineamientos")
    Call<ArrayList<Alineamiento>> doGetAlineamientos();

    @GET("/api/clases")
    Call<ArrayList<Clase>> doGetClases();

    @GET("/api/razas")
    Call<ArrayList<Raza>> doGetRazas();

    @GET("/api/hechizos/{id}")
    Call<Hechizo> doGetHechizo(@Path("id") int id);

    @GET("/api/hechizos/clase/{id}")
    Call<ArrayList<HechizoBase>> doGetHechizosClase(@Path("id") int id);

    @POST("/api/usuarios")
    Call<Usuario> doCreateUser(@Body Usuario user);

    @POST("/api/usuarios/login")
    Call<Usuario> doLogin(@Body Usuario user);

    @GET("/api/dotes")
    Call<ArrayList<Dote>> doGetDotes();

    @GET("/api/dotes/{id}")
    Call<Dote> doGetDote(@Path("id") int id);

    @POST("/api/inventario")
    Call<Inventario> doSaveObject(@Body Inventario objeto);

    @GET("/api/inventario/{id}")
    Call<ArrayList<Inventario>> doGetInventario(@Path("id") int id);

    @GET("/api/habilidades")
    Call<ArrayList<Habilidad>> doGetHabilidades();
}
