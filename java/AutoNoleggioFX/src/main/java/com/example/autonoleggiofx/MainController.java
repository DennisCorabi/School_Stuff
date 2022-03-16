package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Auto;
import com.example.autonoleggiofx.Model.AutoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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
    @FXML
    public void initialize(){
        InitializeTable();
        InitializeChoiceBox();
        carTable.getItems().clear(); //serve nel momento in cui dalla finestra amministrativa torno nell' area cliente
        CarInspector(null); //serve per "nascondere" il testo iniziale delle etichette nella descrizione dell'auto
        UpdateTable();
        if (carTable.getItems().isEmpty())  //se si sta caricando la finestra per la prima volta, carico le auto del file nel vettore
            AutoManager.ReadJson();


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
        AdminController.InitializeTable(ProduttoreColumn, ModelloColumn, TargaColumn, CostoColumn, DataColumn);
    }

    /*
    Metodo che aggiorna la tabella
    TODO: controllare che sia corretto
     */
    private void UpdateTable(){

        ObservableList<Auto> clearObservableList = FXCollections.observableArrayList(new ArrayList<>());
        carTable.setItems(clearObservableList);
        ObservableList<Auto> cars = FXCollections.observableList(AutoManager.getAutoList());
        carTable.setItems(cars);
    }

    /*
    Metodo per visualizzare le informazioni dell'auto cliccata sulla tabella.
    Le info vengono visualizzate in una grid pane a lato della tabella.
     */
    private void CarInspector(Auto car){
        try {
            ProduttoreValue.setText(car.getProduttore().toString());
            ModelloValue.setText(car.getModello());
            TargaValue.setText(car.getTarga());
            CostoValue.setText(car.getCostoGiornaliero().toString());
            DataValue.setText(car.getDataNoleggio());
        }
        catch (NullPointerException ex){
            ProduttoreValue.setText("");
            ModelloValue.setText("");
            TargaValue.setText("");
            CostoValue.setText("");
            DataValue.setText("");
        }
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
    Metodo per visualizzare nella tabella solo le auto di una marca particolare.
     */
    public void GetCarsByMarca(){
        ObservableList<Auto> clearList = FXCollections.observableArrayList(new ArrayList<>());
        carTable.setItems(clearList);

        List<Auto> filteredList = AutoManager.getCarsByProduttore(CarModelChoiceBox.getValue());
        ObservableList<Auto> filteredObservableList = FXCollections.observableList(filteredList);
        carTable.setItems(filteredObservableList);




    }


}