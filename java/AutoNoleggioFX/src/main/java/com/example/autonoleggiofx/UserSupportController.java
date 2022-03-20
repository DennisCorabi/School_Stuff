package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.UserManager;
import javafx.scene.control.*;

public class UserSupportController {

    public TextField AdminKeyTextField;
    public Tab RecuperoCredenzialiTab;
    public Button AccessButton;


    public void CheckAdminKey(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login effettuato con successo.\nL'area di recupero credenziali è stata sbloccata.");
        String AdminKey = AdminKeyTextField.getText();
        if (AdminKey.equals(UserManager.getAdminKey())) InitalizeUserSupport();
        else{
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("La parola chiave inserita è sbagliata");
        }
        alert.show();
    }

    public void InitalizeUserSupport(){
        AccessButton.setDisable(true);
        RecuperoCredenzialiTab.setDisable(false);

    }
}
