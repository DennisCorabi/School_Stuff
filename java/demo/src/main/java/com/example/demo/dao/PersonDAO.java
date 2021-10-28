package com.example.demo.dao;

/*CLASSE CHE DEFISCE COSA L'UTENTE PUO' FARE*/

import com.example.demo.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonDAO {
    void insertPerson(UUID id, Person person);   //inserisce la persona del DB quando ha un ID personalizzato

    default void insertPerson(Person person){   //inserisce la persona nel DB quando non si inserisce un ID specifico (viene generato a caso)N
        UUID id = UUID.randomUUID();
        insertPerson(id, person);
    }
    List<Person> getAll();

    void deletePersonByID(UUID id);
    Person getPersonByID(UUID id);              //dato un ID, cerca nel DB un utente con questo ID
}
