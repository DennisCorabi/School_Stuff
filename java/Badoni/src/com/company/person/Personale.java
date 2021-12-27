package com.company.person;

import java.util.Random;

public class Personale extends Person{


    private double stipendio;

    public Personale(String nome,String cognome, String birthdate) throws Exception {
        super(nome,cognome, birthdate);
        this.stipendio = new Random().nextInt(5000);      //Setto un reddito casuale tra 0 e 5000
    }

    public void setReddito(int reddito) {
        this.stipendio = reddito;
    }


    public double getReddito() {
        return stipendio;
    }

}
