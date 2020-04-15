package com.lidia.ddbuilder.retrofit_api;


import com.lidia.ddbuilder.pojo.Alineamiento;
import com.lidia.ddbuilder.pojo.Clase;
import com.lidia.ddbuilder.pojo.Hechizo;
import com.lidia.ddbuilder.pojo.HechizoBase;
import com.lidia.ddbuilder.pojo.Raza;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitConexion {

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
}
