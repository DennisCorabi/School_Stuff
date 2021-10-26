package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        System.out.println(person.getName());
        personService.AddPerson(person);
    }
    @GetMapping                         //richiesta HTTP per prelevare dati da un DB
    public List<Person> selectAll(){
        return personService.selectAll();
    }
}