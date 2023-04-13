module com.example.ticktaktoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ticktaktoe to javafx.fxml;
    exports com.example.ticktaktoe;
}