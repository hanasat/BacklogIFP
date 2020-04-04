package com.model;

public class Tag {

    private  int id_tag;
    private String nom;

    public Tag(){}

    public Tag(int id_tag, String nom) {
        this.id_tag = id_tag;
        this.nom = nom;
    }

    public int getId_tag() {
        return id_tag;
    }

    public void setId_tag(int id_tag) {
        this.id_tag = id_tag;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id_tag=" + id_tag +
                ", nom='" + nom + '\'' +
                '}';
    }
}
