module com.korn.lakes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;


    opens com.korn.lakes to javafx.fxml;
    exports com.korn.lakes;
    exports com.korn.lakes.view;
    opens com.korn.lakes.view to javafx.fxml;
    exports com.korn.lakes.controller;
    opens com.korn.lakes.controller to javafx.fxml;
}