package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDAO")
public class fake implements PersonDAO{             //utilizza le "funzioni" dell'interfaccia per l'utente
    private final List<Person> DB = new ArrayList<>();  //DB con le utenze

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1; //inserimento avvenuto con successo, ora passa la palla al servizio
    }

    @Override
    public List<Person> selectAll() {
        return DB;
    }
}
