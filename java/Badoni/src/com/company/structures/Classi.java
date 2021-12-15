package com.company.structures;

import com.company.person.Studenti;
import org.jetbrains.annotations.NotNull;

import java.util.Vector;

public class Classi {
    private final Vector<Studenti> alunni = new Vector<>();
    private final String articolazione;
    private final Integer anno;
    private final Character sezione;

    public Classi(Integer anno, Character sezione, String articolazione) throws Exception{
        this.anno = anno;
        this.sezione = Character.toUpperCase(sezione);          //la lettera della classe in maiuscolo 'toUpperCase'
        this.articolazione = articolazione;
    }

    public void addStudent(Studenti student){
        this.alunni.add(student);
    }

    public void removeStudent(@NotNull Studenti student){
        if (alunni.contains(student)) {
            this.alunni.remove(student);
        }
            else
            System.out.println("Studente non appartenente alla classe specificata.");
    }

    public Vector<Studenti> getAlunni() {
        return alunni;
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
