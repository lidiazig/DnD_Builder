package com.lidia.ddbuilder.pojo;

public class Equipo {
    private String nombre;
    private String propiedades;


    public Equipo(String nombre, String propiedades) {
        this.nombre = nombre;
        this.propiedades = propiedades;
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
}
