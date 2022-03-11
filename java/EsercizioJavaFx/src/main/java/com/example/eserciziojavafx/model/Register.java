package com.example.eserciziojavafx.model;
import java.io.*;
import java.util.Objects;
import java.util.Vector;

public class Register {

    public static final Vector<Person> register = new Vector<>();

    public static boolean addPerson(Person person){
        if (register.size() ==0)
            register.add(person);

        else {
            for (Person person1 : register) {
                if (person1.getCellNumber().equals(person.getCellNumber()))
                    return false;
            }
            register.add(person);
        }
        return true;
    }

    public static boolean removePerson(String cellNumber) {
        for (Person person : register) {
            if (Objects.equals(person.getCellNumber(), cellNumber)) {
                register.remove(person);
                return true;
            }
        }
        return false;
    }

    public static Vector<Person> getRegister() {
        return register;
    }
}