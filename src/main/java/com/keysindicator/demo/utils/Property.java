package com.keysindicator.demo.utils;

import com.keysindicator.demo.KeysIndicatorApplication;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Property {

    private final Properties property = new Properties();
    URL propertyPath;
    private int keysTextX;
    private int keysTextY;
    private String labelColor;
    private float labelOpacity;


    public Property() {
        loadConfig();
    }

    private void loadConfig() {
        try {
            propertyPath = KeysIndicatorApplication.class.getResource("/config/config.properties");
            FileInputStream fis = new FileInputStream(propertyPath.getPath());
            property.load(fis);

            keysTextX = Integer.parseInt(property.getProperty("keysText.x", "0"));
            keysTextY = Integer.parseInt(property.getProperty("keysText.y", "0"));
            labelColor = property.getProperty("label.color", "#000000");
            labelOpacity = Float.parseFloat(property.getProperty("label.opacity", "1"));

            System.out.println("X: " + keysTextX
                    + ", y: " + keysTextY);

        } catch (IOException e) {
            System.err.println("File not found");
        }
    }

    public void saveConfig() throws IOException {
        property.setProperty("keysText.x", String.valueOf(keysTextX));
        property.setProperty("keysText.y", String.valueOf(keysTextY));
        property.setProperty("label.color", labelColor);
        property.setProperty("label.opacity", String.valueOf(labelOpacity));
        property.store(new FileOutputStream(propertyPath.getPath()), null);
    }

    public int getKeysTextX() {
        return keysTextX;
    }

    public void setKeysTextX(int keysTextX) {
        this.keysTextX = keysTextX;
    }

    public int getKeysTextY() {
        return keysTextY;
    }

    public void setKeysTextY(int keysTextY) {
        this.keysTextY = keysTextY;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public float getLabelOpacity() {
        return labelOpacity;
    }

    public void setLabelOpacity(float labelOpacity) {
        this.labelOpacity = labelOpacity;
    }
}
