package com.lidia.ddbuilder.retrofit_api;


import com.lidia.ddbuilder.pojo.Clase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitConexion {

    @GET("/api/clases")
    Call<ArrayList<Clase>> doGetClases();
}
