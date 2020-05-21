package com.lidia.ddbuilder.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Personaje {

    private static Personaje INSTANCE = null;
    private int id;
    @SerializedName("id_usuario")
    private int idUsuario;
    private String nombre;
    private String nivel;
    @SerializedName("id_clase")
    private int idClase;
    @SerializedName("id_raza")
    private int idRaza;
    @SerializedName("id_alineamiento")
    private int idAlineamiento;
    private String genero;
    private String tamano;
    private String edad;
    private String idiomas;
    @SerializedName("id_imagen")
    private int idImagen;
    private ArrayList<Equipo> equipo;
    private Caracteristicas caracteristicas;
    private ArrayList<Inventario> inventario;
    private ArmorClass armorClass;
    private ArrayList<Salvacion> salvaciones;
    private ArrayList<Habilidad> habilidades;
    private ArrayList<Dote> dotes;
    private int pgMax;
    private int pgHeridas;
    private int danoNoLetal;
    private String redDano;
    private int velocidad;
    private int resistenciaConjuros;
    private int baseAttack;
    private int featIniciativa;
    private int size;
    private int miscPresa;

    public Personaje() {
        this.caracteristicas = new Caracteristicas();
        this.armorClass = new ArmorClass();
        this.equipo = new ArrayList<>();
        this.inventario = new ArrayList<>();
        this.salvaciones = new ArrayList<>();
        this.habilidades = new ArrayList<>();
        this.dotes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public int getIdAlineamiento() {
        return idAlineamiento;
    }

    public void setIdAlineamiento(int idAlineamiento) {
        this.idAlineamiento = idAlineamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
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