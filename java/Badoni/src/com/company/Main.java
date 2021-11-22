package com.company;

import com.company.person.*;
import com.company.structures.Classi;
import com.google.gson.JsonObject;

public class Main {

    public static void main(String[] args) throws Exception {

        Classi classe1 = new Classi(4,'a',"Informatica");
        Student studente1 = new Student(17, "dennis", "28/09/2004", classe1);
        Personale bidello = new Personale(51,"eugenio","17/05/1965");

        JsonObject user = studente1.getJson();

        System.out.println(user);
        System.out.println(studente1.getJson());
        System.out.println(bidello.getJson());
    }
}
