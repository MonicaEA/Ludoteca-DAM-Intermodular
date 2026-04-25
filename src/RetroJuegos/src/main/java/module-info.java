module com.retrojuegos.retrojuegos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires java.desktop;


    opens com.retrojuegos.retrojuegos to javafx.fxml;
    exports com.retrojuegos.retrojuegos;
    opens com.retrojuegos.retrojuegos.ControllerView to javafx.fxml;
    opens com.retrojuegos.retrojuegos.model to javafx.base;
}