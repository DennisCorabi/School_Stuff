package com.company;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        int scelta=0;
        Classroom classe1 = CreateClassroom();
        classe1.GetUsers();
        do{
            System.out.print("\n" +
                    "1: inserisci un alunno" +
                    "\n2: cambia nome di un alunno" +
                    "\n3: cambia eta' dell'alunno" +
                    "\n4: Cambia cognome di un alunno"+
                    "\n5: stampa informazioni di un alunno specifico" +
                    "\n6: stampa tutti gli alunni di una classe." +
                    "\n0: esci dal programma.");

            System.out.print("\n\nscelta: ");
            scelta=scanner.nextInt();

            switch (scelta){
                case 1:
                    classe1.InsertUsers();
                    break;
                case 2:
                    classe1.setName();
                    break;
                case 3:
                    classe1.setAge();
                    break;
                case 4:
                    classe1.setCognome();
                case 5:
                    classe1.GetUsrInfo();
                    break;
                case 6:
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
