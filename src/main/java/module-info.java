module com.itakademija.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.itakademija.demo to javafx.fxml;
    exports com.itakademija.demo;
}