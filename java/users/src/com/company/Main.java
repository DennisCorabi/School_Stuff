package com.company;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        Classroom classe1 = CreateClassroom();
        menu(classe1);
    }

    public static void menu(Classroom classe1){
        int scelta;
        do{
            System.out.print("\n" +
                    "1: inserisci un alunno" +
                    "\n2: cambia..."+
                    "\n3: stampa informazioni di un alunno specifico" +
                    "\n4: stampa tutti gli alunni di una classe." +
                    "\n0: esci dal programma.");

            System.out.print("\n\nscelta: ");
            scelta=scanner.nextInt();

            switch (scelta){
                case 1:
                    classe1.InsertUsers();
                    break;
                case 2:
                    change(classe1);
                    break;
                case 3:
                    classe1.GetUsrInfo();
                    break;
                case 4:
                    classe1.GetUsers();
                    break;
                case 0:
                    break;

                default:
                    System.out.println("inserisci un numero corrispondente alle funzioni!");
                    break;
            }
        }while (scelta!=0);
    }

    public static void change(Classroom classe){
        int scelta;
        do{
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
                    classe.setAge();            //TODO
                    break;
                case 3:
                    classe.setCognome();
                    break;
                default:
                    System.out.println("inserisci un numero corrispondente alle funzioni!");
                    break;

            }
        }while (scelta!=0);
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
}
