package com.example.covidfx;

import com.example.covidfx.Model.Covid;
import com.example.covidfx.Model.DayLog;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MainController {

    @FXML
    public TableView<DayLog> table;

    public TableColumn<DayLog, String> DataColumn;
    public TableColumn<DayLog, Integer> RicoveratiColumn;
    public TableColumn<DayLog, Integer> TerapiaIntensivaColumn;
    public TableColumn<DayLog, Integer> PositiviTotaliColumn;
    public TableColumn<DayLog, Integer> PositiviNuoviColumn;
    public TableColumn<DayLog, Integer> MortiTotaliColumn;
    public TableColumn<DayLog, Integer> TamponiTotaliColumn;

    @FXML
    public TextField SearchForDay;
    public Label LatestUpdate;
    public TextField Difference1;
    public TextField Difference2;
    public ChoiceBox<String> DifferenceType;
    public Label DifferenceLabel;
    public TextArea LatestUpdateTextArea;

    @FXML
    public Button UpdateTableButton;

    private static final String filePath = "src/main/resources/dpc-covid19-ita-andamento-nazionale.csv";            //path del file da cui si prelevano i dati
    private static final String internetFilePath = "https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-andamento-nazionale/dpc-covid19-ita-andamento-nazionale.csv";        //url da dove si preleva il file
    private static final ObservableList<String> choises = FXCollections.observableList(Covid.Choises);  //serve per il menu a tendina
    private static int counter = 0; //contiene il totale dei resoconti processati



    //Metodo che viene chiamato solamente quando viene aperta la finestra
    @FXML
    public void initialize() throws IOException, CsvException {
        InitalizeTable();
        insertData();
        DifferenceType.setItems(choises);       //aggiungo le possibili opzioni al menu a tendina (sfortunatamente le scelte sono hardcoded, ma non ho trovato altra via)
    }

    /*
    Metodo che viene chiamato solamente quando viene aperta la finestra.
    Mappa le singole colonne con i parametri dentro la classe 'DayLog'.
     */
    private void InitalizeTable() {
        DataColumn.setCellValueFactory(new PropertyValueFactory<>("Data"));
        RicoveratiColumn.setCellValueFactory(new PropertyValueFactory<>("RicoveratiConSintomi"));
        TerapiaIntensivaColumn.setCellValueFactory(new PropertyValueFactory<>("TerapiaIntensivaTotali"));
        PositiviTotaliColumn.setCellValueFactory(new PropertyValueFactory<>("PositiviCorrenti"));
        PositiviNuoviColumn.setCellValueFactory(new PropertyValueFactory<>("PositiviOggi"));
        MortiTotaliColumn.setCellValueFactory(new PropertyValueFactory<>("DecedutiTotali"));
        TamponiTotaliColumn.setCellValueFactory(new PropertyValueFactory<>("TamponiTotali"));

    }


    /*
    Metodo che mi permette di scaricare, leggere e inserire dei dati nella tabella.
    Le singole azioni sono delegate ad altri metodi (per favorire la riusabilità del codice e una maggiore comprensione)
     */
    public void insertData() throws IOException, CsvException {

        UpdateTableButton.setDisable(true);     //disattivo il bottone fino a che non ho finito di fare la routine
        retrieveData();

        if (!table.getItems().isEmpty()) {  //se la tabella non è vuota, prima inserire i dati cancello tutto il suo contenuto.
            table.getItems().clear();
        }
        readData();
        System.out.println("Finished reading data from file at \"" + filePath + "\"\n");

        ObservableList<DayLog> dayLogs = FXCollections.observableList(Covid.getCovidLog()); //la tabella accetta solo questo formato
        table.setItems(dayLogs);
        LatestUpdate.setText("Ultimo aggiornamento: " + Covid.getLatestDate());
        LatestUpdateTextArea.setText("Ultimo aggiornamento: \n"+Covid.getLatestUpdate());
        UpdateTableButton.setDisable(false);

    }

    /*
    Metodo che mi permette di scaricare in automatico il resoconto complessivo a livello nazionale dell'andamento del Covid.
    Dopo averlo scaricato, lo salva nel percorso specificato.
    Volendo, basta cambiare l'URL e questo metodo diventa riutilizzabile per scaricare qualsiasi file da internet, compreso l'HTML delle pagine stesse.
     */
    public void retrieveData() throws IOException {

        URL url = new URL(internetFilePath);
        System.out.println("Downloading from \"" + internetFilePath + "\"");
        try {
            InputStream in = url.openStream();      //apre un collegamento a internet e scarica il file.
            Files.copy(in, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);   //copia il file nel percorso specificato.
            System.out.println("Finished fetching data from \"" + internetFilePath + "\"");

        } catch (IOException ex) {
            System.out.println("Could not fetch data from \"" + internetFilePath + "\"");
        }
    }

    /*
    Metodo Legge il file individuato dal suo percorso (filepath) e ne legge il contenuto. Per ogni riga del file crea un oggetto che rappresenta il resoconto della singola giornata.
    Infine, aggiunge il resoconto a una lista contenente tutti i resoconti a partire dal 24 febbraio 2020.
     */
    public void readData() throws IOException, CsvException {

        CSVReader reader = new CSVReader(new FileReader(filePath));        //uso la libreria OpenCSV per aprire i file con estensione .csv
        String[] content;
        counter=0;
        while ((content = reader.readNext()) != null) {       //per ogni riga del file, chiamo la funzione.
            if (counter != 0)
                insertDay(content);
            counter++;
        }
        reader.close();
    }

    /*
    Metodo che, data una serie di parametri, crea un oggetto che rappresenta il resoconto della giornata
     */
    private void insertDay(String[] data) {
        String date = data[0].split("T")[0];    //codice bruttissimo ma che funziona: preleva dalla data solo l'anno, il mese e il giorno
        int ricoveratiConSintomi = Integer.parseInt(data[2]);
        int terapiaIntensivaTotali = Integer.parseInt(data[3]);
        int positiviCorrenti = Integer.parseInt(data[6]);
        int positiviOggi = Integer.parseInt(data[8]);
        int decedutiTotali = Integer.parseInt(data[10]);
        int tamponiOggi = Integer.parseInt(data[14]);
        DayLog log = new DayLog(date, ricoveratiConSintomi, terapiaIntensivaTotali, positiviCorrenti, positiviOggi, decedutiTotali, tamponiOggi);
        Covid.Add(log); //aggiunge alla lista completa dei resoconti.
    }

    /*
    Metodo per calcolare la differenza tra un parametro (scelto a piacere) di due resoconti giornalieri scelti inserendo la data corrispondente.
     */
    public void calculateDifference(){
        DayLog date1 = Covid.searchForDay(Difference1.getText());
        DayLog date2 = Covid.searchForDay(Difference2.getText());
        int difference= 0;

        if (date1!=null && date2!=null && DifferenceType.getValue()!=null) {

            //in base alla scelta che si è fatto, effettua una differenza.
            switch (DifferenceType.getValue()) {
                case "Ricoverati" -> difference = date1.getRicoveratiConSintomi() - date2.getRicoveratiConSintomi();
                case "Terapia Intensiva" -> difference = date1.getTerapiaIntensivaTotali() - date2.getTerapiaIntensivaTotali();
                case "Positivi" -> difference = date1.getPositiviCorrenti() - date2.getPositiviCorrenti();
                case "Morti" -> difference = date1.getDecedutiTotali() - date2.getDecedutiTotali();
                case "Tamponi" -> difference = date1.getTamponiTotali() - date2.getTamponiTotali();
            }
            if (difference>0)           //per mettere i punti sulle i: se è un numero positivo, metto un '+' prima della cifra
                DifferenceLabel.setText("Variazione: +" + difference);
            else
                DifferenceLabel.setText("Variazione: " + difference);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Il calcolo della variazione non è andato a buon fine.\nControlla che: \n- Le due date siano inserite valide ed inserite nel formato corretto.\n- Sia selezionata una opzione valida nel menu a tendina.");
            alert.show();
        }

    }

    /*
    Metodo che mostra nella tabella solo una giornata, ricarcata tramite l'inserimento in un campo della sua data.
     */
    public void ShowSelectedDates() throws IOException, CsvException {
        String[] dates = SearchForDay.getText().split(", ");
        if (table.getItems().size()!=counter)     //se ho già cercato delle date, prima ripopolo il vettore con tutti i dati
            readData();

        ObservableList<DayLog> logs = FXCollections.observableList(Covid.searchForDays(dates));
        table.getItems().clear();
        table.getItems().addAll(logs);      //elimina la tabella e inserisce una sola riga

    }

    /*
    Metodo che permette di salvare i file da cui vengono letti i dati in un altra posizione (reperibile dall'utente utilizzatore del programma)
     */
    public void SaveCSVFile() {
        FileChooser fileChooser = new FileChooser();        //apre una file di file explorer in base al sistema operativo utilizzato.
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", ".csv"));
        fileChooser.setTitle("Scegli dove vuoi salvare il file");

        File file = fileChooser.showSaveDialog(null);
        if (file!=null) {
            try {
                Files.copy(Path.of(filePath), Path.of(file.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);        //salva il file
                System.out.println("The file has been saved in \"" + file.getAbsolutePath() + "\"");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Il file è stato salvato con successo.");
                alert.show();
            }
            catch (NullPointerException | IOException ex){
                System.out.println("Failed to save the file in \""+file.getAbsolutePath()+"\"");
            }
        }
        else
            System.out.println("Failed to save the file");


    }

    //DA AGGIUNGERE: METTERE SCORCIATOIE DA TASTIERA   (facoltativo)



}