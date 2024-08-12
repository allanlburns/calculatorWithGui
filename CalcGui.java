import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class CalcGui {
    public static void main(String[] args) {
        CalcGui gui = new CalcGui();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(2, 2));

        JTextArea text = new JTextArea(5, 10);
        text.setEditable(false);
        text.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(text, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout(10, 10));

        JButton zeroButton = new JButton("0");
        JButton oneButton = new JButton("1");
        JButton twoButton = new JButton("2");
        JButton threeButton = new JButton("3");
        JButton fourButton = new JButton("4");
        JButton fiveButton = new JButton("5");
        JButton sixButton = new JButton("6");
        JButton sevenButton = new JButton("7");
        JButton eightButton = new JButton("8");
        JButton nineButton = new JButton("9");
        JButton clearButton = new JButton("C");

        zeroButton.addActionListener(e -> text.append("0"));
        oneButton.addActionListener(e -> text.append("1"));
        twoButton.addActionListener(e -> text.append("2"));
        threeButton.addActionListener(e -> text.append("3"));
        fourButton.addActionListener(e -> text.append("4"));
        fiveButton.addActionListener(e -> text.append("5"));
        sixButton.addActionListener(e -> text.append("6"));
        sevenButton.addActionListener(e -> text.append("7"));
        eightButton.addActionListener(e -> text.append("8"));
        nineButton.addActionListener(e -> text.append("9"));
        clearButton.addActionListener(e -> text.setText(""));

        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        buttonPanel.add(zeroButton);
        buttonPanel.add(oneButton);
        buttonPanel.add(twoButton);
        buttonPanel.add(threeButton);
        buttonPanel.add(fourButton);
        buttonPanel.add(fiveButton);
        buttonPanel.add(sixButton);
        buttonPanel.add(sevenButton);
        buttonPanel.add(eightButton);
        buttonPanel.add(nineButton);
        buttonPanel.add(clearButton);

        // frame.getContentPane().add(BorderLayout.CENTER, topPanel);
        // frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.add(topPanel);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.setSize(700, 600);
        frame.setVisible(true);

    }
}