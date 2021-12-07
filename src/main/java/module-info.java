module com.example.task1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires mail;
    requires activation;


    opens com.example.task1 to javafx.fxml;
    exports com.example.task1;
}