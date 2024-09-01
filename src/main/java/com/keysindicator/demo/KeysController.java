package com.keysindicator.demo;

import com.keysindicator.demo.utils.Property;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class KeysController {
    @FXML
    private Label keysText;

    public KeysController() throws InterruptedException {
    }

    public void initialize(String keysText, Stage stage) throws InterruptedException {
        System.out.println("KeysController initialized");
        Property property = new Property();
        this.keysText.setTextFill(Color.web(property.getLabelColor(), property.getLabelOpacity()));
        this.keysText.setFont(Font.font(24));
        this.keysText.setText(keysText);
        this.keysText.setBackground(Background.fill(Color.web("#ffffff", 0.5)));
        this.keysText.autosize();

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(()->{stage.close();});
        }).start();

    }

    public void setKeysText(String text) {
        keysText.setText(text);
    }
}