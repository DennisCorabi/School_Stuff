package com.example.demo.service;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDAO personDAO;          //implementa l'interfaccia

    @Autowired
    public PersonService(@Qualifier("fakeDAO") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public void AddPerson(Person person){
        personDAO.insertPerson(person);
    }

    public List<Person> selectAll(){
        return personDAO.getAll();

    }
    public Person getPersonByID(UUID id){
        return  personDAO.getPersonByID(id);
    }

    public void deletePersonByID(UUID id){
        personDAO.deletePersonByID(id);
    }

    public void setPersonByID(UUID id,String nome){
        personDAO.setPersonByID(id, nome);
    }
}
