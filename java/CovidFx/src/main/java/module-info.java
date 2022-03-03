module com.example.covidfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.example.covidfx to javafx.fxml;
    exports com.example.covidfx;
    exports com.example.covidfx.Model;
    opens com.example.covidfx.Model to javafx.fxml;
}