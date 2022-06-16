package com.github.dmytrozinkevych.javaalert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JavaAlert {
    public static void alert(String message) {
        showMessageWindow(message);
        System.err.println("===================== Done =====================");
    }

    private static void showMessageWindow(String message) {
        //TODO: margins, min and max window sizes
        //TODO: handle newlines in a message
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Java alert");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        JLabel label = new JLabel(message);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        JButton button = new JButton("OK");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(JavaAlert::continueExecution);
        panel.add(button);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void continueExecution(ActionEvent e) {
        System.err.println("===================== Button clicked =====================");
        //TODO: implement
    }
}
