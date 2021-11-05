package com.company.person;

import java.util.UUID;

public class Student extends Person{

    private final UUID matricola;
    private String articolazione;
    private Integer anno;
    private Character sezione;

    public Student(int eta, String nome, String birthdate) throws Exception {
        super(eta, nome, birthdate);
        this.matricola = UUID.randomUUID();         //matricola identificativa per ogni singolo utente generata a random
    }


    public void setClasse(Integer anno, Character sezione, String articolazione ){ //setto la classe dello studente (anno,sezione,articolazione)
        this.anno = anno;
        this.sezione = Character.toUpperCase(sezione);          //la lettera della classe in maiuscolo 'toUpperCase'
        this.articolazione = articolazione;
    }

    public String getClasse(){              //ritorno lo classe dello studente per intero
        return anno.toString()+sezione.toString()+" "+articolazione.toString();
    }

    public UUID getMatricola() {
        return matricola;
    }

    public String getArticolazione() {
        return articolazione;
    }

    public Integer getAnno() {
        return anno;
    }

    public Character getSezione() {
        return sezione;
    }
}
