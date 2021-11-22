package com.company.structures;

import com.company.person.Student;

import java.util.Vector;

public class Classi {
    private Vector<Student> alunni;
    private final String articolazione;
    private final Integer anno;
    private final Character sezione;

    public Classi(Integer anno, Character sezione, String articolazione){
        this.anno = anno;
        this.sezione = Character.toUpperCase(sezione);          //la lettera della classe in maiuscolo 'toUpperCase'
        this.articolazione = articolazione;
    }

    public void addStudent(Student student){
        alunni.add(student);
    }

    public Vector<Student> getAlunni() {
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
