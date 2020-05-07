package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Habilidad {
    private int id;
    private String nombre;
    private boolean penalizacion;
    @SerializedName("solo_entrenamiento")
    private boolean soloEntrenamiento;
    private String caracteristica;
    private int rangos;
    @SerializedName("mod_varios")
    private int modVarios;
    private int penalizador;
    @SerializedName("mod_habilidad")
    private int modHabilidad;

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

    public boolean isPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(boolean penalizacion) {
        this.penalizacion = penalizacion;
    }

    public boolean isSoloEntrenamiento() {
        return soloEntrenamiento;
    }

    public void setSoloEntrenamiento(boolean soloEntrenamiento) {
        this.soloEntrenamiento = soloEntrenamiento;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public int getRangos() {
        return rangos;
    }

    public void setRangos(int rangos) {
        this.rangos = rangos;
    }

    public int getModVarios() {
        return modVarios;
    }

    public void setModVarios(int modVarios) {
        this.modVarios = modVarios;
    }

    public int getPenalizador() {
        return penalizador;
    }

    public void setPenalizador(int penalizador) {
        this.penalizador = penalizador;
    }

    public int getModHabilidad() {
        return modHabilidad;
    }

    public void setModHabilidad(int modHabilidad) {
        this.modHabilidad = modHabilidad;
    }
}
