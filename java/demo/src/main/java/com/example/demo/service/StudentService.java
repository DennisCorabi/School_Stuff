package com.example.demo.Service;

import com.example.demo.DataAccess.ClassDBaccess;
import com.example.demo.DataAccess.UserDBaccess;
import com.example.demo.Model.Classi;
import com.example.demo.Model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Livello di servizio della parte di back-end di una web-app.
Si occupa della businnes logic e comunica direttamente con il gestore del database.
In un certo modo, è qui che incomincia la parte di java/spring in una web application, dal momento che
la parte di interfacciamento con l'utente (API) verrà gestita direttamente dal front-end (xmlhttprequest di JS)
 */
@Service
public class StudentService {
    //creo una istanza del mio data Access per utenti e classi
    UserDBaccess mysqlUserAccess;
    ClassDBaccess mysqlClassAccess;

    @Autowired
    public StudentService(UserDBaccess userDBaccess, ClassDBaccess classDBaccess){
        this.mysqlUserAccess = userDBaccess;
        this.mysqlClassAccess = classDBaccess;
    }

    public void insertStudent(Student student){
        mysqlUserAccess.save(student);
    }

    public Student getStudentById(Long id){
        return mysqlUserAccess.getStudentById(id);
    }

    public Classi getClasse(Classi classe){
        return mysqlClassAccess.getClassiByAnnoAndSezioneAndArticolazione(classe.getAnno(), classe.getSezione(), classe.getArticolazione());
    }

    public void setClasse(Classi classe){
        mysqlClassAccess.save(classe);
    }

}
