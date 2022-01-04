package com.example.springprog.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class User {
    private UUID id;
    private String nome;
    private String password;
    private Date birthday;
    private String email;


    //costruttore base: tutte le informazioni sono fornite dall'utilizzatore del programma
    public User(String nome, String password, String birthday, String email) throws ParseException {
        this.nome = nome;
        this.password = password;
        this.birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
        this.email=email;
    }

    //Getter and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday.toString();
    }

    public void setBirthday(String birthday) throws ParseException {
        this.birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday); //conversione di una stringa formattata in data
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
