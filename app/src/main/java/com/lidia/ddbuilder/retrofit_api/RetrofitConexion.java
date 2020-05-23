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
import com.lidia.ddbuilder.pojo.Token;
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

    @POST("/api/alineamientos")
    Call<ArrayList<Alineamiento>> doGetAlineamientos(@Body Token token);

    @POST("/api/clases")
    Call<ArrayList<Clase>> doGetClases(@Body Token token);

    @POST("/api/razas")
    Call<ArrayList<Raza>> doGetRazas(@Body Token token);

    @POST("/api/hechizos/{id}")
    Call<Hechizo> doGetHechizo(@Path("id") int id, @Body Token token);

    @POST("/api/hechizos/clase/{id}")
    Call<ArrayList<HechizoBase>> doGetHechizosClase(@Path("id") int id, @Body Token token);

    @POST("/api/usuarios")
    Call<Usuario> doCreateUser(@Body Usuario user);

    @POST("/api/usuarios/login")
    Call<String> doLogin(@Body Usuario user);

    @POST("/api/dotes")
    Call<ArrayList<Dote>> doGetDotes(@Body Token token);

    @POST("/api/dotes/{id}")
    Call<Dote> doGetDote(@Path("id") int id,  @Body Token token);

    @POST("/api/inventario")
    Call<ArrayList<Inventario>> doSaveInventario(@Body ArrayList<Inventario> inventario);

    @POST("/api/inventario/{id}")
    Call<ArrayList<Inventario>> doGetInventario(@Path("id") int id);

    @POST("/api/habilidades")
    Call<ArrayList<Habilidad>> doGetHabilidades(@Body Token token);
}
