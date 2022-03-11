module com.example.eserciziojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.eserciziojavafx to javafx.fxml;
    exports com.example.eserciziojavafx;
}