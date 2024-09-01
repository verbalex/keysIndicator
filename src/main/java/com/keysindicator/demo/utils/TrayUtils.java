package com.keysindicator.demo.utils;

import com.keysindicator.demo.HelloApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.function.Function;

public class TrayUtils {

    Stage stage;


    public TrayUtils(Stage stage) {
        this.stage = stage;
        //Перевіряємо підтримку трею
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        URL url = HelloApplication.class.getResource("/images/icon.png");
        Image image = Toolkit.getDefaultToolkit().getImage(url);
        final TrayIcon trayIcon = new TrayIcon(image);
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a pop-up menu components
        MenuItem aboutItem = new MenuItem("About");
//        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
//        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
//        Menu displayMenu = new Menu("Display");
//        MenuItem errorItem = new MenuItem("Error");
//        MenuItem warningItem = new MenuItem("Warning");
//        MenuItem infoItem = new MenuItem("Info");
//        MenuItem noneItem = new MenuItem("None");
        MenuItem showItem = new MenuItem("Show");
        MenuItem exitItem = new MenuItem("Exit");

        exitItem.addActionListener(a ->{
            System.exit(0);
        });

        showItem.addActionListener(a -> {
            showWindow(stage);
        });


        //Add components to pop-up menu
        popup.add(aboutItem);
        popup.addSeparator();
//        popup.add(cb1);
//        popup.add(cb2);
//        popup.addSeparator();
//        popup.add(displayMenu);
//        displayMenu.add(errorItem);
//        displayMenu.add(warningItem);
//        displayMenu.add(infoItem);
//        displayMenu.add(noneItem);
        popup.add(showItem);
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        trayIcon.addActionListener(a -> {
            showWindow(stage);
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }

    private void showWindow(Stage stage) {
        if (stage.isIconified()){
            Platform.runLater(()-> stage.setIconified(false));
        } else {
            Platform.runLater(stage::show);
        }
    }
}
