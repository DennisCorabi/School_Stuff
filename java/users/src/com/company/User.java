package com.company;

public class User{
    public String name;
    public int eta;
    public String cognome;

    public User(String nome, String cognome, int eta){
        this.name = nome;
        this.eta = eta;
        this.cognome = cognome;
        }
    public void setNome(String nome){
        this.name=nome;
    }
    public void setEta(int eta){
        this.eta=eta;
    }
    public void setCognome(String cognome){
        this.cognome=cognome;
    }

}

