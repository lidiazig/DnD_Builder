package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Equipo {
    @SerializedName("id_personaje")
    private int idPersonaje;
    @SerializedName("id_objeto")
    private int idObjeto;
    private String nombre;
    private String propiedades;
    @SerializedName("tipo_objeto")
    private int tipoObjeto;


    public Equipo(String nombre, String propiedades) {
        this.nombre = nombre;
        this.propiedades = propiedades;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(String propiedades) {
        this.propiedades = propiedades;
    }

    public int getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(int tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }
}
