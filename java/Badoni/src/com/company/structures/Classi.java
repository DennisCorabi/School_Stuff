package com.company.structures;

import com.company.person.Student;

import java.util.Vector;

public class Classi {
    private final Vector<Student> alunni = new Vector<>();
    private final String articolazione;
    private final Integer anno;
    private final Character sezione;

    public Classi(Integer anno, Character sezione, String articolazione) throws Exception{
        this.anno = anno;
        this.sezione = Character.toUpperCase(sezione);          //la lettera della classe in maiuscolo 'toUpperCase'
        this.articolazione = articolazione;
    }

    public void addStudent(Student student){
        this.alunni.add(student);
    }

    public void rmStudent(Student student){
        if (alunni.contains(student))
            this.alunni.remove(student);
        else
            System.out.println("Studente non appartenente alla classe specificata.");
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
