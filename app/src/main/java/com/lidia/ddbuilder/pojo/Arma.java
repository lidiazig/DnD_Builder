package com.lidia.ddbuilder.pojo;

public class Arma extends Equipo {
    private int ataque;
    private String dano;
    private String critico;
    private int rango;
    private String tipo;
    private int municion;

    public Arma(String nombre, String propiedades) {
        super(nombre, propiedades);
    }

    public Arma(String nombre, String propiedades, int ataque, String dano, String critico, int rango, String tipo, int municion) {
        super(nombre, propiedades);
        this.ataque = ataque;
        this.dano = dano;
        this.critico = critico;
        this.rango = rango;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMunicion() {
        return municion;
    }

    public void setMunicion(int municion) {
        this.municion = municion;
    }
}
