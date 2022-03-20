package com.example.autonoleggiofx.Model;

import com.aspose.cells.FileFormatType;
import com.aspose.cells.LoadOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AutoManager {
    private static final List<Auto> autoDisponibiliList = new ArrayList<>();
    private static final List<Auto> autoNoleggiateList = new ArrayList<>();
    private static final Gson gson = new Gson();
    private static final String jsonDisponibiliFilePath = "src/main/resources/com/example/autonoleggiofx/Sources/AutoDisponibili.json";
    private static final String jsonNoleggiateFilePath = "src/main/resources/com/example/autonoleggiofx/Sources/AutoNoleggiate.json";
    private static final List<Auto.Produttore> choices = new ArrayList<>(){{add(Auto.Produttore.FIAT); add(Auto.Produttore.FERRARI); add(Auto.Produttore.LAMBORGHINI);}};

    public static void AddToNoleggiateList(Auto auto) throws IOException {
        //Aggiungo metodo per impostare la data, che ora non ho voglia
        auto.setNewDataNoleggio();
        autoDisponibiliList.remove(auto);
        autoNoleggiateList.add(auto);
        WriteJsonNoleggiati();      //aggiorno il file delle auto noleggiate
        WriteJsonDisponibili();     //aggiorno il file delle auto disponibili
    }

    public static float AddToDisponibiliList(Auto auto) throws IOException {

        //anche qui, aggiungere un metodo opportuno che ti calcola la data approssimata al secondo.
        auto.setDataUltimaRestituzione();
        float costo = CalculateBill(auto);        //calcolo quanto deve pagare per aver usato l'auto
        auto.increaseVolteNoleggiate();             //incremento il numero di noleggi di questa macchina
        auto.clearDataNoleggio();
        autoDisponibiliList.add(auto);
        autoNoleggiateList.remove(auto);
        WriteJsonNoleggiati();      //aggiorno il file delle auto noleggiate
        WriteJsonDisponibili();     //aggiorno il file delle auto disponibili
        return costo;

    }

    public static float CalculateBill(Auto auto){
        float costoSecondo = auto.getCostoGiornaliero() / 86400L;
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime fromDate = LocalDateTime.parse(auto.getDataUltimaRestituzione(), formatter);
        LocalDateTime toDate = LocalDateTime.parse(auto.getDataNoleggio(), formatter);
        long timeInSeconds = (fromDate.atZone(zoneId).toEpochSecond()-toDate.atZone(zoneId).toEpochSecond());
        auto.increaseSecondiInNoleggio(timeInSeconds);      //incremento i secondi di noleggio totali della macchina.

        return costoSecondo*timeInSeconds;
    }


    public static void InsertAuto(Auto auto) throws IOException {
        autoDisponibiliList.add(auto);
        WriteJsonDisponibili(); //scrive la lista aggiornata (un auto in piu)
    }

    public static void DeleteAuto(Auto auto) throws IOException {
        autoDisponibiliList.remove(auto);
        WriteJsonDisponibili(); //scrive la lista aggiornata (un auto in meno)
    }

    public static void EditAuto(int index, Auto.Produttore produttore, String modello, String targa, float costo) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Modifica dell'auto avvenuta con successo.");
        Auto autoToEdit = AutoManager.getAutoDisponibiliList().get(index);
        autoToEdit.setProduttore(produttore);
        autoToEdit.setModello(modello);
        autoToEdit.setCostoGiornaliero(costo);

        if (IsTargaUsable(targa) || targa.equals(autoToEdit.getTarga())) autoToEdit.setTarga(targa);
        else alert.setContentText("Targa inserita non disponibile. non è stata modificata.");
        WriteJsonDisponibili(); //aggiorno il file, ora con la macchina modificata
        alert.show();

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

    public static List<Auto> getAuto(){
        List<Auto> autoList = new ArrayList<>();
        autoList.addAll(autoDisponibiliList);
        autoList.addAll(autoNoleggiateList);
        return autoList;
    }

    public static List<Auto> getCarsByProduttore(Auto.Produttore produttore) {
        List<Auto> filteredList = new ArrayList<>();
        for (Auto auto : autoDisponibiliList)
            if (Objects.equals(auto.getProduttore(), produttore)) filteredList.add(auto);
        return filteredList;
    }

    public static Boolean IsTargaUsable(String targa){
        List<Auto> autoTotali = new ArrayList<>();
        autoTotali.addAll(autoNoleggiateList);
        autoTotali.addAll(autoDisponibiliList);

        for (Auto auto: autoTotali){
            if (auto.getTarga().equals(targa)) return false;
        }
        return true;
    }

    /*
     Metodo per scrivere sui rispettivi file la lista delle auto disponibili o noleggiate
      */
    public static void WriteJson(List<Auto> autoListToWrite, String filepath) throws IOException {
        String contentToWrite = gson.toJson(autoListToWrite);
        FileWriter fileWriter = new FileWriter(filepath);
        try {
            fileWriter.write(contentToWrite);
            fileWriter.close();


        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Scrittura del file non riuscita, riprovare.");
            alert.show();
        }
    }
    public static void WriteJsonDisponibili() throws IOException {
        WriteJson(autoDisponibiliList, jsonDisponibiliFilePath);
    }
    public static void WriteJsonNoleggiati() throws IOException {
        WriteJson(autoNoleggiateList, jsonNoleggiateFilePath);
    }

    private static void ReadJson(String filePath, List<Auto> autoList) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            JsonArray content = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement jsonObject : content)
                autoList.add(gson.fromJson(jsonObject, Auto.class));

            reader.close();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Lettura del file non riuscita, riprovare.");
            alert.show();
        }
    }
    public static void ReadJsonDisponibili() {
        ReadJson(jsonDisponibiliFilePath, autoDisponibiliList);
    }
    public static void ReadJsonNoleggiati() {
        ReadJson(jsonNoleggiateFilePath, autoNoleggiateList);
    }

    /*
    Serie di metodi per salvare in formato JSON una delle due liste (auto disponibili/auto noleggiate)
     */
    public static void SaveAsJSON(List<Auto> autoList, String filePath){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            WriteJson(autoList, filePath);
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", ".json"));
            File path = fileChooser.showSaveDialog(null);
            Files.copy(Path.of(filePath), Path.of(path.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

            alert.setContentText("File salvato con successo.");
            alert.show();
        }
        catch (IOException | NullPointerException ex){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Salvataggio del file non riuscito, riprovare.");
            alert.show();
        }

    }
    public static void SaveJsonDisponibili(){
        SaveAsJSON(autoDisponibiliList, jsonDisponibiliFilePath);
    }
    public static void SaveJsonNoleggiati(){
        SaveAsJSON(autoNoleggiateList, jsonNoleggiateFilePath);
    }


    /*
    Metodo che permette di salvare le auto disponibili in un file CSV.
    Il metodo di salvataggio del file CSV consiste nell'effettuare una conversione tra il file JSON (principale) e il CSV.
    Ho usato una guida trovata su internet. Non ci ho capito nulla, ma FUNZIONA.
     */
    public static void SaveAsCSV(List<Auto> autoList, String jsonFilePath){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", ".csv"));
            File csvFilePath = fileChooser.showSaveDialog(null);

            WriteJson(autoList, jsonFilePath);    //salva la lista delle auto disponibili in un file .json (per poi convertirlo in csv)
            JsonNode jsonNode = new ObjectMapper().readTree(new File(jsonFilePath));
            CsvSchema.Builder csvBuilder = CsvSchema.builder();
            JsonNode firstObject = jsonNode.elements().next();
            firstObject.fieldNames().forEachRemaining(csvBuilder::addColumn);
            CsvSchema csvSchema = csvBuilder.build().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.writerFor(JsonNode.class).with(csvSchema).writeValue(csvFilePath, jsonNode);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"File salvato con successo.\nVuoi anche creare un file .XLS?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) SaveAsXLSX(csvFilePath.getAbsolutePath());

        }
        catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Salvataggio del file non riuscito, riprovare.");
            alert.show();
        }
    }
    public static void SaveCsvDisponibili(){
        SaveAsCSV(autoDisponibiliList, jsonDisponibiliFilePath);
    }
    public static void SaveCsvNoleggiati(){
        SaveAsCSV(autoNoleggiateList, jsonNoleggiateFilePath);
    }

    public static void SaveAsXLSX(String csvFilePath){
        System.out.println("ciao");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "File salvato con successo.");
        try {

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX files", ".xlsx"));
            String XlsFilePath = fileChooser.showSaveDialog(null).getAbsolutePath();

            LoadOptions loadOptions = new LoadOptions(FileFormatType.CSV);
            Workbook workbook = new Workbook(csvFilePath, loadOptions);
            workbook.save(XlsFilePath, SaveFormat.XLSX);
        }
        catch (Exception ex){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Salvataggio del file non riuscito, riprovare.");
        }
        alert.show();
    }




}

