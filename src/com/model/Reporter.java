package com.model;

public class Reporter {

 private int id_Reporter;
 private String login;
 private int credit;

 //constructeurs
public Reporter()
{}


    public Reporter(int id_Reporter, String login, int credit) {
        this.id_Reporter = id_Reporter;
        this.login = login;
        this.credit = credit;
    }

    //GETTERS & SETTERS
    public int getId_Reporter() {
        return id_Reporter;
    }

    public void setId_Reporter(int id_Reporter) {
        this.id_Reporter = id_Reporter;
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


    @Override
    public String toString() {
        return "Reporter{" +
                "id_journaliste=" + id_Reporter +
                ", login='" + login + '\'' +
                ", credit=" + credit +
                '}';
    }
}
