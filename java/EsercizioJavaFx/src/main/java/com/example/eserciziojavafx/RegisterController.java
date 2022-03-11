package com.example.eserciziojavafx;

import com.example.eserciziojavafx.model.Person;
import com.example.eserciziojavafx.model.Register;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.Objects;


public class RegisterController {

    @FXML
    TextArea registerTextArea;
    @FXML
    Button showContactsButton;
    @FXML
    Button searchButton;
    @FXML
    TextField searchField;

    @FXML
    TextField deleteField;

    @FXML
    Button deletePersonButton;


    //visualizza tutti i contatti salvati in rubrica
    @FXML
    public void ShowContacts(){
        if (!registerTextArea.getText().isEmpty()) registerTextArea.setText("");    //se la textArea non è vuota, cancella tutto.

        for (Person person: Register.getRegister())
            registerTextArea.setText(registerTextArea.getText()+person+"\n");

        registerTextArea.setEditable(false);
    }


    //mostra solamente una persona, individuata tramite l'inserimento del suo numero telefonico
    @FXML
    public void searchPerson(){
        Alert error = new Alert(Alert.AlertType.ERROR);
        String telephoneNumber = searchField.getText();
        for (Person person: Register.register) {
            if (Objects.equals(telephoneNumber, person.getCellNumber())) {
                registerTextArea.setText(person.toString());
                return;
            }
        }
        error.setContentText("Non sono stati trovati contatti aventi il numero telefonico digitato.");
        error.show();
    }


    //elimina la persona associata al numero telefonico inserito nel campo.
    @FXML
    private void DeletePerson(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        boolean result;
        String errorType;
        result = Register.removePerson(deleteField.getText());
        if (result){
            alert.setContentText("L'eliminazione dell'utente è avventua con successo.");
            ShowContacts();
        }
        else{
            alert.setAlertType(Alert.AlertType.ERROR);
            errorType = "Non è presente nella rubrica un utente con queste informazioni.";
            alert.setContentText("L'eliminazione della persona non è andato a buon fine.\nErrore: "+errorType);
        }
        alert.show();

    }

}
