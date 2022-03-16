package com.example.autonoleggiofx.Model;

import com.google.gson.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AutoManager {
    private static final List<Auto> autoList = new ArrayList<>();
    private static final Gson gson = new Gson();
    private static final String filePath = "src/main/resources/com/example/autonoleggiofx/auto.json";
    private static final List<Auto.Produttore> choices = new ArrayList<>(){{add(Auto.Produttore.FIAT); add(Auto.Produttore.FERRARI); add(Auto.Produttore.LAMBORGHINI);}};

    public static void Add(Auto auto) {
        autoList.add(auto);
    }

    public static void DeleteByTarga(String targa) {
        autoList.removeIf(auto -> auto.getTarga().equals(targa));
    }

    public static List<Auto> getAutoList() {
        return autoList;
    }

    public static List<Auto.Produttore> getChoices() {
        return choices;
    }

    public static List<Auto> getCarsByProduttore(Auto.Produttore produttore) {
        List<Auto> filteredList = new ArrayList<>();
        for (Auto auto : autoList)
            if (Objects.equals(auto.getProduttore(), produttore)) filteredList.add(auto);
        return filteredList;
    }

    public static Boolean IsTargaUsable(String targa){
        for (Auto auto: autoList){
            if (auto.getTarga().equals(targa)) return false;
        }
        return true;
    }

    public static void WriteJson() throws IOException {
        String contentToWrite = gson.toJson(autoList);
        FileWriter fileWriter = new FileWriter(filePath);
        try {
            fileWriter.write(contentToWrite);
            fileWriter.close();
            System.out.println("Scrittura del file avvenuta con successo.");
        } catch (Exception ex) {
            System.out.println("Scrittura del file non riuscita.");
        }
    }

    /*
    Metodo per leggere da file Json e riempire la lista con tutte le auto
     */
    public static void ReadJson() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            JsonArray content = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement jsonObject : content) {
                Add(gson.fromJson(jsonObject, Auto.class));
            }
        } catch (IOException ex) {
            System.out.println("Lettura del file non riuscita.");
        }


    }

    public static void saveAsJSON(){

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", ".json"));
            File path = fileChooser.showSaveDialog(null);
            System.out.println("From: " + filePath + "\tTo: " + path.getAbsolutePath() + ".");
            Files.copy(Path.of(filePath), Path.of(path.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException | NullPointerException ex){
            System.out.println("Il salvataggio del file non è andato a buon fine.");
        }

    }

    public static void saveAsCSV() {
    }
}

