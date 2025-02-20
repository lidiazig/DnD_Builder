package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

public class Vida {
    @SerializedName("id_personaje")
    private int idPersonaje;
    @SerializedName("pg_max")
    private int pgMax;
    @SerializedName("heridas")
    private int pgHeridas;
    @SerializedName("dano_no_letal")
    private int danoNoLetal;

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public int getPgMax() {
        return pgMax;
    }

    public void setPgMax(int pgMax) {
        this.pgMax = pgMax;
    }

    public int getPgHeridas() {
        return pgHeridas;
    }

    public void setPgHeridas(int pgHeridas) {
        this.pgHeridas = pgHeridas;
    }

    public int getDanoNoLetal() {
        return danoNoLetal;
    }

    public void setDanoNoLetal(int danoNoLetal) {
        this.danoNoLetal = danoNoLetal;
    }
}
