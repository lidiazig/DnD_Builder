package com.lidia.ddbuilder.retrofit_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {

    private static final String BASE_URL = "http://192.168.1.33:3000";

    public static Retrofit getConexion()
    {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }}
