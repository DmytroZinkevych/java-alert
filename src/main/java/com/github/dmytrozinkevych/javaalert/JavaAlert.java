package com.github.dmytrozinkevych.javaalert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JavaAlert {

    private static class ThreadLock {

        public synchronized void suspendThread() {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void resumeThread() {
            notifyAll();
        }
    }

    public static void alert(String message) {
        ThreadLock threadLock = new ThreadLock();
        showMessageWindow(message, threadLock);
        threadLock.suspendThread();
    }

    private static void showMessageWindow(String message, ThreadLock threadLock) {
        //TODO: margins, min and max window sizes
        //TODO: handle newlines in a message
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Java alert");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                threadLock.resumeThread();
                e.getWindow().dispose();
            }
        });

        JPanel panel = new JPanel();

        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        JLabel label = new JLabel(message);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        JButton button = new JButton("OK");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(event -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
        panel.add(button);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
