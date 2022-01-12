module com.example.demofex {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demofex to javafx.fxml;
    exports com.example.demofex;
}