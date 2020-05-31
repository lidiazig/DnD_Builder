package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class ArmorClass {
    @SerializedName("id_personaje")
    private int idPersonaje;
    private int armor;
    private int shield;
    private int dex;
    private int size;
    @SerializedName("natural_armor")
    private int naturalArmor;
    private int deflection;
    private int misc;

    public int getAc() {
        return 10 + getArmor() + getShield() + getDex() + getSize() + getNaturalArmor() + getDeflection() + getMisc();
    }

    public int getTouch(){
        return 10 + getDex() + getSize() + getDeflection() + getMisc();
    }

    public int getFlatfooted(){
        return 10 + getArmor() + getShield() + getSize() + getNaturalArmor() + getMisc();
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }


    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNaturalArmor() {
        return naturalArmor;
    }

    public void setNaturalArmor(int naturalArmor) {
        this.naturalArmor = naturalArmor;
    }

    public int getDeflection() {
        return deflection;
    }

    public void setDeflection(int deflection) {
        this.deflection = deflection;
    }

    public int getMisc() {
        return misc;
    }

    public void setMisc(int misc) {
        this.misc = misc;
    }
}
