package com.keysindicator.demo;

import com.keysindicator.demo.utils.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField keysTextXTextField;

    @FXML
    private TextField keysTextYTextField;

    @FXML
    private ColorPicker keysLabelColorPicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        property = new Property();
        this.keysTextXTextField.setText(String.valueOf(property.getKeysTextX()));
        this.keysTextYTextField.setText(String.valueOf(property.getKeysTextY()));
        this.keysLabelColorPicker.setValue(Color.web(property.getLabelColor()));
    }

    @FXML
    protected void onSaveButtonClick() throws IOException {
        property.setKeysTextX(Integer.parseInt(keysTextXTextField.getText()));
        property.setKeysTextY(Integer.parseInt(keysTextYTextField.getText()));
        property.setLabelColor(keysLabelColorPicker.getValue().toString());
        property.saveConfig();
    }

    Property property;

    public void setWelcomeText(String text) {
        welcomeText.setText(text);
    }

}