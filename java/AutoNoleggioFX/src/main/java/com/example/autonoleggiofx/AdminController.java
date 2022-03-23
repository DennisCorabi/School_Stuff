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
    public TextField PasswordToLoginTextField;
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
    //rimuovi macchinate
    public Button DeleteButton;


    //Aggiungi o rimuovi amministratore
    public TextField UsernameTextField;
    public TextField PasswordTextField;

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
    public TableColumn<Auto, Long> RicavoTotaleColumn;


    @FXML
    public void initialize(){
        UserManager.ReadJson();
    }

    /*
   Metodo che gestisce la pagina di login: controlla che le credenziali siano corrette, che i campi siano compilati correttamente.
   Se le credenziali sono corrette, allora chiama un metodo per inizializzare la parte amministrativa.
    */
    public void Login(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login effettuato con successo.\nL'area amministrativa è stata sbloccata.");
        Admin userLogin = new Admin(UserNameTextField.getText(), PasswordToLoginTextField.getText());

        //controlla che siano state inserite le credenziali corrette.
        if (UserManager.Login(userLogin)) InitializeTabs();
        else{
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Password e/o Email non corretta. Riprova.");
        }
        alert.show();

        //Pulisce i campi dopo l'inserimento dei dati
        UserNameTextField.clear();
        PasswordToLoginTextField.clear();
    }

    /*
    Metodo che gestisce l'inizializzazione della tab riservata al super User
     */
    private void InitializeTabs(){
        LoginButton.setDisable(true);
        AdminTab.setDisable(false);             //disattivo tutte quelle tabs e oggetti inutili dopo aver fatto la login
        GestisciTab.setDisable(false);

        InitializeAutoTable();      //inizializzo le tabelle
        InitializeChoiceBoxes();    //inizializzo i menu di scelta
        UpdateTable(AutoManager.getAuto());      //inserisce tutte le auto (disponibili + noleggiate) in una tabella;

        //aggiungo un listener per chiamare una funzione ogni qualvolta seleziono una riga non vuota della tabella
        carTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> ShowCarSelected(newValue));
    }

    /*
    Metodo per visualizzare l'auto selezionata nella tabella. viene chiamato quando seleziono un auto
     */
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
        //se per qualche motivo l'auto selezionata non è più disponibile, svuoto tutti i campi delle informazioni
        catch (NullPointerException ex){
            ProduttoreChoiceBox.setValue(null);
            ModelloTextField.setText("");
            TargaTextField.setText("");
            CostoEditTextField.setText("");
        }
    }

    /*
    Metodo che inizializza una tabella: mappa le colonne in modo che siano legate a un solo parametro della classe auto
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

        //menu di scelta usato per aggiungere un auto
        ProduttoreChoiceBox.setItems(FXCollections.observableList(AutoManager.getChoices()));
        //menu di scelta usato per modifcare un auto
        ProduttoreEditChoiceBox.setItems(FXCollections.observableList(AutoManager.getChoices()));

        //menu di scelta usato per decidere il formato in cui salvare le auto disponibili o noleggiate
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
            Auto autoToRemove = carTable.getSelectionModel().getSelectedItem();     //ottengo l'auto selezionata nella tabella (quella da rimuovere)
            if (autoToRemove==null) throw new NullPointerException();
            AutoManager.DeleteAuto(autoToRemove);
            UpdateTable(AutoManager.getAuto());      //dopo aver eliminato una macchina, aggiorno la tabella
        }

        //se clicco il bottone senza aver selezionato un auto
        catch (NullPointerException | IOException ex){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Seleziona un auto nella tabella prima di continuare.");
        }
        alert.show();

    }

    /*
    Metodo per aggiungerere un auto nella lista disponibili
     */
    public void AddCar() throws IOException {       //TODO: Handle NUll pointer exception

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Inserimento avvenuto con successo.");
        try {
            String modello = ModelloTextField.getText();
            String targa = TargaTextField.getText();
            Float costo = Float.parseFloat(PrezzoTextField.getText());
            Auto auto = new Auto(targa, ProduttoreChoiceBox.getValue(), modello, costo);    //creo l'oggetto della macchina da creare

            //controllo che la targa abbia otto caratteri
            if (targa.length() != 8) {
                // TODO: 22/03/2022 Come nel main controller, implemento un controllo della targa
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("La targa deve avere 8 caratteri.");
                alert.show();
                return;
            }

            //se non ho scelto nessun produttore, manda una exception
            if (ProduttoreChoiceBox.getValue()==null) throw new NullPointerException();

            //controllo che la targa sia disponibile (che non ci siano auto disponibili o noleggiate che abbiamo la medesima targa)
            if (AutoManager.IsTargaUsable(auto.getTarga())) {
                AutoManager.InsertAuto(auto);
                UpdateTable(AutoManager.getAuto());     //dopo aver aggiunto la macchina, aggiorno la tabella
                ClearFieldsInAddCarPage();      //dopo aver aggiunto la macchina, pulisco tutti campi di inserimento

            }
            //se è presente una macchina con la stessa targa
            else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("E' già presente una macchina con questa targa: " + auto.getTarga());
            }
        //se non ho compilato correttamente tutti i campi
        }catch (NullPointerException | NumberFormatException ex){
            alert.setContentText("Compila tutti i campi ed inserisci valori validi prima di continuare.");
        }
        alert.show();
    }

    /*
    Metodo che permette di modificare un auto tra quelle disponibii
     */
    public void EditCar(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Seleziona una macchina e compila correttamente tutti i campi prima di continuare.");
        try {
            //ottengo l'indice nella lista disponibili dell'auto selezionata nella tabella
            int index = AutoManager.getAutoDisponibiliList().indexOf(carTable.getSelectionModel().getSelectedItem());
            Auto.Produttore produttore = ProduttoreEditChoiceBox.getValue();
            String modello = ModelloEditTextField.getText();
            String targa = TargaEditTextField.getText();
            String costo = CostoEditTextField.getText();
            AutoManager.EditAuto(index, produttore, modello, targa, Float.parseFloat(costo));       //passo i parametri nella funzione
            AutoManager.getAuto().forEach(System.out::println);

            ObservableList<Auto> clearObservableList = FXCollections.observableArrayList(new ArrayList<>());
            carTable.setItems(clearObservableList);

            UpdateTable(AutoManager.getAuto());     //dopo aver modificato un auto, aggiorno la tabella todo: non aggiorna la tabella
        }
        //se non compilo correttamente tutti i campi o non sono stati compilati correttamente
        catch (NumberFormatException | IndexOutOfBoundsException ex){
            alert.show();
        }
        //se per qualche motivo a me sconosciuto la modifica dell'auto non va a buon fine
        catch (IOException ex){
            alert.setContentText("Modifica dell'auto non andata a buon fine.");
            alert.show();
        }
    }

    /*
    Metodo per pulire i campi relativi all'inserimento dei parametri dell'auto da creare
     */
    public void ClearFieldsInAddCarPage(){
        ProduttoreChoiceBox.setValue(null);
        ModelloTextField.clear();
        TargaTextField.clear();
        PrezzoTextField.clear();
    }

    /*
    Metodo che chiama un altro metodo che genera una targa univoca di 8 caratteri
     */
    public void GenerateTarga(){
        TargaTextField.setText(Auto.generateTarga());
    }

    /*
    Metodo che aggiorna la tabella
     */
    public void UpdateTable(List<Auto> autoList){

        //se voglio mostrare la lista completa delle auto, prima setto a false il menu vero/falso
        if (autoList.equals(AutoManager.getAuto())) ShowDisponibiliCheckBox.setSelected(false);

        //prima pulisco la tabella inserendo una tabella vuota
        ObservableList<Auto> clearObservableList = FXCollections.observableArrayList(new ArrayList<>());
        carTable.setItems(clearObservableList);

        //dopo inserisco nella tabella passata come argomento della funzione
        ObservableList<Auto> carObservableList = FXCollections.observableList(autoList);
        carTable.setItems(carObservableList);
    }

    /*
    Metodo per salvare tutte le auto disponibili in un file JSON o CSV
     */
    public void SaveDisponibili() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Scegli il formato del file da salvare prima di continuare.");
        try {
            switch (FileTypeChoiceBoxForDisponibili.getValue()) {
                case "JSON" -> AutoManager.SaveJsonDisponibili();           //dò la possibilità di scegliere in quale formato salvare i file
                case "CSV" -> AutoManager.SaveCsvDisponibili();
            }
        }
        //Se il file è vuoto, non lo salva (SERVE SOLO PER IL CSV, un tipo di file che non permette di salvare liste vuote
        catch (NoSuchElementException ex){
            alert.setContentText("Il file è vuoto. non è stato possibile salvarlo.");
            alert.show();
        }
        //se non ho selezionato il formato del file prima di cliccare il bottone
        catch (NullPointerException ex){
            alert.show();
        }
    }

    /*
    Metodo per salvare la lista delle auto noleggiate: in un tutto e per tutto identico al salvataggio delle auto disponibili
     */
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

    /*
    Metodo per caricare una finestra
     */
    private void LoadWindow(String fxmlName, String windowTitle){
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlName)));   //nome del file .fxml da caricare
            Stage stage = new Stage();
            stage.setTitle(windowTitle);        //nome della finestra da caricare
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            System.out.println("Caricamento della finestra fallito.");
        }
    }

    //Metodi per caricare la finestra delle auto noleggiate o quella del recupero credenziali
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
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));     //seleziono la scena principale
            AdminAnchorPane.getChildren().setAll(pane);     //carica sulla stessa finestra la nuova scena
        }
        catch (IOException ex){
            System.out.println("Il caricamento della pagina non è andato a buon fine.");
        }

    }

    /*
    Metodo che ricarica la pagina amministrativa, simulando quindi una disconnessione TODO: merge with ExitAdminPage
     */
    public void Logout(){
        try {
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Admin.fxml")));
            AdminAnchorPane.getChildren().setAll(pane);
        }
        catch (IOException ex){
            System.out.println("Il caricamento della pagina non è andato a buon fine.");
        }
    }

    /*
    Metodo che semplicemente esce dal programma
     */
    public void CloseApplication(){
        Platform.exit();
    }
}