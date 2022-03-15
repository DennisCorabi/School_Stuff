package com.example.autonoleggiofx.Model;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.*;

public class AutoManager {
    private static Integer counter = 0;         //numero di auto che sono fuori dall'autonoleggio
    private static final List<Auto> autoList = new ArrayList<>();
    private static Gson gson = new Gson();
    private static String filePath = "src/main/resources/com/example/autonoleggiofx/auto.json";

    public static void Add(Auto auto) {
        for (Auto auto1 : autoList)
            if (Objects.equals(auto1.getTarga(), auto.getTarga())) {
                return;
            }
        autoList.add(auto);
    }

    public static void Delete(Auto auto) {
        autoList.remove(auto);
    }

    public static Integer getCounter() {
        return counter;
    }

    public static List<Auto> getAutoList() {
        return autoList;
    }


    public static List<Auto> getCarsByModel(String value) {
        List<Auto> filteredList = new ArrayList<>();
        for (Auto auto : autoList)
            if (Objects.equals(auto.getProduttore(), value)) filteredList.add(auto);
        return filteredList;
    }

    public static void WriteJson() throws IOException {
        String contentToWrite = gson.toJson(autoList);
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file);
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

