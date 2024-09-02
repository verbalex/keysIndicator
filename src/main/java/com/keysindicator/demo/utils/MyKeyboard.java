package com.keysindicator.demo.utils;

import com.keysindicator.demo.KeysIndicatorApplication;
import com.keysindicator.demo.MainController;
import com.keysindicator.demo.KeysController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MyKeyboard implements NativeKeyListener {

    MainController mainController;
    Set<Integer> keySetPressed = new HashSet<>();
    Set<Integer> keySetReleased = new HashSet<>();

    public MyKeyboard() {
        // Now press any keys and look at the output
    }

    public MyKeyboard(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        keySetPressed.add(e.getKeyCode());
        //showWindowWithText(NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
        System.out.println("Typed: " + NativeKeyEvent.getKeyText(nke.getKeyCode()));
        //showWindowWithText(NativeKeyEvent.getKeyText(nke.getKeyCode()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        System.out.println("Released: " + NativeKeyEvent.getKeyText(nke.getKeyCode()));
        keySetReleased.add(nke.getKeyCode());
        if (keySetReleased.containsAll(keySetPressed)) {

            Set<String> keySetReleasedString = keySetReleased.stream().
                    sorted(Integer::compareTo).
                    map(NativeKeyEvent::getKeyText).
                    collect(Collectors.toSet());

            showWindowWithText(String.join("+", keySetReleasedString));
            //showWindowWithTextUsingJFrame(String.join("+", keySetReleasedString));
            keySetReleased.clear();
            keySetPressed.clear();
        }
    }

    private void showWindowWithText(String text) {
        Platform.runLater(()-> {
            Property property = new Property();
            FXMLLoader fxmlLoader = new FXMLLoader(KeysIndicatorApplication.class.getResource("keys-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), Math.max(4,(text.length())) * 25, 60);
                scene.setFill(Color.rgb(255,255,255, 0));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            Stage stage = new Stage();
            stage.setTitle(text);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setAlwaysOnTop(true);
            stage.setX(property.getKeysTextX());
            stage.setY(property.getKeysTextY());
            stage.show();
            KeysController keysController = fxmlLoader.getController();
            try {
                keysController.initialize(text, stage);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void showWindowWithTextUsingJFrame(String text) {
        JFrame myFrame = new JFrame("Frame Title");

        myFrame.setLocation(new Point(100, 100));
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        myFrame.getContentPane().add(mainPanel);
        mainPanel.add(new JButton(text), BorderLayout.CENTER);
        myFrame.setResizable(false);
        myFrame.setAlwaysOnTop(true);
        myFrame.pack();
        myFrame.setLocationByPlatform(true);
        myFrame.setVisible(true);

    }



}
