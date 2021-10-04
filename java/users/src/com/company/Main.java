package com.company;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
            Classroom classe1 = CreateClassroom();
            classe1.InsertUsers("dennis","corabi",17);
            classe1.GetUsrInfo("dennis","corabi");
            classe1.GetUsrInfo("giovanni","o");
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
    public void InsertUsers(int grandezza){
        int i,eta;
        String nome,cognome;
        for (i=0;i<grandezza;i++) {
            System.out.printf("inserisci il nome dell'alunno di registro numero %d: ", i + 1);
            nome = scanner.next();
            System.out.printf("\ninserisci il cognome dell'alunno di registro numero %s:", i + 1);
            cognome = scanner.next();
            System.out.printf("inserisci l'eta' dell'alunno di registro numero %d: ", i + 1);
            eta = scanner.nextInt();
            //TODO

        }
    }
}
