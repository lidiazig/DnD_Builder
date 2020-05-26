package com.lidia.ddbuilder.retrofit_api;

import com.lidia.ddbuilder.pojo.Alineamiento;
import com.lidia.ddbuilder.pojo.Arma;
import com.lidia.ddbuilder.pojo.Armadura;
import com.lidia.ddbuilder.pojo.Caracteristicas;
import com.lidia.ddbuilder.pojo.Clase;
import com.lidia.ddbuilder.pojo.DatosAdicionales;
import com.lidia.ddbuilder.pojo.Dote;
import com.lidia.ddbuilder.pojo.Equipo;
import com.lidia.ddbuilder.pojo.Habilidad;
import com.lidia.ddbuilder.pojo.Hechizo;
import com.lidia.ddbuilder.pojo.HechizoBase;
import com.lidia.ddbuilder.pojo.Inventario;
import com.lidia.ddbuilder.pojo.Perfil;
import com.lidia.ddbuilder.pojo.Personaje;
import com.lidia.ddbuilder.pojo.PersonajeLista;
import com.lidia.ddbuilder.pojo.Raza;
import com.lidia.ddbuilder.pojo.Salvacion;
import com.lidia.ddbuilder.pojo.Token;
import com.lidia.ddbuilder.pojo.Usuario;
import com.lidia.ddbuilder.pojo.Vida;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitConexion {

    //Alineamientos
    @POST("/api/alineamientos")
    Call<ArrayList<Alineamiento>> doGetAlineamientos(@Body Token token);

    @POST("/api/alineamientos/{id}")
    Call<ArrayList<Alineamiento>> doGetAlineamiento(@Path("id") int id, @Body Token token);

    //Caracteristicas
    @POST("/api/caracteristicas/save")
    Call<Caracteristicas> doSaveCaracteristicas(@Body Caracteristicas caracteristicas);

    @POST("/api/caracteristicas/get/{id}")
    Call<Caracteristicas> doGetCaracteristicas(@Path("id") int id, @Body Token token);

    //Clases
    @POST("/api/clases")
    Call<ArrayList<Clase>> doGetClases(@Body Token token);

    @POST("/api/clases/{id}")
    Call<ArrayList<Clase>> doGetClase(@Path("id") int id,@Body Token token);

    //Datos adicionales
    @POST("/api/datos/save")
    Call<DatosAdicionales> doSaveDatos(@Body DatosAdicionales datosAdicionales);

    @POST("/api/datos/get/{id}")
    Call<DatosAdicionales> doGetDatos(@Path("id") int id, @Body Token token);

    //Dotes
    @POST("/api/dotes")
    Call<ArrayList<Dote>> doGetDotes(@Body Token token);

    @POST("/api/dotes/personaje/{id}")
    Call<ArrayList<Dote>> doGetDotesPj(@Path("id") int id, @Body Token token);

    @POST("/api/dotes/{id}")
    Call<Dote> doGetDote(@Path("id") int id, @Body Token token);

    //Equipo
    @POST("/api/equipo/save")
    Call<ArrayList<Equipo>> doSaveEquipo(@Body ArrayList<Equipo> equipo);

    @POST("/api/dotes/personaje/{id}")
    Call<ArrayList<Equipo>> doGetEquipoPj(@Path("id") int id, @Body Token token);

    @POST("/api/equipo/arma/{id}")
    Call<Arma> doGetArma(@Path("id") int id, @Body Token token);

    @POST("/api/equipo/armadura/{id}")
    Call<Armadura> doGetArmadura(@Path("id") int id, @Body Token token);

    //Habilidades
    @POST("/api/habilidades")
    Call<ArrayList<Habilidad>> doGetHabilidades(@Body Token token);

    //Hechizos
    @POST("/api/hechizos/{id}")
    Call<Hechizo> doGetHechizo(@Path("id") int id, @Body Token token);

    @POST("/api/hechizos/clase/{id}")
    Call<ArrayList<HechizoBase>> doGetHechizosClase(@Path("id") int id, @Body Token token);

    //Inventario
    @POST("/api/inventario")
    Call<ArrayList<Inventario>> doSaveInventario(@Body ArrayList<Inventario> inventario);

    @POST("/api/inventario/{id}")
    Call<ArrayList<Inventario>> doGetInventario(@Path("id") int id, @Body Token token);

    //Personaje
    @POST("/api/personaje")
    Call<Integer> doSavePersonaje(@Body Perfil personaje);

    @POST("/api/personaje/{id}")
    Call<Perfil> doGetPersonaje(@Path("id") int id, @Body Token token);

    @POST("/api/personaje/user/{id}")
    Call<ArrayList<PersonajeLista>> doGetPersonajesUser(@Path("id") Token id, @Body Token token);

    @POST("/api/personaje/delete/{id}")
    Call<Perfil> doDeletePersonaje(@Path("id") int id, @Body Token token);

    //Razas
    @POST("/api/razas")
    Call<ArrayList<Raza>> doGetRazas(@Body Token token);

    @POST("/api/razas/{id}")
    Call<ArrayList<Raza>> doGetRaza(@Path("id") int id,@Body Token token);

    //Salvaciones
    @POST("/api/salvaciones/save")
    Call<ArrayList<Salvacion>> doSaveSalvaciones(@Body ArrayList<Salvacion> salvaciones);

    @POST("/api/salvaciones/get/{id}")
    Call<ArrayList<Salvacion>> doGetSalvaciones(@Path("id") int id, @Body Token token);

    //Usuarios
    @POST("/api/usuarios")
    Call<Usuario> doCreateUser(@Body Usuario user);

    @POST("/api/usuarios/login")
    Call<String> doLogin(@Body Usuario user);

    //Vida
    @POST("/api/vida/save")
    Call<Vida> doSaveVida(@Body Vida vida);

    @POST("/api/vida/get/{id}")
    Call<Vida> doGetVida(@Path("id") int id, @Body Token token);
}
