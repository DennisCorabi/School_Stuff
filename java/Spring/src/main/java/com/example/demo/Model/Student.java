package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity         //trasforma l'oggetto in una tabella
@Table          //li inserisce in una tabella (nome di default della tabella = nome della classe)
public class  Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private LocalDate dob;
    private String email;

    public Student(String name, String password) {
        this.name = name;
        this.password=password;
    }

    public Student(String name, String password, LocalDate dob, String email) {
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public Student(String name, Long id, String password, LocalDate dob, String email) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.email = email;
    }

    //costruttore necessario per la trasformazione in tabella
    public Student() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                '}';
    }
}
