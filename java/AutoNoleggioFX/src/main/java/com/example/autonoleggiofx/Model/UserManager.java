package com.example.autonoleggiofx.Model;

import com.example.autonoleggiofx.Model.Admin;
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

    public static void ReadJson(){
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(userFilePath));
            JsonArray content = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement jsonObject : content)
                userList.add(gson.fromJson(jsonObject, Admin.class));
            System.out.println("Lettura delle utenze avvenuta con successo.");

            reader.close();

        } catch (IOException ex) {
            System.out.println("Lettura del file utenze non riuscita.");
        }
    }

    private static void WriteJson(){
        try {
            Gson gson = new Gson();
            String adminList = gson.toJson(userList);
            FileWriter fileWriter = new FileWriter(userFilePath);
            fileWriter.write(adminList);
            System.out.println("Scrittura delle utenze avvenuta con successo.");
        }
        catch (IOException ex){
            System.out.println("Scrittura del file utenze non riuscita.");
        }
    }

    public static Boolean Login(Admin userToLogin){
        String passwordToCheck = userToLogin.getPassword();
        String userNameToCheck = userToLogin.getUserName();
        for (Admin admin : userList) {
            if (admin.getPassword().equals(passwordToCheck) && admin.getUserName().equals(userNameToCheck))
                return true;
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
