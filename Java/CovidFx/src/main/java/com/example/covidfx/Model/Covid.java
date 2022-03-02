package com.example.covidfx.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class Covid {
    public static List<DayLog> CovidLog = new Vector<>();

    public static List<DayLog> getCovidLog() {
        return CovidLog;
    }

    public static void Add(DayLog log){
        CovidLog.add(log);
    }

    public static String getLatestDate(){
        return CovidLog.get(CovidLog.size()-1).getData();
    }

    public static DayLog searchForDay(String date){
        for (DayLog log: CovidLog)
            if (Objects.equals(log.getData(), date))
                return log;

        return null;
    }


}
