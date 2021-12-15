package com.company.person;

import com.company.structures.Classi;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Studenti extends Person{

    private final UUID matricola;
    private Classi classe = null;

    public Studenti(int eta, String nome, String birthdate, Classi classe) throws Exception {
        super(eta, nome, birthdate);
        this.matricola = UUID.randomUUID();         //matricola identificativa per ogni singolo utente generata a random
        setClasse(classe);

    }

    //altra dichiarazione di uno studente: questa volta senza dichiarare una classe di appartenenza.
    public Studenti(int eta, String nome, String birthdate) throws Exception {
        super(eta, nome, birthdate);
        this.matricola = UUID.randomUUID();

    }

    public void setClasse(@NotNull Classi classe) {              //inserisco lo studente nella classe scelta, che può cambiare
        if (this.classe!=null)
            this.getClasse().removeStudent(this);

        this.classe = classe;
        classe.addStudent(this);
    }

    public UUID getMatricola() {
        return matricola;
    }

    public Classi getClasse() {
        return classe;
    }
}
