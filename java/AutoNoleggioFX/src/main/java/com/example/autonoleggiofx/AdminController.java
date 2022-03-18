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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminController {

    public AnchorPane AdminAnchorPane;

    /*
    LOGIN TAB
     */
    public TextField PasswordTextField;
    public TextField EmailTextField;
    public Button LoginButton;

    /*
    ADMIN TAB
     */
    public Tab AdminTab;

    //modifica una macchina
    public ChoiceBox<Auto.Produttore> ProduttoreEditChoiceBox;
    public TextField ModelloEditTextField;
    public TextField TargaEditTextField;
    public TextField CostoEditTextField;

    //Aggiungi macchina
    public Tab GestisciTab;
    public TextField ModelloTextField;
    public TextField TargaTextField;
    public DatePicker DataPicker;
    public ChoiceBox<Auto.Produttore> ProduttoreChoiceBox;
    public TextField PrezzoTextField;

    //salva una macchina
    public ChoiceBox<String> FileTypeChoiceBox;

    //rimuovi macchina
    public Button DeleteButton;


    @FXML
    TableView<Auto> carTable;

    public TableColumn<Auto, Auto.Produttore> ProduttoreColumn;
    public TableColumn<Auto, String> ModelloColumn;
    public TableColumn<Auto, String> TargaColumn;
    public TableColumn<Auto, Float> CostoColumn;
    public TableColumn<Auto, LocalDate> DataColumn;


    /*
    Metodo che gestisce la pagina di login: controlla che le credenziali siano corrette, che i campi siano compilati correttamente.
    Se le credenziali sono corrette, allora chiama un metodo per inizializzare la parte amministrativa.
     */
    public void Login(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String PASSWORD_VALUE = "Admin";
        String EMAIL_VALUE = "Admin";

        //controlla che siano stati compilati tutti i campi
        if (PasswordTextField.getText().isEmpty() || EmailTextField.getText().isEmpty()){
            alert.setContentText("Devi compilare correttamente i due campi!");
            alert.show();
            return;
        }

        //controlla che siano state inserite le credenziali corrette.

        boolean isEmailCorrect = Objects.equals(EmailTextField.getText(), EMAIL_VALUE);
        boolean isPasswordCorrect = Objects.equals(PasswordTextField.getText(), PASSWORD_VALUE);

        if (isEmailCorrect && isPasswordCorrect) {
            InitializeTabs();

            alert.setAlertType(Alert.AlertType.INFORMATION);
            LoginButton.setDisable(true);
            alert.setContentText("Login effettuato con successo.\nL'area amministrativa è stata sbloccata.");
        }
        else{
            alert.setContentText("Password e/o Email non corretta. Riprova.");
        }
        alert.show();
        ClearFieldsInLoginPage();       //pulisce i campi dopo l'inserimento dei dati
    }

    /*
    Metodo che gestisce l'inizializzazione della tab riservata al super User
     */
    private void InitializeTabs(){
        AdminTab.setDisable(false);
        GestisciTab.setDisable(false);

        InitializeDisponibiliTable(ProduttoreColumn, ModelloColumn, TargaColumn, CostoColumn, DataColumn);
        InitializeChoiceBoxes();
        UpdateTable();      //inserisce le auto disponibili in una tabella
    }

    /*
    Metodo che inizializza una tabella: mappa le colonne in modo che siano legate a un solo parametro della classe AUTO
     */
    public static void InitializeDisponibiliTable(TableColumn<Auto, Auto.Produttore> produttoreColumn, TableColumn<Auto, String> modelloColumn, TableColumn<Auto, String> targaColumn, TableColumn<Auto, Float> costoColumn, TableColumn<Auto, LocalDate> dataColumn) {
        produttoreColumn.setCellValueFactory(new PropertyValueFactory<>("Produttore"));
        modelloColumn.setCellValueFactory(new PropertyValueFactory<>("Modello"));
        targaColumn.setCellValueFactory(new PropertyValueFactory<>("Targa"));
        costoColumn.setCellValueFactory(new PropertyValueFactory<>("CostoGiornaliero"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("DataNoleggio"));
    }

    static void InitializeNoleggiatiTable(){

    }

    /*
    Metodo per inserire tutte le possibili opzioni di tutti i menu di scelta
     */
    private void InitializeChoiceBoxes(){
        ProduttoreChoiceBox.setItems(FXCollections.observableList(AutoManager.getChoices()));
        ProduttoreEditChoiceBox.setItems(FXCollections.observableList(AutoManager.getChoices()));

        List<String> fileTypesList = new ArrayList<>() {{add("JSON"); add("CSV");}};
        ObservableList<String> fileTypesObservableList = FXCollections.observableList(fileTypesList);
        FileTypeChoiceBox.setItems(fileTypesObservableList);

    }

    /*
    Metodo che pulisce i field della tab di login
     */
    public void ClearFieldsInLoginPage(){
        EmailTextField.clear();
        PasswordTextField.clear();
    }

    /*
    Metodo che elimina una macchina tra quelle disponibili
    TODO: 15/03/2022 sarebbe da unire al metodo che sposta una macchina dalle disponibili alle noleggiate
     */

    public void DeleteCar(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        try{
        Auto autoToRemove = carTable.getSelectionModel().getSelectedItem();
        AutoManager.DeleteAuto(autoToRemove);
        alert.setContentText("Rimozione dell'auto avvenuta con successo.");
        alert.show();

        }
        catch (NullPointerException | IOException ex){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Seleziona un auto nella tabella prima di continuare.");
            alert.show();
        }

        UpdateTable();      //dopo aver eliminato una macchina, aggiorno la tabella
    }

    public void AddCar() throws IOException {       //TODO: Handle NUll pointer exception

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String modello = ModelloTextField.getText();
        String targa = TargaTextField.getText();
        Float costo = Float.parseFloat(PrezzoTextField.getText());
        String data = DataPicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Auto auto = new Auto(targa, data, ProduttoreChoiceBox.getValue(),modello, costo);

        //controllo che tutti i campi siano stati compilati correttamente
        if (ModelloTextField.getText().isEmpty() || TargaTextField.getText().isEmpty() || PrezzoTextField.getText().isEmpty() || DataPicker.getValue()==null || ProduttoreChoiceBox.getValue()==null) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Compila tutti i campi prima di aggiungere una nuova macchina.");
            alert.show();
            return;
        }

        //controllo che la targa abbia otto caratteri
        if (targa.length()!=8){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("La targa deve avere 8 caratteri.");
            alert.show();
            return;
        }
        if (AutoManager.IsTargaUsable(auto.getTarga())) {

            AutoManager.InsertAuto(auto);
            alert.setContentText("Inserimento avvenuto con successo.");
            UpdateTable();
            ClearFieldsInAddCarPage();
            //add scrittura file, se non non viene salvato le nuove auto

        } else {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("E' già presente una macchina con questa targa: " + auto.getTarga());
        }
        alert.show();
    }

    public void ClearFieldsInAddCarPage(){
        ProduttoreChoiceBox.setValue(null);
        DataPicker.setValue(null);
        ModelloTextField.clear();
        TargaTextField.clear();
        PrezzoTextField.clear();
    }

    public void GenerateTarga(){
        TargaTextField.setText(Auto.generateTarga());
    }

    /*
    Metodo che aggiorna la tabella
     */

    public void UpdateTable(){

        ObservableList<Auto> clearObservableList = FXCollections.observableArrayList(new ArrayList<>());
        carTable.setItems(clearObservableList);

        ObservableList<Auto> carObservableList = FXCollections.observableList(AutoManager.getAutoDisponibiliList());
        carTable.setItems(carObservableList);
    }

    /*
    Metodo per salvare tutte le auto contenute in una tabella in un file JSON o CSV
     */
    public void saveAsButton() {
        try {
            switch (FileTypeChoiceBox.getValue()) {
                case "JSON" -> AutoManager.saveAsJSON();            //dò la possibilità di scegliere in quale formato salvare i file
                case "CSV" -> AutoManager.SaveAsCSV();
            }
        }
        catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Seleziona una delle opzioni prima di continuare");
            alert.show();
        }
    }

    public void ShowAutoNoleggiate(){
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AutoNoleggiate.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Auto noleggiate");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            System.out.println("Carimento della finestra fallito.");
        }
    }

    /*
    Metodo che dalla pagina admin torna alla pagina del cliente.
     */
    public void ExitAdminPage(){
        try{
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));     //seleziona una nuova scena
            AdminAnchorPane.getChildren().setAll(pane);     //carica sulla stessa finestra la nuova scena
        }
        catch (IOException ex){
            System.out.println("Il caricamento della pagina non è andato a buon fine.");
        }

    }

    public void Logout(){
        try {
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Admin.fxml")));
            AdminAnchorPane.getChildren().setAll(pane);
        }
        catch (IOException ex){
            System.out.println("Il caricamento della pagina non è andato a buon fine.");
        }
    }
}