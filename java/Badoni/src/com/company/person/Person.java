package com.company.person;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/*
Classe person: classe che definisce un oggetto che rappresenta una persona generica.
Viene utilizzata in questo sorgente come base da cui poi creare altre classi più specifiche.
 */
public class Person {
    private String nome;
    private String cognome;
    private final Integer eta;
    private final Date birthdate;

    //Costruttore
    public Person(String nome,String cognome, String birthdate) throws Exception {
        this.nome= nome;
        this.cognome=cognome;
        this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate); //conversione di una stringa in un oggetto di classe DATE con la formattazione dd/mm/yyyy
        this.eta=setAge();      //effettua il calcolo dell'età in base al parametro "birthdate" e l'instante nel quale si inizializza l'oggetto

    }

    /*
    Metodo setAge:
    Dato il parametro "birthdate", calcolo l'etò della persona facendo la differenza tra la data di nascita e l'istante nel quale si inizializza l'oggetto.
    Prima converto la data di nascita in millisecondi trascorsi dallo unix epoch, definisco il fuso orario e poi effettuo il casting da tipo "Instant" a "LocalDate"
    Calcolo la differenza fra le due date e infine prelevo solamente gli anni di differenza.
     */
    private Integer setAge(){
        LocalDate bday = Instant.ofEpochMilli(birthdate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        Period period=Period.between(bday, LocalDate.now());
        return period.getYears();
    }

    //GETTER E SETTER DEI PARAMETRI
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Integer getEta() {
        return eta;
    }
}
