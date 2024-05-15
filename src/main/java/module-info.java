module com.example.sae_zeldalike {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires org.json;

    opens com.example.sae_zeldalike to javafx.fxml;
    exports com.example.sae_zeldalike;
    exports com.example.sae_zeldalike.Vue;
    opens com.example.sae_zeldalike.Vue to javafx.fxml;
}