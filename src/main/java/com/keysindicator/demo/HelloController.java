package com.keysindicator.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Hello button clicked");
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void setWelcomeText(String text) {
        welcomeText.setText(text);
    }
}