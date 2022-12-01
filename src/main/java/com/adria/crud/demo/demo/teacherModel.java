package com.adria.crud.demo.demo;

public class teacherModel {
    String id, nom , cognoms;
    public teacherModel(String id, String nom, String cognoms) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getCognoms() {
        return cognoms;
    }
    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }
}
