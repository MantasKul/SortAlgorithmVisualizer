module com.example.sortalgorithmvisualiser {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.sortalgorithmvisualiser to javafx.fxml;
    exports com.sortalgorithmvisualiser;
}