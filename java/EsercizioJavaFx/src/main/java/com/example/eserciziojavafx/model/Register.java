package com.example.eserciziojavafx.model;

import javafx.scene.control.TextArea;
import java.util.Vector;

public class Register {
    private final Vector<Person> register = new Vector<>();

    public boolean addPerson(Person person){
        for (Person person1: register)
            if (person1.getCellNumber().equals(person.getCellNumber())){
                return false;
            }
        else {
            register.add(person);
            }
        return true;
    }

    public Vector<Person> getRegister() {
        return register;
    }
}