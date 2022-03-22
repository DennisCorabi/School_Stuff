package com.example.autonoleggiofx.Model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final List<Admin> userList = new ArrayList<>();
    private static final String userFilePath =  "src/main/resources/com/example/autonoleggiofx/Sources/Amministratori.json";
    private static final String ADMIN_KEY = "Admin";

    /*
    Metodo che legge il file contenente le utenze
     */
    public static void ReadJson(){
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(userFilePath));
            JsonArray content = JsonParser.parseReader(reader).getAsJsonArray();        //lettura file
            for (JsonElement jsonObject : content)      //conversione dei singoli elementi dell'array Json in oggetti
                userList.add(gson.fromJson(jsonObject, Admin.class));   //inserimento degli oggetti appena creati
            System.out.println("Lettura delle utenze avvenuta con successo.");
            reader.close();

        } catch (IOException ex) {
            System.out.println("Lettura del file utenze non riuscita.");
        }
    }

    /*
    Metodo che scrive il file destinato a contenere le utenze
     */
    private static void WriteJson(){
        try {
            Gson gson = new Gson();
            String adminList = gson.toJson(userList);
            FileWriter fileWriter = new FileWriter(userFilePath);
            fileWriter.write(adminList);        //scrittura file
            fileWriter.close();
        }
        catch (IOException ex){
            System.out.println("Scrittura del file utenze non riuscita.");
        }
    }

    /*
    Metodo che controlla che l'utente inserito nel form di login sia contenuto nel file
     */
    public static Boolean Login(Admin userToLogin){
        String passwordToCheck = userToLogin.getPassword();
        String userNameToCheck = userToLogin.getUserName();
        for (Admin admin : userList) {      //controllo
            if (admin.getPassword().equals(passwordToCheck) && admin.getUserName().equals(userNameToCheck))
                return true;        //ritorna vero se ha trovato un utente con le rispettive credenziali
        }
        return false;   //ritorna falso se l'utente inserito non è associato a nessun utente nella lista
    }

    /*
    Metodo che, dato un userName, visualizza la sua password
     */
    public static String RecuperaPassword(String username){
        for (Admin admin : userList) {      //controlla che l'username inserito nel form sia associato ad un utente nella lista
            if (admin.getUserName().equals(username))
                return admin.getPassword();     //ritorna password
        }
        return null;
    }

    /*
    Metodo che permette di modificare la password di un utente
     */
    public static Boolean ModificaPassword(Admin adminToReplace, String newPassword){
        for (Admin admin: userList){        //controlla che l'utente inserito (password + username) sia associato ad un utente
            if (admin.getUserName().equals(adminToReplace.getUserName()) && admin.getPassword().equals(adminToReplace.getPassword())) {
                admin.setPassword(newPassword);     //setto la nuova password
                WriteJson();    //aggiorno il file
                return true;
            }
        }
        return false;
    }

    /*
    Metodo che permette di modificare il nome utente di un utente.
     */
    public static Boolean ModificaUsername(Admin adminToReplace, String newUsername){
        userList.forEach(System.out::println);
        for (Admin admin: userList){        //controlla che l'utente inserito (password + username) sia associato ad un utente
            if (admin.getUserName().equals(adminToReplace.getUserName()) && admin.getPassword().equals(adminToReplace.getPassword())) {
                admin.setUserName(newUsername); //  setto il nuovo nome utente FIXME: 21/03/2022
                WriteJson();        //aggiorno il file
                return true;
            }
        }
        return false;
    }

    public static String getAdminKey() {
        return ADMIN_KEY;
    }

    public static void AddUser(Admin admin){
        userList.add(admin);
        WriteJson();
    }

    public static List<Admin> getUserList() {
        return userList;
    }
}
