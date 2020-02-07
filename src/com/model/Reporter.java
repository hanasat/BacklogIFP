package com.model;

public class Reporter {

 private int id_journaliste;
 private String login;
 private int credit;

public Reporter()
{}
    public int getId_journaliste() {
        return id_journaliste;
    }

    public void setId_journaliste(int id_journaliste) {
        this.id_journaliste = id_journaliste;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }



    public Reporter(int id_journaliste, String login, int credit) {
        this.id_journaliste = id_journaliste;
        this.login = login;
        this.credit = credit;
    }

}
