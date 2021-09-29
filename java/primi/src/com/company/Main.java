package com.company;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numeri = new ArrayList<Integer>();
        int numero,i;
        do {
            System.out.println("inserisci il numero (MAGGIORE DI 2 E PARI): ");
            numero=scanner.nextInt();
        }while (numero%2!=0);

        for (i=1;i<numero;i++){
            if (IsPrime(i)==true){
                numeri.add(i);
            }
        }
        System.out.println("numeri: "+numeri);
        Goldbatch(numeri,numero);
    }
    public static boolean IsPrime(int i){
        int y;
        for (y=1;y<=i/2;y++){
            if (i%y==0 && y!=1) {
                return false;
            }
        }
        return true;
    }
    public static void Goldbatch( ArrayList<Integer> numeri, int numero){
        int i,y;
        for (i=0;i<numeri.size();i++){
            for (y=numeri.size()-1;y>i;y--){
                if ((numeri.get(y)+numeri.get(i))==numero)
                    System.out.printf("la congettura e' verificata con i numeri %d e %d.\n",numeri.get(y),numeri.get(i));
            }
        }

    }
}
