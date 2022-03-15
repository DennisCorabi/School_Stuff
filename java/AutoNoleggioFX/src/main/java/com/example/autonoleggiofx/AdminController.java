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
import java.util.SplittableRandom;

public class AdminController {

    public AnchorPane AdminAnchorPane;

    /*
    LOGIN TAB
     */
    public TextField PasswordTextField;
    public TextField EmailTextField;

    /*
    ADMIN TAB
     */
    public Tab AdminTab;
    public Button DeleteButton;

    /*
    AGGIUNGI TAB
     */
    public Tab AggiungiTab;
    public TextField ModelloTextField;
    public TextField TargaTextField;
    public TextField DataTextField;
    public ChoiceBox<String> ProduttoreChoiceBox;
    public TextField PrezzoTextField;



    private final String EMAIL_VALUE = "Admin";
    private final String PASSWORD_VALUE = "Admin";


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
        String insertedEmail = EmailTextField.getText().isEmpty() ? null: EmailTextField.getText();
        String insertedPassword = PasswordTextField.getText().isEmpty() ? null: PasswordTextField.getText();

        //controlla che siano stati compilati tutti i campi
        if (insertedEmail==null || insertedPassword == null){
            alert.setContentText("Devi compilare correttamente i due campi!");
            alert.show();
            return;
        }

        //controlla che siano state inserite le credenziali corrette.
        boolean isEmailCorrect = Objects.equals(EmailTextField.getText(), EMAIL_VALUE);
        boolean isPasswordCorrect = Objects.equals(PasswordTextField.getText(),PASSWORD_VALUE);
        if (isEmailCorrect && isPasswordCorrect) {
            AdminTab.setDisable(false);         //attiva le tab riservate al super-utente
            InitializeAdminTab();
            AggiungiTab.setDisable(false);
            InitializeChoiceBoxes();

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Login effettuato con successo.\nL'area amministrativa è stata sbloccata.");
            alert.show();
        }
        else{
            alert.setContentText("Password e/o Email non corretta. Riprova.");
            alert.show();
            ClearFieldsInLoginPage();       //se le credenziali sono scorrette, pulisce tutti i campi
        }
    }

    /*
    Metodo che gestisce l'inizializzazione della tab riservata al super User
     */
    private void InitializeAdminTab(){
        InitializeTable(ProduttoreColumn, ModelloColumn, TargaColumn, CostoColumn, DataColumn);
        carTable.getSelectionModel().selectedItemProperty().isNotNull().addListener(        // FIXME: 15/03/2022
                observable -> DeleteButton.setDisable(false)
        );
        UpdateTable();      //inserisce le auto disponibili in una tabella
    }

    /*
    Metodo che inizializza una tabella: mappa le colonne in modo che siano legate a un solo parametro della classe AUTO
     */
    static void InitializeTable(TableColumn<Auto, Auto.Produttore> produttoreColumn, TableColumn<Auto, String> modelloColumn, TableColumn<Auto, String> targaColumn, TableColumn<Auto, Float> costoColumn, TableColumn<Auto, LocalDate> dataColumn) {
        produttoreColumn.setCellValueFactory(new PropertyValueFactory<>("Produttore"));
        modelloColumn.setCellValueFactory(new PropertyValueFactory<>("Modello"));
        targaColumn.setCellValueFactory(new PropertyValueFactory<>("Targa"));
        costoColumn.setCellValueFactory(new PropertyValueFactory<>("CostoGiornaliero"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("DataNoleggio"));
    }

    /*
    Metodo per inserire tutte le possibili opzioni di tutti i menu di scelta
     */
    private void InitializeChoiceBoxes(){
        List<String> choices = new ArrayList<>();
        choices.add(Auto.Produttore.FERRARI.toString());
        choices.add(Auto.Produttore.FIAT.toString());
        ProduttoreChoiceBox.setItems(FXCollections.observableList(choices));
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
        Auto carToDelete = carTable.getSelectionModel().getSelectedItem();      // FIXME: 15/03/2022
        AutoManager.Delete(carToDelete);
        alert.setContentText("Eliminazione dell'auto avvenuta con successo.");
        System.out.println(AutoManager.getAutoList());
        alert.show();

        UpdateTable();      //dopo averla eliminata, aggiorna la tabella
    }

    /*
    Metodo che aggiorna la tabella
    TODO: IMPORTANTE, adattare il metodo alla nuova forma di lettura/inserimento dati (ovvero da file)
     */

    public void UpdateTable(){
        ObservableList<Auto> carObservableList = FXCollections.observableList(AutoManager.getAutoList());
        if (!carTable.getItems().isEmpty()) carTable.getItems().clear();
        carTable.setItems(carObservableList);
    }

    /*
    Metodo che dalla pagina admin torna alla pagina del cliente.
    TODO: migliorare

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
}