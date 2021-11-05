package com.company.person;

import java.util.Random;

public class Personale extends Person{


    private int reddito;
    private String ruolo;

    public Personale(int eta, String nome, String birthdate) throws Exception {
        super(eta, nome, birthdate);
        this.reddito = new Random().nextInt(5000);      //Setto un reddito casuale tra 0 e 5000
    }

    public void setReddito(int reddito) {
        this.reddito = reddito;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public double getReddito() {
        return reddito;
    }

    public String getRuolo() {
        return ruolo;
    }
}
