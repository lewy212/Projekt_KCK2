module com.example.projekt2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.prefs;
    requires javafx.media;
    requires com.jfoenix;
    opens com.example.projekt2 to javafx.fxml;
    exports com.example.projekt2;
}
