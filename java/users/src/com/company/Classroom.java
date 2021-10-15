package com.company;

import java.util.Scanner;
import java.util.Vector;
public class Classroom {
    int grandezza;
    String nome;
    public Vector<User> classe = new Vector<>();
    static Scanner scanner = new Scanner(System.in);
    //STATICO: VARIABILE E/O OGGETTO CONDIVISO DA TUTTI GLI OGGETTI DI UNA CLASSE, REMEMBER.

    //costrutto iniziale
    public Classroom(String nome,int grandezza){

        this.grandezza = grandezza;
        this.nome = nome;
    }
    //Funzione che inserisce x alunni in una specifica classe
    public void InsertUsers() {
        int i,eta,users;
        String nome,cognome;
        System.out.print("inserisci il numero di alunni da inserire: ");
        users=scanner.nextInt();
        /*
           se somma di alunni da inseirire + alunni già inseriti è maggiore della capienza della classe,
           allora aggiungo alunni fino a raggiunge la massima capienza della classe.
           Se invece ho già raggiunto la massima capienza della classe, allora non faccio più inserire alunni all'utente.
         */
        if (users+this.classe.size()>this.grandezza) {
            System.out.println("\ninserimento non riuscito, le possibili cause sono le seguenti:\n " +
                    "1) il numero di utenti da inserire e' maggiore della capienza della classe." +
                    "\n\tverranno inseriti gli alunni fino a raggiungimento capacità massima della classe." +
                    "\n2) la classe ha raggiunto la massima capienza." +
                    "\n\tnon verranno inseriti nuovi alunni.\n");
            users=this.grandezza-this.classe.size();
        }
            //Inserisco X alunni (in base all'esito della condizione precedente.
        for (i = 0; i <users; i++) {
            System.out.printf("Inserisci il nome dell'alunno di registro numero %d: ", this.classe.size()+1);
            nome = scanner.next();
            System.out.printf("Inserisci il cognome dell'alunno di registro numero %s: ", this.classe.size()+1);
            cognome = scanner.next();
            System.out.printf("Inserisci l'eta' dell'alunno di registro numero %d: ", this.classe.size()+1);
            eta = scanner.nextInt();
            classe.add(new User(nome, cognome, eta));
            System.out.println();
        }
    }
    //funzione che stampa tutte le info di un'alunno.
    public void GetUsrInfo(){
        String nome,cognome;
        System.out.print("inserisci il nome dell'alunno da cercare: ");         //TODO
        nome = scanner.nextLine();

        System.out.print("inserisci il cognome dell'alunno da cercare: ");
        cognome = scanner.nextLine();

        for(User obj: classe)
            /*
            Se trova un alunno con cognome e nome pari a quelli inseriti allora lo stampo.
            In caso contrario, stampo un messaggio di errore.
             */
            if (obj.name.equals(nome) && obj.cognome.equals(cognome)) {
                System.out.printf("Nome: %s\nCognome: %s\nEta': %d\n", obj.name, obj.cognome, obj.eta);
                break;
            }
        System.out.printf("non sono stati trovati alunni con nome: %s e cognome: %s.\n",nome,cognome);
    }

    public void GetUsers(){
        for (User obj: this.classe){
            System.out.printf("Nome: %s\tCognome: %s\tEta': %d\n",obj.name,obj.cognome, obj.eta);
        }
        System.out.print("\n");
    }
    private int GetIndex(){
        int index;
        do{
            System.out.print("inserisci il numero di registro dell'alunno: ");
            index = scanner.nextInt();
        }while(index>this.classe.size());

        return index;


    }
    public void setName(){
        System.out.print("inserisci il nome da cambiare: ");
        classe.get(GetIndex()-1).setNome(scanner.next());
    }
    public void setAge(){
        System.out.print("inserisci l'eta' da cambiare: ");
        classe.get(GetIndex()-1).setEta(scanner.nextInt());
    }
    public void setCognome(){
        System.out.print("inserisci il cognome da cambiare: ");
        classe.get(GetIndex()-1).setCognome(scanner.next());
    }
}