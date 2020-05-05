package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Personaje {
    private int id;
    @SerializedName("id_usuario")
    private int idUsuario;
    private String nombre;
    private int nivel;
    @SerializedName("id_clase")
    private int idClase;
    @SerializedName("id_raza")
    private int idRaza;
    @SerializedName("id_alineamiento")
    private int idAlineamiento;
    private String genero;
    private String tamano;
    private int edad;
    @SerializedName("id_imagen")
    private int idImagen;


}
