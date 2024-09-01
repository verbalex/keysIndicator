package com.keysindicator.demo.utils;

import com.keysindicator.demo.HelloApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Property {

    private final Properties property = new Properties();
    private int keysTextX;
    private int keysTextY;
    private String labelColor;
    private float labelOpacity;


    public Property() {
        loadConfig();
    }

    public void loadConfig() {
        try {
            URL propertyPath = HelloApplication.class.getResource("/config/config.properties");
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

    public int getKeysTextX() {
        return keysTextX;
    }

    public int getKeysTextY() {
        return keysTextY;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public float getLabelOpacity() {
        return labelOpacity;
    }

}
