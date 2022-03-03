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

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

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

    @FXML
    public Button UpdateTableButton;

    private static final String filePath = "src/main/resources/dpc-covid19-ita-andamento-nazionale.csv";
    private static final String internetFilePath = "https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-andamento-nazionale/dpc-covid19-ita-andamento-nazionale.csv";

    //Metodo che viene chiamato solamente quando viene aperta la finestra
    @FXML
    public void initialize() throws IOException, CsvException {
        InitalizeTable();
        insertData();
    }

    private void InitalizeTable() {
        DataColumn.setCellValueFactory(new PropertyValueFactory<>("Data"));
        RicoveratiColumn.setCellValueFactory(new PropertyValueFactory<>("RicoveratiConSintomi"));
        TerapiaIntensivaColumn.setCellValueFactory(new PropertyValueFactory<>("TerapiaIntensivaTotali"));
        PositiviTotaliColumn.setCellValueFactory(new PropertyValueFactory<>("PositiviCorrenti"));
        PositiviNuoviColumn.setCellValueFactory(new PropertyValueFactory<>("PositiviOggi"));
        MortiTotaliColumn.setCellValueFactory(new PropertyValueFactory<>("DecedutiTotali"));
        TamponiTotaliColumn.setCellValueFactory(new PropertyValueFactory<>("TamponiTotali"));

    }


    /*Metodo che mi permette di scaricare, leggere e inserire dei dati nella tabella
    Le singole azioni sono delegate ad altri metodi (per favorire la riusabilità del codice e una maggiore comprensione di quest'ultimo)
     */
    public void insertData() throws IOException, CsvException {
        UpdateTableButton.setDisable(true);     //lo disattivo fino a che non ho finito di fare la routine
        retrieveData();
        if (!table.getItems().isEmpty()) {  //se la tabella non è vuota, prima inserire i dati cancello tutto il suo contenuto.
            table.getItems().clear();
        }
        readData();

        ObservableList<DayLog> dayLogs = FXCollections.observableList(Covid.getCovidLog());
        table.setItems(dayLogs);
        LatestUpdate.setText("Ultimo aggiornamento: " + Covid.getLatestDate());
        UpdateTableButton.setDisable(false);

    }

    /*
    Metodo che mi permette di scaricare in automatico il resoconto complessivo a livello nazionale dell'andamento del Covid.
    Dopo averlo scaricato, lo salva nel percorso specificato.
    Volendo, basta cambiare l'URL e questo metodo diventa riutilizzabile per scaricare qualsiasi file da internet.
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
            ex.printStackTrace();
        }
    }

    /*Metodo Legge il file individuato dal suo percorso (filepath) e ne legge il contenuto.
    Dopodiché, per ogni riga del file crea un oggetto che rappresenta il resoconto della singola giornata.
    Infine, aggiunge il resoconto a una lista contente tutti i resoconti a partire dal 24 febbraio 2020.
     */
    public void readData() throws IOException, CsvException {

        CSVReader reader = new CSVReader(new FileReader(filePath));        //uso la dependency OpenCSV per gestire i file con estensione .csv
        String[] content;
        int counter = 0;

        while ((content = reader.readNext()) != null) {       //per ogni riga del file, chiamo la funzione.
            if (counter != 0)
                insertDay(content);
            counter++;
        }
        reader.close();
        System.out.println("Finished reading data from file at \"" + filePath + "\"");
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

    public void calculateDifference() throws IOException, CsvException {
        DayLog date1 = searchDay(Difference1.getText());
        DayLog date2 = searchDay(Difference2.getText());
        if (date1!=null && date2!=null)
            System.out.println(date1.getData()+" "+ date2.getData());


        //differenza in base alla scelta che si è fatta.
    }

    /*
    Metodo che ricerca ed eventualmente ritorna il resoconto di una giornata specifica, individuata dalla sua data
     */
    public DayLog searchDay(String date) throws IOException, CsvException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        DayLog log = Covid.searchForDay(date);
        if (log == null) {
            alert.setContentText("Non è stato trovato un risultato.");
            alert.show();
            return null;
        }
        return log;
    }

    /*
    Metodo che mostra nella tabella solo una giornata, ricarcata tramite l'inserimento in un campo della sua data.
     */
    public void ShowLog() throws IOException, CsvException {
        DayLog log = searchDay(SearchForDay.getText());
        table.getItems().clear();
        table.getItems().add(log);
    }
}