package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Armadura extends Equipo {
    @SerializedName("tipo_armadura")
    private String tipo;
    @SerializedName("bonus_armadura")
    private int bonus;
    @SerializedName("max_destreza")
    private int maxDex;
    private int penalty;
    @SerializedName("fallo_conjuro")
    private String spellFailure;
    private int velocidad;
    private int peso;

    public Armadura(String nombre, String propiedades) {
        super(nombre, propiedades);
    }

    public Armadura(String nombre, String propiedades, String tipo, int bonus, int maxDex, int penalty, String spellFailure, int velocidad, int peso) {
        super(nombre, propiedades);
        this.tipo = tipo;
        this.bonus = bonus;
        this.maxDex = maxDex;
        this.penalty = penalty;
        this.spellFailure = spellFailure;
        this.velocidad = velocidad;
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getMaxDex() {
        return maxDex;
    }

    public void setMaxDex(int maxDex) {
        this.maxDex = maxDex;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public String getSpellFailure() {
        return spellFailure;
    }

    public void setSpellFailure(String spellFailure) {
        this.spellFailure = spellFailure;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
