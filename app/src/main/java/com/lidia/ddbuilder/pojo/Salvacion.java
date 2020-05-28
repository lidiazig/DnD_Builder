package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Salvacion {
    @SerializedName("id_personaje")
    private int idPersonaje;
    private String tipo;
    private int base;
    @SerializedName("caracteristica")
    private int ability;
    private int magic;
    private int misc;

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public Salvacion(String tipo) {
        this.tipo = tipo;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public int getTotal() {
      return getBase() + getAbility() + getMagic() + getMisc();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getMisc() {
        return misc;
    }

    public void setMisc(int misc) {
        this.misc = misc;
    }
}
