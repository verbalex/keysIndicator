package com.keysindicator.demo.utils;

import com.keysindicator.demo.KeysIndicatorApplication;
import com.keysindicator.demo.MainController;
import com.keysindicator.demo.KeysController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class MyKeyboard implements NativeKeyListener {

    MainController mainController;
    private Stage primaryStage;
    Set<Integer> keySetPressed = new HashSet<>();
    Set<Integer> keySetReleased = new HashSet<>();
    LinkedList<Integer> keysPressedOrder = new LinkedList<>();

    public MyKeyboard() {
        // Now press any keys and look at the output
    }

    public MyKeyboard(MainController mainController, Stage primaryStage) {
        this.mainController = mainController;
        this.primaryStage = primaryStage;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        keySetPressed.add(e.getKeyCode());
        keysPressedOrder.add(e.getKeyCode());
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

            LinkedList<String> keySetReleasedString = new LinkedList<>();
            keysPressedOrder.forEach(k -> {keySetReleasedString.add(NativeKeyEvent.getKeyText(k));});
            showWindowWithText(String.join("+", keySetReleasedString));
            //showWindowWithTextUsingJFrame(String.join("+", keySetReleasedString));
            keySetReleased.clear();
            keySetPressed.clear();
            keysPressedOrder.clear();
        }
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

    private void showWindowWithText(String text) {
        Platform.runLater(() -> {
            Property property = new Property();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            dialog.initStyle(StageStyle.TRANSPARENT);
            dialog.setAlwaysOnTop(true);
            dialog.setX(property.getKeysTextX());
            dialog.setY(property.getKeysTextY());

            FXMLLoader fxmlLoader = new FXMLLoader(KeysIndicatorApplication.class.getResource("keys-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), Math.max(4,(text.length())) * 30, 60);
                scene.setFill(Color.rgb(255,255,255, 0));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            dialog.setScene(scene);
            dialog.show();
            KeysController keysController = fxmlLoader.getController();
            try {
                keysController.initialize(text, dialog);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }



}
