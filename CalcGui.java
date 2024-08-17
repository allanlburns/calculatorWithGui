import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        private StringBuilder currentInput = new StringBuilder();
        private double result = 0;
        private String lastOperator = "";
        private boolean startNewInput = true;
        // To make list of operands variable, I'll need to implement variable arguments, loop through, and initialize operand vars
        // Then I'll need to change the boolean to "isFinalOperand"

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if ("0123456789.".contains(command)) {
                // If pressed button is a number or decimal
                if (startNewInput) {
                    // if the operator + - * / hasn't been clicked. Have to change this for variable args.
                    currentInput = new StringBuilder(command);
                    startNewInput = false;
                } else {
                    // for variable num of operands, I'll probably need a function to increment operand
                    currentInput.append(command);
                }
            } else if ("+-*/".contains(command)) {
                if (!currentInput.isEmpty()) {
                    calculate();
                    lastOperator = command;
                    startNewInput = true;
                }
            } else if (command.equals("=")) {
                calculate();
                lastOperator = "";
                startNewInput = true;
            // else if for other buttons will go here
            } else if (command.equals("C")) {
                clear();
            }

            updateDisplay();
        }

        private void calculate() {
            if (!currentInput.toString().isEmpty()) {
                double inputValue = Double.parseDouble(currentInput.toString());
                if (lastOperator.isEmpty()) {
                    result = inputValue;
                } else {
                    switch (lastOperator) {
                        case "+":
                            result = calculator.add(result, inputValue);
                            break;
                        case "-":
                            result = calculator.subtract(result, inputValue);
                            break;
                        case "*":
                            result = calculator.multiply(result, inputValue);
                            break;
                        case "/":
                            result = calculator.divide(result, inputValue);
                            break;
                        default:
                            result = inputValue;
                }
                }
                currentInput = new StringBuilder();
            }
        }

        private void clear() {
            result = 0;
            currentInput = new StringBuilder();
            lastOperator = "";
            startNewInput = true;
        }

        private void updateDisplay() {
            if (currentInput.length() > 0) {
                display.setText(currentInput.toString());
            } else if (!lastOperator.isEmpty()) {
                display.setText(String.format("%.2f %s", result, lastOperator));
            } else {
                display.setText(String.format("%.2f", result));
            }
        }
    }

    public static void main(String[] args) {
        CalcGui gui = new CalcGui();
        // SwingUtilities.invokeLater(() -> new CalcGui());
    }
}