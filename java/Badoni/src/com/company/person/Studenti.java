package com.company.person;

import com.company.structures.Classi;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Studenti extends Person{

    private final UUID matricola;
    private Classi classe = null;

    public Studenti(String nome, String cognome, String birthdate, Classi classe) throws Exception {
        super(nome,cognome, birthdate);
        this.matricola = UUID.randomUUID();         //matricola identificativa per ogni singolo utente generata a random
        setClasse(classe);

    }

    //altra dichiarazione di uno studente: questa volta senza dichiarare una classe di appartenenza.
    public Studenti(String nome, String cognome,String birthdate) throws Exception {
        super(nome,cognome,birthdate);
        this.matricola = UUID.randomUUID();

    }

    //inserisco lo studente nella classe scelta, che può cambiare
    public void setClasse(Classi classe) {
        if (this.classe!=null)                                  //se lo studente già apparteneva ad una classe, lo tolgo dalla classe
            this.getClasse().removeStudent(this);

        this.classe = classe;                                  //lo aggiungo alla nuova classe
        classe.addStudent(this);
    }

    public UUID getMatricola() {
        return matricola;
    }

    public Classi getClasse() {
        return classe;
    }


}
