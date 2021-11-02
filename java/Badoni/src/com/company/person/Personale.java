package com.company.person;

public class Personale extends Person{


    private long reddito;
    private String ruolo;

    public Personale(int eta, String nome) {
        super(eta, nome);
    }

    public void setReddito(long reddito) {
        this.reddito = reddito;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public long getReddito() {
        return reddito;
    }

    public String getRuolo() {
        return ruolo;
    }
}
