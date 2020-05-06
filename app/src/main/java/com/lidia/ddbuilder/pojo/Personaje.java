package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Personaje {
    private int id;
    @SerializedName("id_usuario")
    private int idUsuario;
    private String nombre;
    private String nivel;
    @SerializedName("id_clase")
    private int idClase;
    @SerializedName("id_raza")
    private int idRaza;
    @SerializedName("id_alineamiento")
    private int idAlineamiento;
    private String genero;
    private String tamano;
    private String edad;
    @SerializedName("id_imagen")
    private int idImagen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public int getIdAlineamiento() {
        return idAlineamiento;
    }

    public void setIdAlineamiento(int idAlineamiento) {
        this.idAlineamiento = idAlineamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }
}
