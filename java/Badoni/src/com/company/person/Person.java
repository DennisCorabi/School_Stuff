package com.company.person;

import java.util.Date;

public class Person {
    private int eta;
    private String nome;
    private Date birthdate = new Date();

    public Person(int eta, String nome){
        this.eta =eta;
        this.nome= nome;
    }

    public int getEta() {
        return eta;
    }

    public String getNome() {
        return nome;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
