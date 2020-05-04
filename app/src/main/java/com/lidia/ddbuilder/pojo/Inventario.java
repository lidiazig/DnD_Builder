package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Inventario {
    @SerializedName("id_objeto")
    private int id;
    private String nombre;
    private String cantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
