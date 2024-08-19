import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalcGui {
    private Calculator calculator;
    private JTextArea display;

    public CalcGui() {
        calculator = new Calculator();
        go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(2, 2));

        display = new JTextArea(5, 10);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

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
        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton equalsButton = new JButton("=");
        JButton offButton = new JButton("OFF");

        zeroButton.addActionListener(new ButtonClickListener());
        oneButton.addActionListener(new ButtonClickListener());
        twoButton.addActionListener(new ButtonClickListener());
        threeButton.addActionListener(new ButtonClickListener());
        fourButton.addActionListener(new ButtonClickListener());
        fiveButton.addActionListener(new ButtonClickListener());
        sixButton.addActionListener(new ButtonClickListener());
        sevenButton.addActionListener(new ButtonClickListener());
        eightButton.addActionListener(new ButtonClickListener());
        nineButton.addActionListener(new ButtonClickListener());
        clearButton.addActionListener(new ButtonClickListener());
        addButton.addActionListener(new ButtonClickListener());
        subtractButton.addActionListener(new ButtonClickListener());
        equalsButton.addActionListener(new ButtonClickListener());
        offButton.addActionListener(new ButtonClickListener());


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
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(offButton);

        
        frame.add(topPanel);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.setSize(700, 600);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private String currentInput = "";
        private double result = 0;
        private String command = "";
        private String inputs = "";

        @Override
        public void actionPerformed(ActionEvent e) {
            command = e.getActionCommand();
            if (command != "=") {
                inputs += command;
                updateDisplay(inputs);
                System.out.println("inputs: " + inputs);
                System.out.println("inputs: " + inputs);
            } else {
                calculate(inputs);
            }
        }

        private void calculate(String input) {
            result = (double) input.charAt(0);
            for (int i = 0; i < input.length() -1; i++) {
                if ("+-*/".indexOf(input.charAt(i)) != -1) {
                    // if current char is an operator +-*/, switch statement and call corresponding calc method
                    char nextValue = input.charAt(i + 1);
                    switch (input.charAt(i)) {
                        case '+':
                            result = calculator.add(result, nextValue);
                            break;
                        case '-':
                            result = calculator.subtract(result, nextValue);
                            break;
                        case '*':
                            result = calculator.multiply(result, nextValue);
                            break;
                        case '/':
                            result = calculator.divide(result, nextValue);
                            break;
                    }
                }
                
            }

            updateDisplay(Double.toString(result));
        }


        private void clear() {
            result = 0;
            currentInput = "";
        }

        private void updateDisplay(String input) {
            display.setText(input);
        }
    }

    public static void main(String[] args) {
        CalcGui gui = new CalcGui();
        // SwingUtilities.invokeLater(() -> new CalcGui());
    }
}