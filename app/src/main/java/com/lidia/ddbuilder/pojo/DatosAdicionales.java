package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class DatosAdicionales {
    @SerializedName("id_personaje")
    private int idPersonaje;
    @SerializedName("reduccion_dano")
    private String redDano;
    private int velocidad;
    @SerializedName("resistencia_conjuros")
    private int resistenciaConjuros;
    @SerializedName("ataque_base")
    private int baseAttack;
    @SerializedName("iniciativa_dote")
    private int featIniciativa;
    @SerializedName("presa_tamano")
    private int size;
    @SerializedName("presa_dote")
    private int miscPresa;

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public String getRedDano() {
        return redDano;
    }

    public void setRedDano(String redDano) {
        this.redDano = redDano;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getResistenciaConjuros() {
        return resistenciaConjuros;
    }

    public void setResistenciaConjuros(int resistenciaConjuros) {
        this.resistenciaConjuros = resistenciaConjuros;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getFeatIniciativa() {
        return featIniciativa;
    }

    public void setFeatIniciativa(int featIniciativa) {
        this.featIniciativa = featIniciativa;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMiscPresa() {
        return miscPresa;
    }

    public void setMiscPresa(int miscPresa) {
        this.miscPresa = miscPresa;
    }
}
