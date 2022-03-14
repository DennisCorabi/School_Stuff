package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Auto;
import com.example.autonoleggiofx.Model.AutoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Objects;

public class AdminController {

    public TextField PasswordTextField;
    public TextField EmailTextField;
    public Tab AdminTab;

    private final String EMAIL_VALUE = "Admin";
    private final String PASSWORD_VALUE = "Admin";
    public Button DeleteButton;

    @FXML
    TableView<Auto> carTable;

    public TableColumn<Auto, Auto.Produttore> ProduttoreColumn;
    public TableColumn<Auto, String> ModelloColumn;
    public TableColumn<Auto, String> TargaColumn;
    public TableColumn<Auto, Float> CostoColumn;
    public TableColumn<Auto, LocalDate> DataColumn;


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
            AdminTab.setDisable(false);
            InitializeAdminTab();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Login effettuato con successo.\nL'area amministrativa è stata sbloccata.");
            alert.show();
        }
        else{
            alert.setContentText("Password e/o Email non corretta. Riprova.");
            alert.show();
            ClearFieldsInLoginPage();
        }
    }

    private void InitializeAdminTab(){
        InitializeTable(ProduttoreColumn, ModelloColumn, TargaColumn, CostoColumn, DataColumn);
        carTable.getSelectionModel().selectedItemProperty().isNotNull().addListener(
                observable -> DeleteButton.setDisable(false)
        );
        UpdateTable();
    }

    static void InitializeTable(TableColumn<Auto, Auto.Produttore> produttoreColumn, TableColumn<Auto, String> modelloColumn, TableColumn<Auto, String> targaColumn, TableColumn<Auto, Float> costoColumn, TableColumn<Auto, LocalDate> dataColumn) {
        produttoreColumn.setCellValueFactory(new PropertyValueFactory<>("Produttore"));
        modelloColumn.setCellValueFactory(new PropertyValueFactory<>("Modello"));
        targaColumn.setCellValueFactory(new PropertyValueFactory<>("Targa"));
        costoColumn.setCellValueFactory(new PropertyValueFactory<>("CostoGiornaliero"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("DataNoleggio"));
    }

    public void DeleteCar(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Auto carToDelete = carTable.getSelectionModel().getSelectedItem();
        AutoManager.Delete(carToDelete);
        alert.setContentText("Eliminazione dell'auto avvenuta con successo.");
        System.out.println(AutoManager.getAutoList());
        alert.show();

        UpdateTable();
    }

    public void UpdateTable(){
        ObservableList<Auto> carObservableList = FXCollections.observableList(AutoManager.getAutoList());
        if (!carTable.getItems().isEmpty()) carTable.getItems().clear();
        carTable.setItems(carObservableList);
    }
    public void ClearFieldsInLoginPage(){
        EmailTextField.clear();
        PasswordTextField.clear();
    }
}