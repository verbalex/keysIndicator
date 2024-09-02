package com.keysindicator.demo;

import com.keysindicator.demo.utils.MyKeyboard;
import com.keysindicator.demo.utils.Property;
import com.keysindicator.demo.utils.TrayUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.swing.*;
import java.io.IOException;

public class KeysIndicatorApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        new Property();
        Platform.setImplicitExit(false);
        FXMLLoader fxmlLoader = new FXMLLoader(KeysIndicatorApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        new TrayUtils(stage);
        MainController mainController = fxmlLoader.getController();

        SwingUtilities.invokeLater(() -> {
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());
                System.exit(1);
            }
            GlobalScreen.addNativeKeyListener(new MyKeyboard(mainController));
        });

    }

    public static void main(String[] args) {
        launch();
    }


}