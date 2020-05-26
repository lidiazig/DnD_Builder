package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Dote {
    @SerializedName("id_personaje")
    private int idPersonaje;
    private int id;
    private String nombre;
    private String prerrequisito;
    private String descripcion;
    private String notas;

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

    public String getPrerrequisito() {
        return prerrequisito;
    }

    public void setPrerrequisito(String prerrequisito) {
        this.prerrequisito = prerrequisito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
