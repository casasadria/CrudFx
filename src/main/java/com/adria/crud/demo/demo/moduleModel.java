package com.adria.crud.demo.demo;

public class moduleModel {
    String id, nom, id_professor,professor;
    public moduleModel(String id, String nom, String id_professor, String professor) {
        this.id = id;
        this.nom = nom;
        this.id_professor = id_professor;
        this.professor = professor;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public moduleModel(String id, String nom, String id_professor) {
        this.id = id;
        this.nom = nom;
        this.id_professor = id_professor;
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
    public String getId_professor() {
        return id_professor;
    }
    public void setId_professor(String id_professor) {
        this.id_professor = id_professor;
    }
}
