package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Classroom {
    int grandezza;
    public static Scanner scanner = new Scanner(System.in);
    public Vector<User> classe = new Vector<>(2);
    //STATICO: VARIABILE E/O OGGETTO CONDIVISO DA TUTTI GLI OGGETTI DI UNA CLASSE, REMEMBER.
    private int cont=0;


    public Classroom(int grandezza){

        this.grandezza = grandezza;
    }

    public void InsertUsers(String nome, int eta) {
        if (classe.capacity()==grandezza)      //TODO NON FUNZIONA, RISOLVERE.
            System.out.println("inserimento non riuscito: grandezza massima raggiunta.");
        else {
            classe.add(new User(nome, eta));
            cont++;
        }
    }

    public void GetUsrInfo(String nome){
        for(User obj: classe)
            if (obj.name.equals(nome))
                System.out.printf("Nome: %s\tEta': %d",obj.name,obj.eta);
        System.out.print("\n");

    }
    public void GetUsers(){
        for (User obj: classe){
            System.out.printf("Nome: %s\tEta': %d\n",obj.name,obj.eta);

        }
    }
}


class User {
    public String name;
    public int eta;

    public User(String nome, int eta){
        this.name = nome;
        this.eta = eta;
    }
}