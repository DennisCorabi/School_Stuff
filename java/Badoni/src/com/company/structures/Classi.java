package com.company.structures;

import com.company.person.Studenti;
import java.util.Vector;

/*
Classe Classi: classe che rappresenta la struttura della classe scolastica, presente all'interno di qualsiasi scuola di qualsiasi grado.
 */
public class Classi {
    private final Vector<Studenti> alunni = new Vector<>();     //vettore contente tutti gli alunni della classe
    private final String articolazione;
    private final Integer anno;
    private final Character sezione;

    public Classi(Integer anno, Character sezione, String articolazione) throws Exception{
        this.anno = anno;
        this.sezione = Character.toUpperCase(sezione);          //la lettera della classe in maiuscolo 'toUpperCase'
        this.articolazione = articolazione;
    }

    //metodo per aggiungere studenti alla classe
    public void addStudent(Studenti student){
        this.alunni.add(student);
    }
    /*
    Metodo per rimuovere studenti dalla classe. Prima di effettuare la rimozione, devo controllare se lo studente passato come parametro si trovi all'interno della classe.
     */
    public void removeStudent(Studenti student){
        if (alunni.contains(student)) {
            this.alunni.remove(student);
        }
        else
            System.out.println("Studente non appartenente alla classe specificata.");       //se lo studente non fosse presente, allora informo l'utente dell'errore.
    }

    //GETTER E SETTER DEI PARAMETRI DELLA CLASSE
    public String getClasse(){
        return anno + " " + sezione + " " + articolazione;
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
