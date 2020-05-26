package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Caracteristicas {
    @SerializedName("id_personaje")
    private int idPersonaje;
    private int fuerza;
    private int destreza;
    private int constitucion;
    private int inteligencia;
    private int sabiduria;
    private int carisma;

    public Caracteristicas() {
        this.fuerza = 10;
        this.destreza = 10;
        this.constitucion = 10;
        this.inteligencia = 10;
        this.sabiduria = 10;
        this.carisma = 10;
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getConstitucion() {
        return constitucion;
    }

    public void setConstitucion(int constitucion) {
        this.constitucion = constitucion;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getSabiduria() {
        return sabiduria;
    }

    public void setSabiduria(int sabiduria) {
        this.sabiduria = sabiduria;
    }

    public int getCarisma() {
        return carisma;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    public int getModificador(int num){
        return (num - 10) / 2;
    }

    public String getModificadorStr(int num){
        int mod = getModificador(num);
        if (mod >= 0)
            return "+" + mod;
        else
            return "" + mod;
    }
}
