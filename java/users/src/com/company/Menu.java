package com.company;

import java.util.Scanner;

public class Menu {

    Scuola scuola;
    static Scanner scanner = new Scanner(System.in);

    public Menu(Scuola scuola) {
        this.scuola =scuola;
    }

    public mainMenu(){
        int scelta;
        System.out.print("\n\nscelta: ");
        scelta=scanner.nextInt();

        switch (scelta){
            case 1:
                break;
            case 2:
                secondchange(classe);
                break;
    }

    public static void first(){
        int scelta;
        do{
            System.out.print("\n" +
                    "1: inserisci un alunno" +
                    "\n2: cambia..."+
                    "\n3: stampa informazioni di un alunno specifico" +
                    "\n4: stampa tutti gli alunni di una classe." +
                    "\5: stampa tutte le classi del programma." +
                    "\n0: esci dal programma.");

            System.out.print("\n\nscelta: ");
            scelta=scanner.nextInt();

            switch (scelta){
                case 1:
                    classe.InsertUsers();
                    break;
                case 2:
                    secondchange(classe);
                    break;
                case 3:
                    classe.GetUsrInfo();
                    break;
                case 4:
                    classe.GetUsers();
                    break;
                case 5:

                case 0:
                    break;

                default:
                    System.out.println("inserisci un numero corrispondente alle funzioni!");
                    break;
            }
        }while (scelta!=0);
    }

    public static void secondchange(Classroom classe){
        int scelta;
        System.out.println("\n1: cambia nome di un alunno" +
                "\n2: cambia eta' dell'alunno" +
                "\n3: Cambia cognome di un alunno");

        System.out.print("scelta: ");
        scelta = scanner.nextInt();
        switch(scelta){
            case 1:
                classe.setName();
                break;
            case 2:
                classe.setAge();
                break;
            case 3:
                classe.setCognome();
                break;
            default:
                System.out.println("inserisci un numero corrispondente alle funzioni!");
                break;

        }
    }

    public static Classroom create(){
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

}
