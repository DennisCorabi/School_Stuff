package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList num = new ArrayList();

    public static void main(String[] args){
        int numero,cont=0,returnint;
        do{
            System.out.printf("inserisci il numero %d: ",cont+1);
            numero=scanner.nextInt();
            if (numero!=0) {
                num.add(numero);
                cont++;
            }
        }while (numero!=0);

        do{
            System.out.println("\ncosa vuoi fare adesso?\n" +
            "1: Calcolo del numero di coppie di numeri consecutivi uguali.\n" +
            "2: calcolo del numero di coppie in cui il secondo numero è divisore del primo (non uguali).\n" +
            "3: visualizzazione di un messaggio che dica quale tipo di coppie tra le due tipologie indicate è presentein numero maggiore." +
            "4: Uscita dal programma.");
            returnint=menu();

        }while (returnint!=0);

    }
    public static int menu(){
        int scelta;
        scelta=scanner.nextInt();
        switch (scelta){
            case 1:
                System.out.println("Numero coppie di numeri consecutivi uguali: "+CoppieUguali());
                return 1;
            case 2:
                System.out.println("Numero coppie di numeri divisori: "+Divisore());
                return 2;
            case 3:
                GetCoppiaMag();
                return 3;
            case 4:
                return 0;
            default:
                System.out.println("inserisci un numero compreso da 1 a 3!");
                return 4;
        }
    }
    public static int CoppieUguali(){
        int i,cont=0;
        for (i=0;i<num.size()-1;i++){
            if (num.get(i)==num.get(i+1))
                cont++;
        }
        return cont;
    }
    public static int Divisore() {
        int i;
        int cont = 0;
        for (i = 1; i < num.size() - 1; i++) {
            if ((int) num.get(i) % (int) num.get(i - 1) == 0)
                cont++;
        }
        return cont;
    }

    public static void GetCoppiaMag(){
        int cont1,cont2;
        cont1=CoppieUguali();
        cont2=Divisore();
        if (cont1>cont2)
            System.out.println("la prima funzione ha piu' coppie ("+cont1+")");
        else
            System.out.println("la seconda funzione ha piu' coppie ("+cont2+")");

    }
}