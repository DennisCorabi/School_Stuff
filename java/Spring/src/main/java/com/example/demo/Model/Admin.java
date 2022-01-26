package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin {

    private String name;
    public String password;
    private Long id;

    public Admin() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}