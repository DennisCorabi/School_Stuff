package com.company.person;

/*
Classe Personale: classe figlia della classe "Person" che rappresenta il personale ATA all'interno di una scuola.
Eredita dalla superclasse i parametri e metodi pubblici. È molto simile alla classe Docente.
 */
public class Personale extends Person{

    private Integer anzianita;
    private double bonus;
    private double stipendio;

    public Personale(String nome,String cognome, String birthdate, Integer anzianita, double stipendio) throws Exception {
        super(nome,cognome, birthdate);
        this.anzianita=anzianita;
        setStipendio(stipendio);
    }

    public Personale(String nome,String cognome, String birthdate, Integer anzianita) throws Exception {
        super(nome,cognome, birthdate);
        this.anzianita=anzianita;
        setStipendio(1500f);
    }


    //Medesimo metodo utilizzato all'interno della classe "Docenti" per calcolare lo stipendio effettivo (base + bonus).
    public void setStipendio(double stipendio){
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

    public Integer getAnzianita() {
        return anzianita;
    }

    //se si cambia l'anzianità, effettua di nuovo il calcolo dello stipendio per vedere se il suo bonus aumenta e, conseguentemente, il suo stipendio.
    public void setAnzianita(Integer anzianita) {
        this.anzianita = anzianita;
        setStipendio(stipendio-bonus);
    }

    public double getStipendio() {
        return stipendio;
    }



}
