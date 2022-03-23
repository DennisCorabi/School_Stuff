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

    /*
    Metodo per aggiungere un auto alla lista delle noleggiate: viene chiamato quando noleggio un auto
     */
    public static void AddToNoleggiateList(Auto auto) throws IOException {

        auto.setNewDataNoleggio();      //setto la data di noleggio
        autoDisponibiliList.remove(auto);   //rimuovo l'auto dalla lista dell auto disponibili
        autoNoleggiateList.add(auto);   //rimuovo l'auto dalla lista delle auto noleggiate

        WriteJsonNoleggiati();      //aggiorno il file delle auto noleggiate
        WriteJsonDisponibili();     //aggiorno il file delle auto disponibili
    }

    /*
    Metodo per aggiungere un auto alla lista delle dispnibili: viene chiamato quando restituisco un auto
     */
    public static float AddToDisponibiliList(Auto auto) throws IOException {

        //anche qui, aggiungere un metodo opportuno che ti calcola la data approssimata al secondo.
        auto.setDataUltimaRestituzione();
        float costo = CalculateBill(auto);        //calcolo quanto deve pagare per aver usato l'auto
        auto.increaseVolteNoleggiate();             //incremento il numero di noleggi di questa macchina
        auto.clearDataNoleggio();               //svuoto il parametro contenente la data di noleggio

        autoDisponibiliList.add(auto);
        autoNoleggiateList.remove(auto);
        WriteJsonNoleggiati();      //aggiorno il file delle auto noleggiate
        WriteJsonDisponibili();     //aggiorno il file delle auto disponibili
        return costo;

    }
    /*
    Metodo per calcolare il prezzo da pagare in base alla data di noleggio, di restituzione ed il prezzo/giornata dell'auto
     */
    public static float CalculateBill(Auto auto){
        float costoSecondo = auto.getCostoGiornaliero() / 86400L;           //costo al secondo

        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime fromDate = LocalDateTime.parse(auto.getDataUltimaRestituzione(), formatter);  //string --> localDate
        LocalDateTime toDate = LocalDateTime.parse(auto.getDataNoleggio(), formatter);      //string --> localDate

        //calcolo differenza in secondi tra la data di noleggio e quella di restituzione
        long timeInSeconds = (fromDate.atZone(zoneId).toEpochSecond()-toDate.atZone(zoneId).toEpochSecond());
        auto.increaseSecondiInNoleggio(timeInSeconds);      //incremento i secondi di noleggio totali della macchina.

        return costoSecondo*timeInSeconds;      //ritorno il prezzo complessivo del noleggio dell'auto
    }

    /*
    Metodo per inserire auto nella lista disponibili: viene chiamata solo nell'adminController
     */
    public static void InsertAuto(Auto auto) throws IOException {
        autoDisponibiliList.add(auto);
        WriteJsonDisponibili(); //scrive la lista aggiornata (un auto in piu)
    }

    /*
    Metodo per eleminare auto nella lista disponibili: viene chiamato solo nell' AdminController
     */
    public static void DeleteAuto(Auto auto) throws IOException {
        autoDisponibiliList.remove(auto);
        WriteJsonDisponibili(); //scrive la lista aggiornata (un auto in meno)
    }

    /*
    Metodo che permette di modificare i parametri di un auto tra quelle al momento disponibili
     */
    public static void EditAuto(int index, Auto.Produttore produttore, String modello, String targa, float costo) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Modifica dell'auto avvenuta con successo. Ricarica la pagina per visualizzare le modifiche.\"");
        Auto autoToEdit = AutoManager.getAutoDisponibiliList().get(index);
        autoToEdit.setProduttore(produttore);
        autoToEdit.setModello(modello);             //aggiorno i parametri
        autoToEdit.setCostoGiornaliero(costo);

        //se la targa nuova è già presa, non la cambio ed avviso l'utente
        if (IsTargaUsable(targa) || targa.equals(autoToEdit.getTarga())) autoToEdit.setTarga(targa);
        else alert.setContentText("Targa inserita non disponibile. non è stata modificata.");
        WriteJsonDisponibili(); //aggiorno il file, ora con la macchina modificata
        alert.show();

        //IMPORTANTE: NON SI AGGIORNA LA TABELLA, MENTRE LA LISTA ED IL FILE SI. NON SO PERCHE' FACCIA COSI', PER QUESTO DEVO CHIEDERE ALL'UTENTE DI USCIRE E RIENTRARE DALLA FINESTRA-
        //non riesco proprio a trovare una soluzione

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

    //Ritorna lista completa delle auto (Disponibili + noleggiate)
    public static List<Auto> getAuto(){
        List<Auto> autoList = new ArrayList<>();
        autoList.addAll(autoDisponibiliList);
        autoList.addAll(autoNoleggiateList);
        return autoList;
    }

    //Ritorna una lista contente auto di un solo produttore
    public static List<Auto> getCarsByProduttore(Auto.Produttore produttore) {
        List<Auto> filteredList = new ArrayList<>();
        for (Auto auto : autoDisponibiliList)
            if (Objects.equals(auto.getProduttore(), produttore)) filteredList.add(auto);
        return filteredList;
    }

    //Metodo per il controllo della targa: controlla che la targa sia utilizzabile
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

    //scrittura in formato Json della lista contenente le auto disponibili
    public static void WriteJsonDisponibili() throws IOException {
        WriteJson(autoDisponibiliList, jsonDisponibiliFilePath);
    }
    //scrittura in formato Json della lista contenente le auto noleggiate
    public static void WriteJsonNoleggiati() throws IOException {
        WriteJson(autoNoleggiateList, jsonNoleggiateFilePath);
    }

    //lettura in formato Json di una lista in un certo percorso
    private static void ReadJson(String filePath, List<Auto> autoList) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));       //lettura del file
            JsonArray content = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement jsonObject : content)  //conversione di ogni elemento Json del file in oggetto di classe Auto
                autoList.add(gson.fromJson(jsonObject, Auto.class));        //aggiunta oggtto nel vettore

            reader.close();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Lettura del file non riuscita, riprovare.");
            alert.show();
        }
    }
    //lettura in formato Json della lista contenente le auto disponibili
    public static void ReadJsonDisponibili() {
        ReadJson(jsonDisponibiliFilePath, autoDisponibiliList);
    }

    //lettura in formato Json della lista contenente le auto noleggiate
    public static void ReadJsonNoleggiati() {
        ReadJson(jsonNoleggiateFilePath, autoNoleggiateList);
    }

    //Metodo per salvare in formato Json la lista delle auto disponibili o noleggiate
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

    //Metodo per salvare in formato Json la lista delle auto disponibili
    public static void SaveJsonDisponibili(){
        SaveAsJSON(autoDisponibiliList, jsonDisponibiliFilePath);
    }
    //Metodo per salvare in formato Json la lista delle auto noleggiate
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

    //Metodo che permette di salvare in formato CSV la lista delle auto disponibili
    public static void SaveCsvDisponibili(){
        SaveAsCSV(autoDisponibiliList, jsonDisponibiliFilePath);
    }
    //Metodo che permette di salvare in formato CSV la lista delle auto noleggiate
    public static void SaveCsvNoleggiati(){
        SaveAsCSV(autoNoleggiateList, jsonNoleggiateFilePath);
    }

    /*
    Metodo che viene chiamato ogni qualvolta salvo un file in formato CSV.
    Il metodo dà la possibilità all'utente di salvare un file in formato .XLSX, così che le liste delle auto siano visibili anche in formato Excel .
     */
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

