package com.example.covidfx.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.skin.SliderSkin;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class Covid {
    public static List<DayLog> CovidLog = new Vector<>();
    public static final List<String> Choises = new ArrayList<>() {{add("Ricoverati"); add("Terapia Intensiva"); add("Positivi"); add("Morti"); add("Tamponi"); }};


    public static List<DayLog> getCovidLog() {
        return CovidLog;
    }
    public static void Add(DayLog log){
        CovidLog.add(log);
    }
    public static String getLatestDate(){
        return CovidLog.get(CovidLog.size()-1).getData();
    }


    /*
    Metodo che ricerca ed eventualmente ritorna il resoconto di una giornata specifica, individuata dalla sua data
     */
    public static DayLog searchForDay(String date){
        for (DayLog log: CovidLog)
            if (Objects.equals(log.getData(), date))
                return log;

        return null;
    }

    public static List<DayLog> searchForDays(String[] dates){
        List<DayLog> logs = new Vector<>();
        for (String date: dates){
            DayLog log = searchForDay(date);
            if (log!=null)
                logs.add(log);

        }
        return logs;
    }

    public static String getLatestUpdate() {
        DayLog latestUpdate = CovidLog.get(CovidLog.size()-1);
        DayLog theDayBefore = CovidLog.get(CovidLog.size()-2);

        String terapiaIntensivaDifference = String.valueOf(latestUpdate.getTerapiaIntensivaTotali()-theDayBefore.getTerapiaIntensivaTotali());
        String mortiDifference = String.valueOf(latestUpdate.getDecedutiTotali()-theDayBefore.getDecedutiTotali());
        String tamponiDifference = String.valueOf(latestUpdate.getTamponiTotali()-theDayBefore.getTamponiTotali());
        String ricoveratiDifference = String.valueOf(latestUpdate.getRicoveratiConSintomi()-theDayBefore.getRicoveratiConSintomi());


        return "Data: " +  latestUpdate.getData()+
                "\nRicoverati: " + ricoveratiDifference +
                "\nTerapia intensiva: " + terapiaIntensivaDifference +
                "\nPositivi : " + latestUpdate.getPositiviOggi() +
                "\nPositivi totali: " + latestUpdate.getPositiviCorrenti() +
                "\nMorti: " + mortiDifference +
                "\nTamponi: " + tamponiDifference;
    }


}
