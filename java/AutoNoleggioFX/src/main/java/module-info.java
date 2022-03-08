module com.example.autonoleggiofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.autonoleggiofx to javafx.fxml;
    exports com.example.autonoleggiofx;
    exports com.example.autonoleggiofx.Model;
    opens com.example.autonoleggiofx.Model to javafx.fxml;
}