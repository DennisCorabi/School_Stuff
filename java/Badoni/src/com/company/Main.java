package com.company;

import com.company.person.Student;

public class Main {

    public static void main(String[] args) {
        Student studente1 = new Student(17, "dennis", "4ai");
        studente1.setClasse(4,'a',"Informatica");
        System.out.println("matricola studente: "+studente1.getMatricola());
        System.out.printf("Classe dello studente '%s': %s",studente1.getNome(),studente1.getClasse());
    }
}
