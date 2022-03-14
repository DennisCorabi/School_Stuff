package Model;

import java.util.Vector;

public class Covid {
    public static Vector<DayLog> CovidLog = new Vector<>();


    public static Vector<DayLog> getCovidLog() {
        return CovidLog;
    }

    public static void Add(DayLog log){
        CovidLog.add(log);
    }


}
