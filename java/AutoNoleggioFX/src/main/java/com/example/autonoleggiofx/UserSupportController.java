package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Admin;
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
            RecuperoPasswordTextField.clear();
        }
        alert.show();

    }

    /*
    Metodo che permette di modificare la password di un amministratore
     */
    public void ModificaPassword(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            String userName = (!UsernameTextField.getText().isEmpty()) ? UsernameTextField.getText().trim() : null;
            String newPassword = (!NewPasswordTextField.getText().isEmpty()) ? NewPasswordTextField.getText() : null;
            String currentPassword = (!CurrentPasswordTextField.getText().isEmpty()) ? CurrentPasswordTextField.getText() : null;
            Admin admin = new Admin(userName, currentPassword);

            if (userName == null || newPassword == null || currentPassword == null) throw new NullPointerException();
            if (UserManager.ModificaPassword(admin, newPassword)) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Modifica della password avvenuta con successo.");
            }
            else alert.setContentText("Non è stato trovato un utente con le credenziali inserite, riprovare.");

        }catch (NullPointerException ex){
            alert.setContentText("Compila correttamente tutti i campi prima di continuare.");
        }
        alert.show();
        ClearAllTextFields();
    }

    /*
    Metodo che permette di modificare il nome utente di un amministratore.
     */
    public void ModificaUsername(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            String currentUsername = (!CurrentUsernameTextField.getText().isEmpty()) ? CurrentUsernameTextField.getText().trim() : null;
            String newUsername = (!NewUsernameTextField.getText().isEmpty()) ? NewUsernameTextField.getText().trim() : null;
            String currentPassword = (!CurrentPasswordTextField2.getText().isEmpty()) ? CurrentPasswordTextField2.getText().trim() : null;
            Admin admin = new Admin(currentUsername, currentPassword);

            //controlla che i campi siano stati compilati correttamente
            if (currentPassword == null || currentUsername == null || newUsername == null) throw new NullPointerException();
            if (UserManager.ModificaUsername(admin, newUsername)) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Modifica del nome utente avvenuta con successo.");
            }
            else alert.setContentText("Non è stato trovato un utente con le credenziali inserite, riprovare.");

        }catch (NullPointerException ex){
            alert.setContentText("Compila correttamente tutti i campi prima di continuare.");
        }
        alert.show();
        ClearAllTextFields();
    }

    public void ClearAllTextFields(){
        CurrentPasswordTextField.clear();
        CurrentUsernameTextField.clear();
        CurrentPasswordTextField2.clear();
        NewPasswordTextField.clear();
        NewUsernameTextField.clear();
        UsernameTextField.clear();
        RecuperoPasswordTextField.clear();

    }

    public void InitalizeUserSupport(){
        AccessButton.setDisable(true);
        RecuperoCredenzialiTab.setDisable(false);

    }
}
