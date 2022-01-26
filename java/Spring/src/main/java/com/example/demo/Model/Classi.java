package com.example.demo.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Classi")
public class Classi {

    private String anno;
    private String sezione;
    private String articolazione;
    private Long id;

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getSezione() {
        return sezione;
    }

    public void setSezione(String sezione) {
        this.sezione = sezione;
    }

    public String getArticolazione() {
        return articolazione;
    }

    public void setArticolazione(String articolazione) {
        this.articolazione = articolazione;
    }





    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }


}
