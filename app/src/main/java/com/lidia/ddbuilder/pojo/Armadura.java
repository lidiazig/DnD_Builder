package com.lidia.ddbuilder.pojo;

public class Armadura extends Equipo {
    private String tipo;
    private int bonus;
    private int maxDex;
    private int penalty;
    private String spellFailure;
    private int velocidad;
    private int peso;

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
