package com.example.contagicovidfx;

import com.example.contagicovidfx.Model.Covid;
import com.example.contagicovidfx.Model.DayLog;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MainController {

    private static final String path = "src/main/resources/dpc-covid19-ita-andamento-nazionale.csv";



    public static void main(String[] args) throws IOException, CsvException {

        retrieveData();
        CSVReader reader = new CSVReader(new FileReader(path));
        String[] content;
        int counter = 0;
        while ((content = reader.readNext())!=null) {
            if (counter!=0)
                insertDay(content);
            counter++;
        }
        System.out.println("Sono stati letti "+counter+" giorni.");

        for (DayLog log : Covid.getCovidLog())
            System.out.println(log.getData()+": "+log.getPositiviOggi()+ " positivi.");



    }

    public static void retrieveData() throws IOException {

        URL url = new URL("https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-andamento-nazionale/dpc-covid19-ita-andamento-nazionale.csv");
        System.out.println("Downloading from "+url);

        try {
            InputStream in = url.openStream();
            Files.copy(in, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception ex){
            System.out.println("Qualcosa non è andato a buon fine...");
            ex.printStackTrace();
        }


    }

    private static void insertDay(String[] data){
        String date = data[0];
        int ricoveratiConSintomi = Integer.parseInt(data[2]);
        int terapiaIntensivaTotali = Integer.parseInt(data[3]);
        int positiviCorrenti = Integer.parseInt(data[6]);
        int positiviOggi = Integer.parseInt(data[8]);
        int decedutiTotali = Integer.parseInt(data[10]);
        int tamponiOggi = Integer.parseInt(data[14]);
        DayLog log = new DayLog(date, ricoveratiConSintomi, terapiaIntensivaTotali, positiviCorrenti, positiviOggi, decedutiTotali, tamponiOggi);
        Covid.Add(log);

    }
}
