package com.example.eserciziojavafx;

import com.example.eserciziojavafx.model.Person;
import com.example.eserciziojavafx.model.Register;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    @FXML
    TextField fieldNome;
    @FXML
    TextField fieldCognome;
    @FXML
    TextField fieldTelefono;
    @FXML
    Button submitButton;
    @FXML
    Button clearButton;
    @FXML
    Button getAllPeopleButton;
    @FXML
    Button deleteButton;

    @FXML
    private void Submit(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String errorType;
        boolean result;

        //controlla che i campi siano tutti compilati
        if (!fieldNome.getText().isEmpty() && !fieldCognome.getText().isEmpty() && !fieldTelefono.getText().isEmpty()){
            Person personToAdd = new Person(fieldNome.getText(), fieldCognome.getText(), fieldTelefono.getText());
            result = Register.addPerson(personToAdd);   //inserisce nel registro

            //controlla che l'inserimento sia andato a buon fine
            if (result){
                alert.setContentText("L'inserimento della persona nella rubrica è avvenuto con successo.");
                ClearFields();
             }
             else{
                errorType = "è gia presente nella rubrica un contatto con il medesimo numero telefonico.";
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("L'inserimento della persona non è andato a buon fine.\nErrore: "+errorType);
             }
        }

        else {
            errorType="i campi non sono stati compilati correttamente.";
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("L'inserimento della persona non è andato a buon fine.\nErrore: "+errorType);
        }
        alert.show();
    }


    //pulisce tutti i campi
    @FXML
    private void ClearFields(){
        fieldNome.clear();
        fieldCognome.clear();
        fieldTelefono.clear();
    }


    //mostra tutte le persone salvate in rubrica: apre una nuova finestra
    @FXML
    private void getAllPeople() throws IOException {
        Parent part = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Rubrica.fxml")));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);     //la finestra appena creata blocca tutte le altre (non si possono muovere)
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
    }

}
