package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Arma extends Equipo {
    @SerializedName("bonus_arma")
    private int ataque;
    private String dano;
    private String critico;
    @SerializedName("alcance")
    private int rango;
    @SerializedName("tipo_dano")
    private String tipoDano;
    private int municion;

    public Arma(String nombre, String propiedades) {
        super(nombre, propiedades);
    }

    public Arma(String nombre, String propiedades, int ataque, String dano, String critico, int rango, String tipoDano, int municion) {
        super(nombre, propiedades);
        this.ataque = ataque;
        this.dano = dano;
        this.critico = critico;
        this.rango = rango;
        this.tipoDano = tipoDano;
        this.municion = municion;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public String getDano() {
        return dano;
    }

    public void setDano(String dano) {
        this.dano = dano;
    }

    public String getCritico() {
        return critico;
    }

    public void setCritico(String critico) {
        this.critico = critico;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public String getTipoDano() {
        return tipoDano;
    }

    public void setTipoDano(String tipoDano) {
        this.tipoDano = tipoDano;
    }

    public int getMunicion() {
        return municion;
    }

    public void setMunicion(int municion) {
        this.municion = municion;
    }
}
