package com.example.eserciziojavafx.model;

import java.util.Locale;

public class Person {
    private String nome;
    private String cognome;
    private String cellNumber;

    public Person(String nome, String cognome, String cellNumber) {
        this.nome = nome.toUpperCase(Locale.ROOT);
        this.cognome = cognome.toUpperCase();
        this.cellNumber = cellNumber;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    @Override
    public String toString() {
        return nome + "\t" + cognome + "\t" + cellNumber;
    }
}
