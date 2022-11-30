module com.adria.crud.demo.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.adria.crud.demo.demo to javafx.fxml;
    exports com.adria.crud.demo.demo;
}