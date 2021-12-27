package com.company.person;

import com.company.structures.Classi;
import java.util.Vector;


/*
Classe Docente: classe che rapprsenta, all'interno di un ambiente scolastico, la figura del docente.
È una classe figlia della classe "Person"; Perciò, eredita tutti i suoi parametri e metodi pubblici.
 */
public class Docente extends Person{

    private Integer anzianita;      //anni di servizio del docente nella medesima scuola
    private double stipendio;       //stipendio finale (stipendio base + bonus in denaro)
    private double bonus;           //bonus in denaro determinato in base all'anzianità
    private final Vector<Classi> classi = new Vector<>();      //Vettore contenente tutte le classi dove insegna
    private String materia;     //materia che insegna

    //Primo costruttore: vengono assegnati dei valori ai parametri nome, cognome, data di nascita, materia, anzianità e stipendio
    public Docente(String nome,String cognome,String birthdate,String materia, Integer anzianita, double stipendio) throws Exception{
        super(nome,cognome, birthdate);
        this.materia=materia;
        this.anzianita=anzianita;
        setStipendio(stipendio);

    }
    //Secondo costruttore: simile al primo, solamente che non si definisce uno stipendio preciso. In questo caso, lo stipendio di default è di 1500 €.
    public Docente(String nome,String cognome,String birthdate,String materia, Integer anzianita) throws Exception{
        super(nome,cognome, birthdate);
        this.materia=materia;
        this.anzianita=anzianita;
        setStipendio(1500f);

    }

    //metodo per aggiungere una classe all'elenco di classi dove questo docente spiega.
    public void addClasse(Classi classe){
        classi.add(classe);
    }

    /*
    Metodo per eliminare una classe dall'elenco di classi dove il docente spiega.
    Prima di eliminarla, però, devo controllare se è presente all'interno dell'elenco. Posso controllare grazie alla funzione "contains" dell'oggetto di classe Vector.
     */
    public void removeClasse(Classi classe){
        if (classi.contains(classe)){
            classi.remove(classe);
        }
        else{
            System.out.println("In questa classe non insegna il docente "+getNome()+" "+getCognome());      //se la classe non è presente, avviso l'utente.
        }
    }

    //GETTER E SETTER DEI PARAMETRI DELLA CLASSE
    public int getAnzianita() {
        return anzianita;
    }

    //se si cambia l'anzianità, effettua di nuovo il calcolo dello stipendio per vedere se il suo bonus aumenta e, conseguentemente, il suo stipendio.
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

    /*
    Metodo setStipendio: dato uno stipendio di base (passato come parametro), calcolo lo stipendio effettivo (comprensivo di bonus anzianità) in base a quanti di servizio
    ha questo docente nella scuola. Per determinare il bonus, si fa uso di una tabella trovata su un sito (LINK nel documento Google).
    P.S. non so se sia giusta la tabella e tantomeno se sia corretto questo calcolo per lo stipendio. In ogni caso, mi sembrava una cosa bella da inserire.
     */

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

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
