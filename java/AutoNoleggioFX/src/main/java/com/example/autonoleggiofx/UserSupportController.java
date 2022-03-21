package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Auto;
import com.example.autonoleggiofx.Model.UserManager;
import javafx.scene.control.*;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class UserSupportController {

    public TextField AdminKeyTextField;
    public Tab RecuperoCredenzialiTab;
    public Button AccessButton;

    //RECUPERO PASSWORD
    public TextField RecuperoPasswordTextField;

    //MODIFICA PASSWORD
    public TextField UsernameTextField;
    public PasswordField CurrentPasswordTextField;
    public PasswordField NewPasswordTextField;
    public TextField CurrentUsernameTextField;
    public PasswordField CurrentPasswordTextField2;
    public TextField NewUsernameTextField;


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

    public void RecuperaPassword(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String userName = RecuperoPasswordTextField.getText();
        if (RecuperoPasswordTextField.getText().isEmpty()) {
            alert.setContentText("Inserisci un utente prima di continuare.");
            alert.show();
        }

        String recoveredPassword = UserManager.RecuperaPassword(userName.trim());
        if (recoveredPassword==null) alert.setContentText("Non è stato trovato alcun utente con questo username: "+userName);
        else{
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("La password associata all'utente \""+userName+"\" è "+recoveredPassword);
        }
        alert.show();

    }

    public void ModificaPassword(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String userName = UsernameTextField.getText().trim();
        String currentPassword = CurrentPasswordTextField.getText();
        String newPassword = NewPasswordTextField.getText();

        if (currentPassword.equals(newPassword)) {
            alert.setContentText("La password nuova non può essere identica alla precedente.");
        }
        else if (UserManager.ModificaPassword(userName , currentPassword, newPassword)){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Modifica della password avvenuta con successo.");
        }
        else alert.setContentText("Password inserita non valida. Per modificare la password di un utente, prima inserire quella corrente.");
        alert.show();
    }

    public void ModificaUsername(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String currentUsername = CurrentUsernameTextField.getText().trim();
        String newUsername = NewUsernameTextField.getText().trim();
        String currentPassword = CurrentPasswordTextField2.getText();

        if (currentUsername.equals(newUsername)){
            alert.setContentText("Il nuovo nome utente non può essere identico al precedente.");
        }
        else if (UserManager.ModificaUsername(currentUsername, currentPassword, newUsername)){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Modifica del nome utente avvenuta con successo.");
        }
        else alert.setContentText("Password inserita non valida. Per modificare il nome di un utente, prima inserire la password associata.");
        alert.show();
    }

    public void InitalizeUserSupport(){
        AccessButton.setDisable(true);
        RecuperoCredenzialiTab.setDisable(false);

    }
}
