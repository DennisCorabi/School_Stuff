package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Admin;
import com.example.autonoleggiofx.Model.Auto;
import com.example.autonoleggiofx.Model.AutoManager;
import com.example.autonoleggiofx.Model.UserManager;
import javafx.application.Platform;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AdminController {

    public AnchorPane AdminAnchorPane;

    /*
    LOGIN TAB
     */
    public TextField PasswordTextField;
    public TextField UserNameTextField;
    public Button LoginButton;

    /*
    ADMIN TAB
     */
    public Tab AdminTab;
    public CheckBox ShowDisponibiliCheckBox;

    //modifica una macchina
    public ChoiceBox<Auto.Produttore> ProduttoreEditChoiceBox;
    public TextField ModelloEditTextField;
    public TextField TargaEditTextField;
    public TextField CostoEditTextField;
    public Button EditCarButton;

    //Aggiungi macchina
    public Tab GestisciTab;
    public TextField ModelloTextField;
    public TextField TargaTextField;
    public ChoiceBox<Auto.Produttore> ProduttoreChoiceBox;
    public TextField PrezzoTextField;

    //salva una macchina
    public ChoiceBox<String> FileTypeChoiceBoxForDisponibili;
    public ChoiceBox<String> FileTypeChoiceBoxForNoleggiate;
    //rimuovi macchina
    public Button DeleteButton;



    @FXML
    TableView<Auto> carTable;

    public TableColumn<Auto, Auto.Produttore> ProduttoreColumn;
    public TableColumn<Auto, String> ModelloColumn;
    public TableColumn<Auto, String> TargaColumn;
    public TableColumn<Auto, Float> CostoColumn;
    public TableColumn<Auto, Boolean> NoleggiataColumn;
    public TableColumn<Auto, String> DataNoleggioColumn;
    public TableColumn<Auto, Long> SecondiNoleggiataColumn;
    public TableColumn<Auto, Integer> VolteNoleggiataColumn;
    public TableColumn<Auto, Float> RicavoTotaleColumn;
    /*
    Metodo che gestisce la pagina di login: controlla che le credenziali siano corrette, che i campi siano compilati correttamente.
    Se le credenziali sono corrette, allora chiama un metodo per inizializzare la parte amministrativa.
     */

    @FXML
    public void initialize(){
        UserManager.ReadJson();
        UserManager.getUserList().forEach(System.out::println);
    }

    public void Login(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login effettuato con successo.\nL'area amministrativa è stata sbloccata.");
        Admin userLogin = new Admin(UserNameTextField.getText(),PasswordTextField.getText());

        //controlla che siano state inserite le credenziali corrette.
        if (UserManager.Login(userLogin)) InitializeTabs();
        else{
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Password e/o Email non corretta. Riprova.");
        }
        alert.show();

        //Pulisce i campi dopo l'inserimento dei dati
        UserNameTextField.clear();
        PasswordTextField.clear();
    }

    /*
    Metodo che gestisce l'inizializzazione della tab riservata al super User
     */
    private void InitializeTabs(){
        LoginButton.setDisable(true);
        AdminTab.setDisable(false);
        GestisciTab.setDisable(false);

        InitializeAutoTable();
        InitializeChoiceBoxes();
        UpdateTable(AutoManager.getAuto());      //inserisce le auto disponibili in una tabella;

        carTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> ShowCarSelected(newValue));
    }

    public void ShowCarSelected(Auto auto){
        try {
            ProduttoreEditChoiceBox.setValue(auto.getProduttore());
            ModelloEditTextField.setText(auto.getModello());
            TargaEditTextField.setText(auto.getTarga());
            CostoEditTextField.setText(auto.getCostoGiornaliero().toString());

            //Se l'auto che viene selezionata è una di quelle noleggiate, vieto all'utente di modificarla o eliminarla
            EditCarButton.setDisable(auto.getNoleggiata());
            DeleteButton.setDisable(auto.getNoleggiata());
        }
        catch (NullPointerException ex){        //se per qualche motivo l'auto selezionata non è più disponibile, svuoto tutti i campi delle informazioni
            ProduttoreChoiceBox.setValue(null);
            ModelloTextField.setText("");
            TargaTextField.setText("");
            CostoEditTextField.setText("");
        }
    }

    /*
    Metodo che inizializza una tabella: mappa le colonne in modo che siano legate a un solo parametro della classe AUTO
     */
    public void InitializeAutoTable(){
        ProduttoreColumn.setCellValueFactory(new PropertyValueFactory<>("Produttore"));
        ModelloColumn.setCellValueFactory(new PropertyValueFactory<>("Modello"));
        TargaColumn.setCellValueFactory(new PropertyValueFactory<>("Targa"));
        CostoColumn.setCellValueFactory(new PropertyValueFactory<>("CostoGiornaliero"));
        NoleggiataColumn.setCellValueFactory(new PropertyValueFactory<>("Noleggiata"));
        DataNoleggioColumn.setCellValueFactory(new PropertyValueFactory<>("DataNoleggio"));
        VolteNoleggiataColumn.setCellValueFactory(new PropertyValueFactory<>("VolteNoleggiate"));
        SecondiNoleggiataColumn.setCellValueFactory(new PropertyValueFactory<>("SecondiInNoleggio"));
        RicavoTotaleColumn.setCellValueFactory(new PropertyValueFactory<>("RicavoTotale"));
    }

    /*
    Metodo per inserire tutte le possibili opzioni di tutti i menu di scelta
     */
    private void InitializeChoiceBoxes(){
        ProduttoreChoiceBox.setItems(FXCollections.observableList(AutoManager.getChoices()));
        ProduttoreEditChoiceBox.setItems(FXCollections.observableList(AutoManager.getChoices()));

        List<String> fileTypesList = new ArrayList<>() {{add("JSON"); add("CSV");}};
        ObservableList<String> fileTypesObservableList = FXCollections.observableList(fileTypesList);
        FileTypeChoiceBoxForDisponibili.setItems(fileTypesObservableList);
        FileTypeChoiceBoxForNoleggiate.setItems(fileTypesObservableList);

    }

    /*
    Metodo che elimina una macchina tra quelle disponibili
     */

    public void DeleteCar(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rimozione dell'auto avvenuta con successo.");

        try{
        Auto autoToRemove = carTable.getSelectionModel().getSelectedItem();
        AutoManager.DeleteAuto(autoToRemove);
        }
        catch (NullPointerException | IOException ex){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Seleziona un auto nella tabella prima di continuare.");
        }
        alert.show();
        UpdateTable(AutoManager.getAuto());      //dopo aver eliminato una macchina, aggiorno la tabella
    }

    public void AddCar() throws IOException {       //TODO: Handle NUll pointer exception

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Inserimento avvenuto con successo.");

        try {
            String modello = ModelloTextField.getText();
            String targa = TargaTextField.getText();
            Float costo = Float.parseFloat(PrezzoTextField.getText());
            Auto auto = new Auto(targa, ProduttoreChoiceBox.getValue(), modello, costo);

            //controllo che la targa abbia otto caratteri
            if (targa.length() != 8) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("La targa deve avere 8 caratteri.");
                alert.show();
                return;
            }

            if (ProduttoreChoiceBox.getValue()==null) throw new NullPointerException();
            if (AutoManager.IsTargaUsable(auto.getTarga())) {
                AutoManager.InsertAuto(auto);
                UpdateTable(AutoManager.getAuto());
                ClearFieldsInAddCarPage();

            } else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("E' già presente una macchina con questa targa: " + auto.getTarga());
            }

        }catch (NullPointerException | NumberFormatException ex){
            alert.setContentText("Compila tutti i campi ed inserisci valori validi prima di continuare.");
        }

        alert.show();
    }

    public void EditCar(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Seleziona una macchina e compila correttamente tutti i campi prima di continuare.");
        try {
            int index = AutoManager.getAutoDisponibiliList().indexOf(carTable.getSelectionModel().getSelectedItem());
            Auto.Produttore produttore = ProduttoreEditChoiceBox.getValue();
            String modello = ModelloEditTextField.getText();
            String targa = TargaEditTextField.getText();
            String costo = CostoEditTextField.getText();
            AutoManager.EditAuto(index, produttore, modello, targa, Float.parseFloat(costo));
            AutoManager.getAuto().forEach(System.out::println);
            UpdateTable(AutoManager.getAuto());
        }
        catch (NumberFormatException | IndexOutOfBoundsException ex){
            alert.show();
        }
        catch (IOException ex){
            alert.setContentText("Modifica dell'auto non andata a buon fine.");
            alert.show();
        }
    }

    public void ClearFieldsInAddCarPage(){
        ProduttoreChoiceBox.setValue(null);
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

    public void UpdateTable(List<Auto> autoList){

        if (autoList.equals(AutoManager.getAuto())) ShowDisponibiliCheckBox.setSelected(false);

        ObservableList<Auto> clearObservableList = FXCollections.observableArrayList(new ArrayList<>());
        carTable.setItems(clearObservableList);

        ObservableList<Auto> carObservableList = FXCollections.observableList(autoList);
        carTable.setItems(carObservableList);
    }

    /*
    Metodo per salvare tutte le auto disponibili o noleggiate in un file JSON o CSV
     */
    public void SaveDisponibili() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Scegli il formato del file da salvare prima di continuare.");
        try {
            switch (FileTypeChoiceBoxForDisponibili.getValue()) {
                case "JSON" -> AutoManager.SaveJsonDisponibili();           //dò la possibilità di scegliere in quale formato salvare i file
                case "CSV" -> AutoManager.SaveCsvDisponibili();
            }
        }
        catch (NoSuchElementException ex){

            alert.setContentText("Il file è vuoto. non è stato possibile salvarlo.");
            alert.show();
        }
        catch (NullPointerException ex){
            alert.show();
        }
    }
    public void SaveNoleggiati(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            switch (FileTypeChoiceBoxForNoleggiate.getValue()) {
                case "JSON" -> AutoManager.SaveJsonNoleggiati();           //dò la possibilità di scegliere in quale formato salvare i file
                case "CSV" -> AutoManager.SaveCsvNoleggiati();
            }
        }
        catch (NoSuchElementException exception){
            alert.setContentText("Il file è vuoto. non è stato possibile salvarlo.");
            alert.show();
        }
        catch (NullPointerException ex){
            alert.setContentText("Scegli il formato del file da salvare prima di continuare.");
            alert.show();
        }
    }

    /*
    Metodo per visualizzare sulla lista solo le auto disponibili.
    Se il checkbox è spuntato, allora stampa solo le auto disponibili. Se ho tolto la spunta, invece, visualizza tutte le auto
     */
    public void ShowOnlyDisponibili(){
        if (ShowDisponibiliCheckBox.isSelected()) UpdateTable(AutoManager.getAutoDisponibiliList());
        else UpdateTable(AutoManager.getAuto());
    }

    private void LoadWindow(String fxmlName, String windowTitle){
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlName)));
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            System.out.println("Caricamento della finestra fallito.");
        }
    }
    public void LoadAutoNoleggiateWindow(){
        LoadWindow("AutoNoleggiate.fxml", "Auto Noleggiate");
    }
    public void LoadRecuperoCredenzialiWindow(){
        LoadWindow("UserSupport.fxml", "Recupero Credenziali");
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

    public void CloseApplication(){
        Platform.exit();
    }
}