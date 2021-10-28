package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


// CONTROLLER (API) -- SERVICE (BUSINNES LOGIC)-- DATA ACCESS (INTERFACE DAO)

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {                     //person controller
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping                //richiesta HTTP per scrivere su un database
    public void addPerson(@RequestBody Person person){
        personService.AddPerson(person);
    }
    @GetMapping
    public List<Person> selectAll(){
        return personService.selectAll();
    }
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){         //pathvariable: chiedo alla pagina di "darmi" un nome
        return personService.getPersonByID(id);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonByID(@PathVariable("id") UUID id){          //funzione che implementa la richiesta di eliminare un untente passandogli il suo ID
        personService.deletePersonByID(id);
    }
}