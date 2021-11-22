package com.company.person;

import com.company.structures.Classi;

import java.util.Vector;
public class Docente extends Person{
    private int anzianita;
    private Vector<Classi> classi;

    public Docente(int eta, String nome, String birthdate) throws Exception{
        super(eta, nome, birthdate);
    }


    public int getAnzianita() {
        return anzianita;
    }
    public void setAnzianita(int anzianita) {
        this.anzianita = anzianita;
    }

    public Vector<Classi> getClassi(){
        return classi;
    }
}
