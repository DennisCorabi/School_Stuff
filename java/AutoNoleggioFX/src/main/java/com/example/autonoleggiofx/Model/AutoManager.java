package com.example.autonoleggiofx.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.*;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class AutoManager {
    private static final List<Auto> autoDisponibiliList = new ArrayList<>();
    private static final List<Auto> autoNoleggiateList = new ArrayList<>();
    private static final Gson gson = new Gson();
    private static final String JsonFilePath = "src/main/resources/com/example/autonoleggiofx/auto.json";
    private static final List<Auto.Produttore> choices = new ArrayList<>(){{add(Auto.Produttore.FIAT); add(Auto.Produttore.FERRARI); add(Auto.Produttore.LAMBORGHINI);}};

    public static void AddToNoleggiateList(Auto auto){
        //Aggiungo metodo per impostare la data, che ora non ho voglia
        auto.setDataNoleggio("17/03/2022");
        autoDisponibiliList.remove(auto);
        autoNoleggiateList.add(auto);
    }

    public static void InsertAuto(Auto auto) {
        autoDisponibiliList.add(auto);
    }

    public static void DeleteAuto(Auto auto) {
        autoDisponibiliList.remove(auto);
    }

    public static List<Auto> getAutoNoleggiateList() {
        return autoNoleggiateList;
    }


    public static List<Auto> getAutoDisponibiliList() {
        return autoDisponibiliList;
    }

    public static List<Auto.Produttore> getChoices() {
        return choices;
    }

    public static List<Auto> getCarsByProduttore(Auto.Produttore produttore) {
        List<Auto> filteredList = new ArrayList<>();
        for (Auto auto : autoDisponibiliList)
            if (Objects.equals(auto.getProduttore(), produttore)) filteredList.add(auto);
        return filteredList;
    }

    //TODO: AGGIUNGERE CONTROLLO DELLA TARGA ANCHE PER LE AUTO NOLEGGIATE
    public static Boolean IsTargaUsable(String targa){
        for (Auto auto: autoDisponibiliList){
            if (auto.getTarga().equals(targa)) return false;
        }
        return true;
    }

    public static void WriteJson() throws IOException {
        String contentToWrite = gson.toJson(autoDisponibiliList);
        FileWriter fileWriter = new FileWriter(JsonFilePath);
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
            Reader reader = Files.newBufferedReader(Paths.get(JsonFilePath));
            JsonArray content = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement jsonObject : content) {
                InsertAuto(gson.fromJson(jsonObject, Auto.class));
            }
        } catch (IOException ex) {
            System.out.println("Lettura del file non riuscita.");
        }
    }

    public static void saveAsJSON(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            WriteJson();
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", ".json"));
            File path = fileChooser.showSaveDialog(null);
            Files.copy(Path.of(JsonFilePath), Path.of(path.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

            alert.setContentText("File salvato con successo.");
            alert.show();
        }
        catch (IOException | NullPointerException ex){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Salvataggio del file non riuscito, riprovare.");
            alert.show();
        }

    }

    /*
    Metodo che permette di salvare le auto disponibili in un file CSV.
    Il metodo di salvataggio del file CSV consiste nell'effettuare una conversione tra il file JSON (principale) e il CSV.
    Ho usato una guida trovata su internet, perciò non ci ho capito nulla, però FUNZIONA.
     */
    public static void SaveAsCSV(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("File salvato con successo.");
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", ".csv"));
            File CsvFilePath = fileChooser.showSaveDialog(null);

            WriteJson();    //salva la lista delle auto disponibili in un file .json (per poi convertirlo in csv)
            JsonNode jsonNode = new ObjectMapper().readTree(new File(JsonFilePath));
            CsvSchema.Builder csvBuilder = CsvSchema.builder();
            JsonNode firstObject = jsonNode.elements().next();
            firstObject.fieldNames().forEachRemaining(csvBuilder::addColumn);
            CsvSchema csvSchema = csvBuilder.build().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.writerFor(JsonNode.class).with(csvSchema).writeValue(CsvFilePath, jsonNode);

            alert.show();

        }
        catch (IOException ex){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Salvataggio del file non riuscito, riprovare.");
            alert.show();
        }
    }
}

