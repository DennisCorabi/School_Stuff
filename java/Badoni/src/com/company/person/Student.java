package com.company.person;

import com.company.structures.Classi;

import java.util.UUID;

public class Student extends Person {

    private final UUID matricola;
    private Classi classe;

    public Student(int eta, String nome, String birthdate, Classi classe) throws Exception {
        super(eta, nome, birthdate);
        this.matricola = UUID.randomUUID();         //matricola identificativa per ogni singolo utente generata a random
        setClasse(classe);

    }

    //altra dichiarazione di uno studente senza classe
    public Student(int eta, String nome, String birthdate) throws Exception {
        super(eta, nome, birthdate);
        this.matricola = UUID.randomUUID();
        setClasse(classe);

    }

    public void setClasse(Classi classe) {              //inserisco lo studente nella classe scelta, che può cambiare
        this.classe = classe;
        classe.addStudent(this);
    }

    public UUID getMatricola() {
        return matricola;
    }
}
