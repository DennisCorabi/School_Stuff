package com.example.demofex;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProvaController {

    public TextField risultato;
    public Button uguale;

    @FXML
    protected void calcola(){
        risultato.setText("ciao");
    }
}
