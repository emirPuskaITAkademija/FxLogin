module com.itakademija.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.itakademija.demo to javafx.fxml;
    opens com.itakademija.demo.dao.person to javafx.base;
    exports com.itakademija.demo;
}