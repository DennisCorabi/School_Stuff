module com.example.badons {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.badons to javafx.fxml;
    exports com.example.badons;
}