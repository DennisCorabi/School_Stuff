package com.example.eserciziojavafx;

import com.example.eserciziojavafx.model.Person;
import com.example.eserciziojavafx.model.Register;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.google.gson.Gson;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {

    Register personRegister = new Register();


    @FXML
    TextField fieldNome;
    @FXML
    TextField fieldCognome;
    @FXML
    TextField fieldTelefono;
    @FXML
    TextArea registerArea;
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
        boolean result;
        Person personToAdd = new Person(fieldNome.getText(), fieldCognome.getText(), fieldTelefono.getText());
        result = personRegister.addPerson(personToAdd);
        if (result) {
            registerArea.setText(registerArea.getText() + personToAdd + "\n");
            ClearFields();
        }
    }

    @FXML
    private void ClearFields(){
        fieldNome.clear();
        fieldCognome.clear();
        fieldTelefono.clear();
    }

    @FXML
    private void getAllPeole() throws IOException {

        Gson gson = new Gson();     //da passare alla scena
        for (Person person: personRegister.getRegister()){
            System.out.println(gson.toJson(person));


        Stage newWindow = new Stage();
        newWindow.setTitle("Rubrica");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Rubrica.fxml"));
        newWindow.setScene(new Scene(fxmlLoader.load()));

        newWindow.show();


        }
    }
}
