module org.untitled.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.untitled.weatherapp to javafx.fxml;
    exports org.untitled.weatherapp;
}