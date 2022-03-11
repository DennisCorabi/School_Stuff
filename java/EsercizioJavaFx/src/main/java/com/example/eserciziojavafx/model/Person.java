package com.example.eserciziojavafx.model;


import com.google.gson.Gson;

public class Person {
    private String nome;
    private String cognome;
    private String cellNumber;

    public Person(String nome, String cognome, String cellNumber) {


        this.nome =nome.toUpperCase();
        this.cognome = cognome.toUpperCase();
        this.cellNumber = cellNumber;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }
    @Override
    public String toString() {
        return nome + "\t\t" +
                cognome + "\t\t" +
                cellNumber;
    }
}
