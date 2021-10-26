package com.company;

import java.awt.*;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        Menu scuola = new Menu(new Scuola());
        scuola.first();

    }
}
