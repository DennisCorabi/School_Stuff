package com.example.demo.dao;

/*CLASSE CHE DEFISCE COSA L'UTENTE PUO' FARE*/

import com.example.demo.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonDAO {
    int insertPerson(UUID id, Person person);   //inserisce la persona del DB quando ha un ID personalizzato

    default int insertPerson(Person person){   //inserisce la persona nel DB quando non si inserisce un ID specifico (viene generato a caso)N
        UUID id = UUID.randomUUID();
        return insertPerson(id,person);
    }
    List<Person> selectAll();
}
