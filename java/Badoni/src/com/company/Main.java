package com.company;

import com.company.person.*;
import com.google.gson.JsonObject;

public class Main {

    public static void main(String[] args) throws Exception {

        Student studente1 = new Student(17, "dennis", "28/09/2004");
        Personale bidello = new Personale(51,"eugenio","17/05/1965");
        studente1.setClasse(4,'a',"Informatica");

        JsonObject user = studente1.getJson();

        System.out.println(user);
        System.out.println(studente1.getJson());
        System.out.println(bidello.getJson());
    }
}
