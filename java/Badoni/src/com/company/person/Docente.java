package com.company.person;

import com.company.structures.Classi;

import java.util.Vector;
public class Docente extends Person{
    private Integer anzianita;
    private double stipendio;
    private double bonus;

    private Vector<Classi> classi = new Vector<>();

    public Docente(String nome,String cognome,String birthdate,Integer anzianita, double stipendio) throws Exception{
        super(nome,cognome, birthdate);
        this.anzianita=anzianita;
        setStipendio(stipendio);

    }

    public void addClasse(Classi classe){
        classi.add(classe);
    }

    public void removeClasse(Classi classe){
        if (classi.contains(classe)){
            classi.remove(classe);
        }
        else{
            System.out.println("In questa classe non insegna il docente "+getNome()+" "+getCognome());
        }
    }

    public int getAnzianita() {
        return anzianita;
    }
    public void setAnzianita(int anzianita) {
        this.anzianita = anzianita;
        setStipendio(stipendio-bonus);
    }

    public Vector<Classi> getClassi(){
        return classi;
    }

    public double getStipendio() {
        return stipendio;
    }

    public void setStipendio(double stipendio) {
        if (anzianita>=9 && anzianita<=14)
           bonus=257.44;
        else if (anzianita>=15 && anzianita<=20)
            bonus=452.87;
        else if (anzianita>=21 && anzianita<=27)
            bonus=701.81;
        else if (anzianita>=28 && anzianita<=34)
            bonus=864.90;
        else if(anzianita>=35)
            bonus=994.91;
        else
            bonus=0;

         this.stipendio=stipendio+bonus;
    }
}
