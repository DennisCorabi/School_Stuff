package com.company.person;

import com.company.structures.Classi;
import java.util.UUID;

/*
Classe Studenti: classe figlia della classe "Person" e definisce uno studente.
Preleva dalla classe madre tutti i parametri e metodi pubblici.
 */
public class Studenti extends Person{

    //per generare la matricola, viene utilizzata la classe UUID, la quale genera una sequenza di caratteri che rappresente un valore a 128 bit.
    private final UUID matricola;
    private Classi classe = null;   //all'inizio la classe è null perchè l'utente non ha alcuna classe

    //primo costruttore: assegno un valore ai parametri nome, cognome, data di nascita e classe
    public Studenti(String nome, String cognome, String birthdate, Classi classe) throws Exception {
        super(nome,cognome, birthdate);             //eredito i parametri della classe "Person"
        this.matricola = UUID.randomUUID();         //la matricola dell'utente viene generata a random grazie all'utilizzo di un metodo statico della classe UUID
        setClasse(classe);

    }

    //altra dichiarazione di uno studente: non dichiaro una classe di appartenenza (potrà essere aggiunta in seguito).
    public Studenti(String nome, String cognome,String birthdate) throws Exception {
        super(nome,cognome,birthdate);
        this.matricola = UUID.randomUUID();

    }

    /*
    Metodo setClasse:
    Questo metodo permette di "Inserire" lo studente in una classe. Prima d'inserirlo in una classe, però, bisogna controllare che non sia già presente in un'altra classe;
    in quel caso, lo tolgo prima dalla "vecchia" classe e poi lo inserisco nella nuova, ovvero quella che viene passata come parametro al metodo.
     */
    public void setClasse(Classi classe) {
        if (this.classe!=null)                                  //se lo studente già apparteneva a una classe, lo tolgo dalla classe
            this.getClasse().removeStudent(this);

        this.classe = classe;                                  //lo aggiungo alla nuova classe
        classe.addStudent(this);
    }

    //GETTER E SETTER DEI PARAMETRI DELL'OGGETTO

    public UUID getMatricola() {
        return matricola;
    }

    public Classi getClasse() {
        return classe;
    }



}
