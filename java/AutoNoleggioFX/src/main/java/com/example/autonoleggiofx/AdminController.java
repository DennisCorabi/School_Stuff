package com.example.autonoleggiofx;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import java.util.Objects;

public class AdminController {

    public TextField PasswordTextField;
    public TextField EmailTextField;
    public Tab AdminTab;

    private final String EMAIL_VALUE = "Admin";
    private final String PASSWORD_VALUE = "Admin";

    public void Login(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String insertedEmail = EmailTextField.getText();
        String insertedPassword = PasswordTextField.getText();

        if ()
        boolean isEmailCorrect = Objects.equals(EmailTextField.getText(), EMAIL_VALUE);
        boolean isPasswordCorrect = Objects.equals(PasswordTextField.getText(),PASSWORD_VALUE);

        if (isEmailCorrect && isPasswordCorrect) AdminTab.setDisable(false);
        else{
        }
    }
}