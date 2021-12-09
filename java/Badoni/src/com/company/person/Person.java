package com.company.person;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private Integer eta;
    private String nome;
    private final Date birthdate;

    public Person(Integer eta, String nome, String birthdate) throws Exception {
        this.eta =eta;
        this.nome= nome;
        this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate); //conversione di una stringa in una data con la formattazione dd/mm/yyyy

    }

    public Integer getEta() {
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

    public Date getBirthdate() {
        return birthdate;
    }
}
