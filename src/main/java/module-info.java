module com.keysindicator.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires jnativehook;

    opens com.keysindicator.demo to javafx.fxml;
    exports com.keysindicator.demo;
}