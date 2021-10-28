package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDAO")
public class DataAccess implements PersonDAO{             //utilizza le "funzioni" dell'interfaccia per l'utente
    private final List<Person> DB = new ArrayList<>();  //DB con le utenze

    @Override
    public void insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
    }


    @Override
    public List<Person> getAll() {
        return DB;
    }

    @Override
    public void deletePersonByID(UUID id) {         //elimino un utente dato un ID. Usufruisco della funzione ricerca
        DB.remove(getPersonByID(id));
    }

    @Override
    public Person getPersonByID(UUID id) {              //dato il nome, ritorno la persona ricercata
        for (Person person: DB){
            if (id.equals(person.getId()))
                return person;
        }
        return null;
    }
}
