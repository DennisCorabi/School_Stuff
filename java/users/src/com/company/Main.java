package com.company;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
            Classroom classe1 = CreateClassroom();
            InsertUsers(3,classe1);
            classe1.setName(1);
            classe1.GetUsers();
    }



    public static Classroom CreateClassroom(){
        int grandezza;
        String nome;
        System.out.print("inserisci il nome della classe: ");
        nome = scanner.next();

        do{
            System.out.print("inserisci la grandezza della classe (POSITVA): ");
            grandezza=scanner.nextInt();

        }while (grandezza<=0);
        return new Classroom(nome,grandezza);
    }

    public static void InsertUsers(int grandezza, Classroom classe){
        int i,eta;
        String nome,cognome;
        for (i=0;i<grandezza;i++) {
            System.out.printf("Inserisci il nome dell'alunno di registro numero %d: ", i + 1);
            nome = scanner.next();
            System.out.printf("Inserisci il cognome dell'alunno di registro numero %s:", i + 1);
            cognome = scanner.next();
            System.out.printf("Inserisci l'eta' dell'alunno di registro numero %d: ", i + 1);
            eta = scanner.nextInt();
            classe.InsertUsers(nome,cognome,eta);
        }
        classe.GetUsers();
    }
}
