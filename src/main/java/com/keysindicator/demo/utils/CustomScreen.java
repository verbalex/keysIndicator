package com.keysindicator.demo.utils;

import org.jnativehook.GlobalScreen;
import org.jnativehook.dispatcher.SwingDispatchService;
import org.jnativehook.keyboard.SwingKeyAdapter;
import org.jnativehook.mouse.SwingMouseAdapter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomScreen extends GlobalScreen {

    // Static blocks are run when the class is loaded.
    static {
        // Add adaptors to the listener to convert the events.
        addNativeKeyListener(new KeyAdapter());
        addNativeMouseListener(new MouseAdapter());

        setEventDispatcher(new SwingDispatchService());
    }

    private static class KeyAdapter extends SwingKeyAdapter {
        public void keyTyped(KeyEvent keyEvent) {
            KeyListener[] listeners = eventListeners.getListeners(KeyListener.class);

            for (int i = 0; i < listeners.length; i++) {
                listeners[i].keyTyped(keyEvent);
            }
            System.out.println(keyEvent);
        }

        public void keyPressed(KeyEvent keyEvent) {
            KeyListener[] listeners = eventListeners.getListeners(KeyListener.class);

            for (int i = 0; i < listeners.length; i++) {
                listeners[i].keyPressed(keyEvent);
            }
            System.out.println(keyEvent);
        }

        public void keyReleased(KeyEvent keyEvent) {
            KeyListener[] listeners = eventListeners.getListeners(KeyListener.class);

            for (int i = 0; i < listeners.length; i++) {
                listeners[i].keyReleased(keyEvent);
            }
            System.out.println(keyEvent);
        }
    }

    private static class MouseAdapter extends SwingMouseAdapter {
        public void mouseClicked(MouseEvent mouseEvent) {
            MouseListener[] listeners = eventListeners.getListeners(MouseListener.class);

            for (int i = 0; i < listeners.length; i++) {
                listeners[i].mouseClicked(mouseEvent);
            }
            System.out.println(mouseEvent);
        }

        public void mousePressed(MouseEvent mouseEvent) {
            MouseListener[] listeners = eventListeners.getListeners(MouseListener.class);

            for (int i = 0; i < listeners.length; i++) {
                listeners[i].mousePressed(mouseEvent);
            }
            System.out.println(mouseEvent);
        }

        public void mouseReleased(MouseEvent mouseEvent) {
            MouseListener[] listeners = eventListeners.getListeners(MouseListener.class);

            for (int i = 0; i < listeners.length; i++) {
                listeners[i].mouseReleased(mouseEvent);
            }
            System.out.println(mouseEvent);
        }

        public void mouseEntered(MouseEvent mouseEvent) {
            MouseListener[] listeners = eventListeners.getListeners(MouseListener.class);

            for (int i = 0; i < listeners.length; i++) {
                listeners[i].mouseEntered(mouseEvent);
            }
            System.out.println(mouseEvent);
        }

        public void mouseExited(MouseEvent mouseEvent) {
            MouseListener[] listeners = eventListeners.getListeners(MouseListener.class);

            for (int i = 0; i < listeners.length; i++) {
                listeners[i].mouseExited(mouseEvent);
            }
            System.out.println(mouseEvent);
        }
    }

    public static void addSwingKeyListener(KeyListener listener) {
        if (listener != null) {
            eventListeners.add(KeyListener.class, listener);
        }
    }

    public static void removeKeyListener(KeyListener listener) {
        if (listener != null) {
            eventListeners.remove(KeyListener.class, listener);
        }
    }

    public static void addMouseListener(MouseListener listener) {
        if (listener != null) {
            eventListeners.add(MouseListener.class, listener);
        }
    }

    public static void removeMouseListener(MouseListener listener) {
        if (listener != null) {
            eventListeners.remove(MouseListener.class, listener);
        }
    }
}