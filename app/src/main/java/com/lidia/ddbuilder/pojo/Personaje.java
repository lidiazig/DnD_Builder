package com.lidia.ddbuilder.pojo;

import java.util.ArrayList;

public class Personaje {

    private static Personaje INSTANCE = null;

    private Perfil perfil;
    private ArrayList<Equipo> equipo;
    private Caracteristicas caracteristicas;
    private ArrayList<Inventario> inventario;
    private ArmorClass armorClass;
    private ArrayList<Salvacion> salvaciones;
    private ArrayList<Habilidad> habilidades;
    private ArrayList<Dote> dotes;
    private DatosAdicionales datosAdicionales;
    private Vida vida;

    public Personaje() {
        this.perfil = new Perfil();
        this.caracteristicas = new Caracteristicas();
        this.armorClass = new ArmorClass();
        this.equipo = new ArrayList<>();
        this.inventario = new ArrayList<>();
        this.salvaciones = new ArrayList<>();

        salvaciones.add(new Salvacion("fortaleza"));
        salvaciones.add(new Salvacion("reflejos"));
        salvaciones.add(new Salvacion("voluntad"));


        this.habilidades = new ArrayList<>();
        this.dotes = new ArrayList<>();
        this.datosAdicionales = new DatosAdicionales();
        this.vida = new Vida();
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public ArrayList<Equipo> getEquipo() {
        return equipo;
    }

    public void setEquipo(ArrayList<Equipo> equipo) {
        this.equipo = equipo;
    }

    public Caracteristicas getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Caracteristicas caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public ArrayList<Inventario> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Inventario> inventario) {
        this.inventario = inventario;
    }

    public ArmorClass getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(ArmorClass armorClass) {
        this.armorClass = armorClass;
    }

    public ArrayList<Salvacion> getSalvaciones() {
        return salvaciones;
    }

    public void setSalvaciones(ArrayList<Salvacion> salvaciones) {
        this.salvaciones = salvaciones;
    }

    public ArrayList<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public ArrayList<Dote> getDotes() {
        return dotes;
    }

    public void setDotes(ArrayList<Dote> dotes) {
        this.dotes = dotes;
    }

    public DatosAdicionales getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(DatosAdicionales datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    public Vida getVida() {
        return vida;
    }

    public void setVida(Vida vida) {
        this.vida = vida;
    }

    public void setId(int id) {
        this.perfil.setId(id);
        this.caracteristicas.setIdPersonaje(id);
        // this.armorClass.setIdPersonaje(id)

        for (Equipo e : equipo) {
             e.setIdPersonaje(id);
        }
        for (Inventario e : inventario) {
            e.setIdPersonaje(id);
        }
        for (Salvacion e : salvaciones) {
            e.setIdPersonaje(id);
        }
        for (Habilidad e : habilidades) {
            e.setIdPersonaje(id);
        }
        for (Dote e : dotes) {
            e.setIdPersonaje(id);
        }

        datosAdicionales.setIdPersonaje(id);
        vida.setIdPersonaje(id);
    }


    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Personaje();
        }
    }

    public static Personaje getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
}