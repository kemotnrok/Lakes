module com.korn.lakes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.korn.lakes to javafx.fxml;
    exports com.korn.lakes;
    exports com.korn.lakes.view;
    opens com.korn.lakes.view to javafx.fxml;
}