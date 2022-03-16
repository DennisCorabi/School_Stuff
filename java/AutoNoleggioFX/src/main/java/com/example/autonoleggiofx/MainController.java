package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Auto;
import com.example.autonoleggiofx.Model.AutoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    public ChoiceBox<String> FileTypeChoiceBox;
    public ChoiceBox<String> CarModelChoiceBox;

    public Label TargaValue;
    public Label CostoValue;
    public Label ModelloValue;
    public Label DataValue;
    public Label ProduttoreValue;
    @FXML
    public void initialize(){
        InitializeTable();
        InitializeChoiceBox();
        UpdateTable();

        /*aggiungo un "listener" alla finestra: quando clicco su una riga della tabella, chiamo la funzione "carInspector"
        alla funzione passo le info della macchina che ho cliccato.
         */
        carTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> CarInspector(newValue));
    }

    /*
    Metodo che inizializza i menu di scelta in tutta la finestra:
    TODO: spostare il salvataggio della tabella in json o csv nella parte di admin (qui è inutile)
     */
    private void InitializeChoiceBox(){
        List<String> fileTypesList = new ArrayList<>() {{add("JSON"); add("CSV");}};
        ObservableList<String> fileTypesObservableList = FXCollections.observableList(fileTypesList);
        FileTypeChoiceBox.setItems(fileTypesObservableList);


        List<String> carModelsList = new ArrayList<>() {{add("FIAT"); add("FERRARI"); add("LAMBROGHINI");}};
        ObservableList<String> carModelsObservableList = FXCollections.observableList(carModelsList);
        CarModelChoiceBox.setItems(carModelsObservableList);
    }

    /*
    Metodo che inizializza una tabella: mappa le colonne in modo che siano legate a un solo parametro della classe AUTO
    al posto di scrivere codice, chiamo il metodo statico di un altra classe (anche se non ha molto senso)
     */
    private void InitializeTable(){
        AdminController.InitializeTable(ProduttoreColumn, ModelloColumn, TargaColumn, CostoColumn, DataColumn);
    }

    /*
    Metodo che aggiorna la tabella
    TODO: controllare che sia corretto
     */
    private void UpdateTable(){
        AutoManager.ReadJson();
        ObservableList<Auto> cars = FXCollections.observableList(AutoManager.getAutoList());
        ObservableList<Auto> clearObservableList = FXCollections.observableArrayList(new ArrayList<>());
        carTable.setItems(clearObservableList);

        carTable.setItems(cars);
        System.out.println("Sono stati caricate "+AutoManager.getCounter()+" auto.");
    }

    /*
    Metodo per visualizzare le informazioni dell'auto cliccata sulla tabella.
    Le info vengono visualizzate in una grid pane a lato della tabella.
     */
    private void CarInspector(Auto car){
        ProduttoreValue.setText(car.getProduttore());
        ModelloValue.setText(car.getModello());
        TargaValue.setText(car.getTarga());
        CostoValue.setText(car.getCostoGiornaliero().toString());
        DataValue.setText(car.getDataNoleggio());
    }

    /*
    Metodo per caricare, sulla medesima finestra, la parte del programma destinata a essere utilizzata dall'amministratore dell'azienda.
     */
    public void OpenAdmin(){
        try{
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Admin.fxml")));
            MainAnchorPane.getChildren().setAll(pane);
        }
        catch (IOException ex){
            System.out.println("Il caricamento della pagina non è andato a buon fine.");
        }
    }

    /*
    Metodo per salvare tutte le auto contenute in una tabella in un file JSON o CSV
    TODO: spostare il metodo nella parte amministrativa, qui è inutile.
     */
    public void saveAsButton() {
        try {
            switch (FileTypeChoiceBox.getValue()) {
                case "JSON" -> AutoManager.saveAsJSON();            //dò la possibilità di scegliere in quale formato salvare i file
                case "CSV" -> AutoManager.saveAsCSV();
            }
        }
        catch (NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Seleziona una delle opzioni prima di continuare");
            alert.show();
        }
    }

    /*
    Metodo per visualizzare nella tabella solo le auto di una marca particolare.
    FIXME non va nulla di questa funzione, ma spero che il nuovo sistema di lettura-scrittura-modifica dei dati si riuscirà a farlo funzionare
     */
    public void GetCarsByMarca(){
        List<Auto> filteredList = AutoManager.getCarsByModel(CarModelChoiceBox.getValue());
        ObservableList<Auto> filteredObservableList = FXCollections.observableList(filteredList);
        ObservableList<Auto> clearList = FXCollections.observableArrayList(new ArrayList<>());
        carTable.setItems(clearList);
        carTable.setItems(filteredObservableList);
    }


}