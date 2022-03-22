package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Auto;
import com.example.autonoleggiofx.Model.AutoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainController {

    public AnchorPane MainAnchorPane;


    @FXML
    TableView<Auto> carTable;

    public TableColumn<Auto, Auto.Produttore> ProduttoreColumn;
    public TableColumn<Auto, String> ModelloColumn;
    public TableColumn<Auto, String> TargaColumn;
    public TableColumn<Auto, Float> CostoColumn;
    public TableColumn<Auto, LocalDate> DataColumn;
    public ChoiceBox<Auto.Produttore> CarModelChoiceBox;

    public Label TargaValue;
    public Label CostoValue;
    public Label ModelloValue;
    public Label DataValue;
    public Label ProduttoreValue;
    public TextField RestituisciTextField;

    @FXML
    public void initialize() {
        InitializeTable();
        InitializeChoiceBox();
        carTable.getItems().clear(); //serve nel momento in cui dalla finestra amministrativa torno nell' area cliente
        CarInspector(null); //serve per "nascondere" il testo iniziale delle etichette nella descrizione dell'auto
        ShowAllCars();

        //se si sta caricando la finestra per la prima volta, carico le auto dei file nei rispettivi vettori
        if (AutoManager.getAutoDisponibiliList().isEmpty()) AutoManager.ReadJsonDisponibili();
        if (AutoManager.getAutoNoleggiateList().isEmpty()) AutoManager.ReadJsonNoleggiati();

        /*aggiungo un "listener" alla finestra: quando clicco su una riga della tabella, chiamo la funzione "carInspector"
        alla funzione passo le info della macchina che ho cliccato.
         */
        carTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> CarInspector(newValue));
    }

    /*
    Metodo che inizializza i menu di scelta in tutta la finestra:
     */
    private void InitializeChoiceBox(){
        ObservableList<Auto.Produttore> carModelsObservableList = FXCollections.observableList(AutoManager.getChoices());
        CarModelChoiceBox.setItems(carModelsObservableList);
    }

    /*
    Metodo che inizializza una tabella: mappa le colonne in modo che siano legate a un solo parametro della classe AUTO
    al posto di scrivere codice, chiamo il metodo statico di un altra classe (anche se non ha molto senso)
     */
    private void InitializeTable(){
            ProduttoreColumn.setCellValueFactory(new PropertyValueFactory<>("Produttore"));
            ModelloColumn.setCellValueFactory(new PropertyValueFactory<>("Modello"));
            TargaColumn.setCellValueFactory(new PropertyValueFactory<>("Targa"));
            CostoColumn.setCellValueFactory(new PropertyValueFactory<>("CostoGiornaliero"));
            DataColumn.setCellValueFactory(new PropertyValueFactory<>("DataUltimaRestituzione"));
    }

    /*
    Metodo che aggiorna la tabella
     */
    private void UpdateTable(){

        //prima svuoto la tabella riempiendola con una lista vuota
        ObservableList<Auto> clearObservableList = FXCollections.observableArrayList(new ArrayList<>());
        carTable.setItems(clearObservableList);

        //dopo visualizzo la lista delle auto disponibili
        ObservableList<Auto> cars = FXCollections.observableList(AutoManager.getAutoDisponibiliList());
        carTable.setItems(cars);
    }

    /*
    Metodo per visualizzare le informazioni dell'auto cliccata sulla tabella.
    Le info vengono visualizzate in una grid pane a lato della di quest'ultima.
     */
    private void CarInspector(Auto car){
        try {
            ProduttoreValue.setText(car.getProduttore().toString());
            ModelloValue.setText(car.getModello());
            TargaValue.setText(car.getTarga());
            CostoValue.setText(car.getCostoGiornaliero().toString());
            DataValue.setText(car.getDataUltimaRestituzione());
        }
        //Se per qualche motivo a me sconosciuto, l'auto selezionata non è più disponibile, svuota la selezione
        catch (NullPointerException ex){
            ProduttoreValue.setText("");
            ModelloValue.setText("");
            TargaValue.setText("");
            CostoValue.setText("");
            DataValue.setText("");
        }
    }

    /*
    Metodo per caricare sulla medesima finestra la parte del programma destinata a essere utilizzata dagli amministratori dell'azienda.
     */
    public void OpenAdmin(){
        try{
            //carica il nuovo file .fxml nella stessa finestra (non creandone un'altra)
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Admin.fxml")));
            MainAnchorPane.getChildren().setAll(pane);
        }
        catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Il caricamento della pagina non è andato a buon fine, riprovare");
            alert.show();
        }
    }

    /*
    Metodo per visualizzare nella tabella solo le auto di una marca particolare.
     */
    public void GetCarsByMarca(){

        //se si clicca sul bottone dopo aver selezionato una categoria, mostra solo le auto del produttore scelto.
        if (CarModelChoiceBox.getValue()!=null) {
            ObservableList<Auto> clearList = FXCollections.observableArrayList(new ArrayList<>());
            carTable.setItems(clearList);

            List<Auto> filteredList = AutoManager.getCarsByProduttore(CarModelChoiceBox.getValue());
            ObservableList<Auto> filteredObservableList = FXCollections.observableList(filteredList);
            carTable.setItems(filteredObservableList);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Inserisci una delle opzioni prima di continuare.");
            alert.show();
        }
    }

    /*
    Metodo che permette di visualizzare tutte le auto disponibili nell'autonoleggio
     */
    public void ShowAllCars(){
        UpdateTable();      //semplicemente chiamo la medesima funzione che viene chiamata quando viene caricata la finestra
    }

    /*
    Metodo che permette di noleggiare un auto tra quelle disponibili
     */
    public void AddAutoToNoleggiateList(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            Auto auto = carTable.getSelectionModel().getSelectedItem();     //prende l'auto selezionata nel momento in cui clicco sul bottone
            AutoManager.AddToNoleggiateList(auto);
            UpdateTable();      //mostro la tabella, ora aggiornata (senza la macchina appena noleggiata)

            alert.setContentText("Auto noleggiata con successo.");          //feedback
            alert.show();

            //se clicco sul bottone senza aver selezionato una macchina (riga
        }catch (NullPointerException ex){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Devi prima scegliere una macchina nella tabella.");
            alert.show();

        } catch (IOException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Noleggio della macchina non riuscito, riprovare.");
            alert.show();
        }
    }
    /*
    Metodo per restituire, attraverso l'inserimento della targa, di una macchina noleggiata
     */
    public void RestituisciAuto(){
        String targa = RestituisciTextField.getText();
        Alert alert = new Alert(Alert.AlertType.ERROR, "Inserisci una targa valida prima di continuare");

        //se la targa inserita non rispetta le regole di sintassi
        // TODO: 22/03/2022 Aggiungere controllo che la targa rispetti il formato standard
        if (targa.length() < 8) {
            alert.show();
            return;
        }

        try {
            // TODO: 20/03/2022 OTTIMIZZARE IL CODICE, CHE COSI' FA CAGARE

            //controllo che la targa inserita sia associata ad una macchina tra quelle noleggiate
            for (Auto auto : AutoManager.getAutoNoleggiateList()) {
                if (auto.getTarga().equals(targa)) {
                    float costo =AutoManager.AddToDisponibiliList(auto);        //ottengo il prezzo da pagare per l'uso della macchina
                    UpdateTable();  //mostro la tabella aggiornata (ora con un auto in più)
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Restituzione avvenuta con successo.\nPrezzo da pagare per aver noleggiato la macchina: "+costo+"€ ");
                    alert.show();
                    return;
                }
            }
            alert.setContentText("Non è stata trovata un'auto tra quelle noleggiate con questa targa.");
            alert.show();

        }catch (NullPointerException| IOException ex) {
            ex.printStackTrace();
            alert.setContentText("Restituzione della macchina non riuscita, riprovare.");
            alert.show();
        }
    }
}