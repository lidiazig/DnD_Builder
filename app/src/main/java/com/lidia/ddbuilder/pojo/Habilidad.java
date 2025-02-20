package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Habilidad {
    @SerializedName("id_personaje")
    private int idPersonaje;
    private int id;
    private String nombre;
    private int penalizacion;
    @SerializedName("solo_entrenamiento")
    private int soloEntrenamiento;
    private String caracteristica;
    private int rangos;
    @SerializedName("mod_varios")
    private int modVarios;
    private int penalizador;
    @SerializedName("mod_habilidad")
    private String modHabilidad;

    public Habilidad() {
        this.rangos = 0;
        this.modVarios = 0;
        this.penalizador = 0;
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

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

    public int isPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(int penalizacion) {
        this.penalizacion = penalizacion;
    }

    public int isSoloEntrenamiento() {
        return soloEntrenamiento;
    }

    public void setSoloEntrenamiento(int soloEntrenamiento) {
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

    public String getModHabilidad() {
        return modHabilidad;
    }

    public void setModHabilidad(String modHabilidad) {
        this.modHabilidad = modHabilidad;
    }
}
