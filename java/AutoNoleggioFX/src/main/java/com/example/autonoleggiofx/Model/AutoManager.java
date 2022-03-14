package com.example.autonoleggiofx.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class AutoManager {
    private static final List<Auto> autoList = new ArrayList<>();


    public static void Add(Auto auto){
        for (Auto auto1: autoList)
            if (Objects.equals(auto1.getTarga(), auto.getTarga())) {
                return;
            }
        autoList.add(auto);
    }

    public static List<Auto> getAutoList() {
        return autoList;
    }

    public static void saveAsJSON(){
    }

    public static void saveAsCSV(){
    }

    public static List<Auto> getCarsByModel(String value){
        List<Auto> filteredList = new ArrayList<>();
        autoList.forEach(System.out::println);
        for (Auto auto: autoList)
            if (Objects.equals(auto.getMarca(),value)) filteredList.add(auto);
        return filteredList;
    }
}