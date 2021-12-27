package com.company.person;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Person {
    private String nome;
    private String cognome;
    private final Integer eta;
    private final Date birthdate;

    public Person(String nome,String cognome, String birthdate) throws Exception {
        this.nome= nome;
        this.cognome=cognome;
        this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate); //conversione di una stringa in una data con la formattazione dd/mm/yyyy
        this.eta=setAge();

    }

    private Integer setAge(){
        LocalDate bday = Instant.ofEpochMilli(birthdate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        Period period=Period.between(bday, LocalDate.now());
        return period.getYears();
    }

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
