module com.example.sae_zeldalike {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.sae_zeldalike to javafx.fxml;
    exports com.example.sae_zeldalike;
}