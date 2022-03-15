package com.example.autonoleggiofx.Model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AutoManager {
    private static Integer counter = 0;         //numero di auto che sono fuori dall'autonoleggio
    private static final List<Auto> autoList = new ArrayList<>();
    private static Gson gson = new Gson();
    private static String filePath = "src/main/resources/com/example/autonoleggiofx/auto.json";

    public static void Add(Auto auto){
        for (Auto auto1: autoList)
            if (Objects.equals(auto1.getTarga(), auto.getTarga())) {
                return;
            }
        autoList.add(auto);
    }

    public static void Delete(Auto auto){
        autoList.remove(auto);
    }

    public static Integer getCounter() {
        return counter;
    }

    public static List<Auto> getAutoList() {
        return autoList;
    }

    public static void saveAsJSON() throws IOException {
        readJson();
    }

    public static void saveAsCSV(){
    }

    public static List<Auto> getCarsByModel(String value){
        List<Auto> filteredList = new ArrayList<>();
        for (Auto auto: autoList)
            if (Objects.equals(auto.getProduttore(),value)) filteredList.add(auto);
        return filteredList;
    }

    public static void readJson() throws IOException {
        String json = gson.toJson(autoList);
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(json);

        System.out.println(json);
    }
}
