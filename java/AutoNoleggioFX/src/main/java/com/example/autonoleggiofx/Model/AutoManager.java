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
import java.util.*;

public class AutoManager {
    private static final List<Auto> autoDisponibiliList = new ArrayList<>();
    private static final List<Auto> autoNoleggiateList = new ArrayList<>();
    private static final Gson gson = new Gson();
    private static final String JsonDisponibiliFilePath = "src/main/resources/com/example/autonoleggiofx/Sources/AutoDisponibili.json";
    private static final String JsonNoleggiateFilePath = "src/main/resources/com/example/autonoleggiofx/Sources/AutoNoleggiate.json";
    private static final List<Auto.Produttore> choices = new ArrayList<>(){{add(Auto.Produttore.FIAT); add(Auto.Produttore.FERRARI); add(Auto.Produttore.LAMBORGHINI);}};

    public static void AddToNoleggiateList(Auto auto) throws IOException {
        //Aggiungo metodo per impostare la data, che ora non ho voglia
        auto.setDataNoleggio("17/03/2022");
        autoDisponibiliList.remove(auto);
        autoNoleggiateList.add(auto);
        WriteJsonNoleggiati();      //aggiorno il file delle auto noleggiate
        WriteJsonDisponibili();     //aggiorno il file delle auto disponibili
    }

    public static void AddToDisponibiliList(Auto auto) throws IOException {
        autoDisponibiliList.add(auto);
        autoNoleggiateList.remove(auto);
        WriteJsonNoleggiati();      //aggiorno il file delle auto noleggiate
        WriteJsonDisponibili();     //aggiorno il file delle auto disponibili
    }

    public static void InsertAuto(Auto auto) throws IOException {
        autoDisponibiliList.add(auto);
        AutoManager.WriteJsonDisponibili(); //scrive la lista aggiornata (un auto in piu)
    }

    public static void DeleteAuto(Auto auto) throws IOException {
        autoDisponibiliList.remove(auto);
        WriteJsonDisponibili(); //scrive la lista aggiornata (un auto in meno)
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

    public static Boolean IsTargaUsable(String targa){
        List<Auto> autoTotali = autoNoleggiateList;
        autoTotali.addAll(autoDisponibiliList);

        for (Auto auto: autoTotali){
            if (auto.getTarga().equals(targa)) return false;
        }
        return true;
    }

    public static void WriteJson(List<Auto> autoListToWrite, String filepath) throws IOException {
        String contentToWrite = gson.toJson(autoListToWrite);
        FileWriter fileWriter = new FileWriter(filepath);
        try {
            fileWriter.write(contentToWrite);
            fileWriter.close();
            System.out.println("Scrittura del file avvenuta con successo.");
        } catch (Exception ex) {
            System.out.println("Scrittura del file non riuscita.");
        }
    }

    /*
    Metodo per scrivere sui rispettivi file la lista delle auto disponibili o noleggiate
     */
    public static void WriteJsonDisponibili() throws IOException {
        WriteJson(autoDisponibiliList, JsonDisponibiliFilePath);
    }
    public static void WriteJsonNoleggiati() throws IOException {
        WriteJson(autoNoleggiateList, JsonNoleggiateFilePath);
    }

    private static void ReadJson(String jsonNoleggiateFilePath, List<Auto> autoList) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(jsonNoleggiateFilePath));
            JsonArray content = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement jsonObject : content) {
                autoList.add(gson.fromJson(jsonObject, Auto.class));
            }
            System.out.println("Lettura del file avvenuta con successo.");
        } catch (IOException ex) {
            System.out.println("Lettura del file non riuscita.");
        }
    }

    /*
    Metodo per leggere da file Json e riempire la lista con tutte le auto disponibli o noleggiate
     */
    public static void ReadJsonDisponibili() {
        ReadJson(JsonDisponibiliFilePath, autoDisponibiliList);
    }
    public static void ReadJsonNoleggiati() {
        ReadJson(JsonNoleggiateFilePath, autoNoleggiateList);
    }

    public static void saveAsJSON(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            WriteJson(autoDisponibiliList, JsonDisponibiliFilePath);
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", ".json"));
            File path = fileChooser.showSaveDialog(null);
            Files.copy(Path.of(JsonDisponibiliFilePath), Path.of(path.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

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

            WriteJson(autoDisponibiliList, JsonDisponibiliFilePath);    //salva la lista delle auto disponibili in un file .json (per poi convertirlo in csv)
            JsonNode jsonNode = new ObjectMapper().readTree(new File(JsonDisponibiliFilePath));
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

