package com.example.demo.Service;

import com.example.demo.DataAccess.Repository;
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
    //creo una istanza del mio data Access
    Repository mysqlrepo;

    @Autowired
    public StudentService(Repository repository){
        this.mysqlrepo = repository;
    }


    public List<Student> getAllStudents(){
        return mysqlrepo.findAll();
    }

    public void insertStudent(Student student){
        mysqlrepo.save(student);
    }

    public Student getStudentById(Long id){
        return mysqlrepo.getStudentById(id);
    }

    public void setStudentName(long oldId, String nome){
        Student student = mysqlrepo.getStudentById(oldId);
        student.setName(nome);
        mysqlrepo.save(student);
    }


}
