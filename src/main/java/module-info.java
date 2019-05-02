module com.alucard {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.alucard to javafx.fxml;
    exports com.alucard;
}