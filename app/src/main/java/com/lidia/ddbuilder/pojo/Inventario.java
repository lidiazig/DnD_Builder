package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Inventario {
    @SerializedName("id_personaje")
    private int idPersonaje;
    private String nombre;
    private String cantidad;

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
