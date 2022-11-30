package com.adria.crud.demo.demo;

import java.util.Date;

public class pupilModel {
    private String id,nom, cognoms, curs_actual,progenitors;
    public String data_naixement;

    public pupilModel(String id, String nom, String cognoms, String data_naixement, String curs_actual, String progenitors) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.data_naixement = data_naixement;
        this.curs_actual = curs_actual;
        this.progenitors = progenitors;
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

    public String getData_naixement() {
        return data_naixement;
    }

    public void setData_naixement(String data_naixement) {
        this.data_naixement = data_naixement;
    }

    public String getCurs_actual() {
        return curs_actual;
    }

    public void setCurs_actual(String curs_actual) {
        this.curs_actual = curs_actual;
    }

    public String getProgenitors() {
        return progenitors;
    }

    public void setProgenitors(String progenitors) {
        this.progenitors = progenitors;
    }

}
