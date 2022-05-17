module com.bitmap.bitmapsfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.commons.io;


    opens com.bitmap.bitmapsfx to javafx.fxml, java.desktop;
    exports com.bitmap.bitmapsfx;
}