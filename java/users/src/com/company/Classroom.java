package com.company;

import java.util.Vector;

public class Classroom {
    int grandezza;
    String nome;
    public Vector<User> classe = new Vector<>(1);
    //STATICO: VARIABILE E/O OGGETTO CONDIVISO DA TUTTI GLI OGGETTI DI UNA CLASSE, REMEMBER.


    public Classroom(String nome,int grandezza){

        this.grandezza = grandezza;
        this.nome = nome;
    }

    public void InsertUsers(String nome,String cognome, int eta) {
        if (classe.capacity()>=grandezza)
            System.out.println("inserimento non riuscito: grandezza massima raggiunta.");
        else
            classe.add(new User(nome,cognome,eta));
    }

    public void GetUsrInfo(String nome, String cognome){
        for(User obj: classe)
            if (obj.name.equals(nome) && obj.cognome.equals(cognome)) {
                System.out.printf("Nome: %s\nCognome: %s\nEta': %d\n", obj.name, obj.cognome, obj.eta);
                break;
            }
        System.out.printf("non sono stati trovati alunni con nome: %s e cognome: %s.\n",nome,cognome);
    }
    public void GetUsers(){
        for (User obj: classe){
            System.out.printf("Nome: %s\tCognome: %s\tEta': %d\n",obj.name,obj.cognome, obj.eta);
        }
        System.out.print("\n");
    }
    public void setName(int index){
        System.out.print("inserisci il nome: ");
        classe.get(index).setNome(Main.scanner.next());
    }
}