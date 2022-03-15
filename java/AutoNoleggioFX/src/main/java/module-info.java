module com.example.autonoleggiofx {
    requires javafx.controls;
    requires com.google.gson;
    requires javafx.fxml;


    exports com.example.autonoleggiofx;
    exports com.example.autonoleggiofx.Model;
    opens com.example.autonoleggiofx.Model to javafx.fxml, com.google.gson;
    opens  com.example.autonoleggiofx to javafx.fxml;
}