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
import java.net.URL;
import java.nio.file.Files;
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

    private static final String filePath = "src/main/resources/dpc-covid19-ita-andamento-nazionale.csv";
    private static final String internetFilePath = "https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-andamento-nazionale/dpc-covid19-ita-andamento-nazionale.csv";




    //Metodo che viene chiamato solamente quando viene aperta la finestra
    @FXML
    public void initialize() throws IOException, CsvException {
        InitalizeTable();
        insertData();
    }

    private void InitalizeTable(){
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
       retrieveData();

       if (!table.getItems().isEmpty()) {  //se la tabella non è vuota, prima inserire i dati cancello tutto il suo contenuto.
           table.getItems().clear();
       }
       readData();      //FARE METODO CHE VERIFICA SE BISOGNA SCARICARE I DATI NUOVI OPPURE NO

       ObservableList<DayLog> dayLogs = FXCollections.observableList(Covid.getCovidLog());
       LatestUpdate.setText("Ultimo aggiornamento: " + Covid.getLatestDate());
       table.setItems(dayLogs);

    }

    /*
    Metodo che mi permette di scaricare in automatico il resoconto complessivo a livello nazionale dell'andamento del Covid.
    Dopo averlo scaricato, lo salva nel percorso specificato.
    Volendo, basta cambiare l'URL e questo metodo diventa riutilizzabile per scaricare qualsiasi file da internet.
     */
    public void retrieveData() throws IOException {
        URL url = new URL(internetFilePath);
        System.out.println("Downloading from \""+ internetFilePath +"\"");
        try {
            InputStream in = url.openStream();      //apre un collegamento a internet e scarica il file.
            Files.copy(in, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);   //copia il file in modo permanente sulla nostra macchina nel percorso specificato.
        }
        catch (Exception ex){
            System.out.println("Qualcosa non è andato a buon fine...");
            ex.printStackTrace();
        }

        System.out.println("Finished fetching data from \""+ internetFilePath +"\"");


    }

    /*Metodo Legge il file individuato dal suo percorso (filepath) e ne legge il contenuto.
    Dopodiché, per ogni riga del file crea un oggetto che rappresenta il resoconto della singola giornata.
    Infine, aggiunge il resoconto a una lista contente tutti i resoconti a partire dal 24 febbraio 2020.
     */
    public void readData() throws IOException, CsvException {

        CSVReader reader = new CSVReader(new FileReader(filePath));        //uso la dependency OpenCSV per gestire i file con estensione .csv
        String[] content;

        int counter = 0;
        while ((content = reader.readNext())!=null) {       //per ogni riga del file, chiamo la funzione.
            if (counter!=0)
                insertDay(content);
            counter++;
        }
        System.out.println("Finished reading data from file at \""+filePath+"\"");
    }

    /*
    Metodo che, data una serie di parametri, crea un oggetto che rappresenta il resoconto della giornata
     */
    private void insertDay(String[] data){
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

    public void searchDay() throws IOException, CsvException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        DayLog log = Covid.searchForDay(SearchForDay.getText());
        if (log==null){
            alert.setContentText("Non è stato trovato un risultato.");
            alert.show();
            return;
        }

        table.getItems().clear();
        table.getItems().add(log);
    }
}
