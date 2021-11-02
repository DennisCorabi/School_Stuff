package com.company.person;

import com.company.Main;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Student extends Person{

    private final UUID matricola;
    private Date birthdate;
    private String articolazione;
    private Integer anno;
    private Character sezione;

    public Student(int eta, String nome, String classe) {
        super(eta, nome);
        this.matricola = UUID.randomUUID();
    }

    public void setClasse(Integer anno, Character sezione, String articolazione ){
        this.anno = anno;
        this.sezione = Character.toUpperCase(sezione);
        this.articolazione = articolazione;
    }
    public String getClasse(){
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
