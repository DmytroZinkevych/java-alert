package io.github.dmytrozinkevych.javaalert;

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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Java Alert");
        frame.setMinimumSize(new Dimension(200, 120));
        frame.setPreferredSize(new Dimension(400, 250));
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

        JTextArea textArea = new JTextArea(message);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, textArea.getFont().getSize()));
        textArea.setMargin(new Insets(5, 10, 5, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        JButton button = new JButton("OK");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(event -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));

        panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0,5)));

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JavaAlert() { }
}
