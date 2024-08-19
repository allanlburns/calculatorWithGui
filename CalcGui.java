import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalcGui {
    private JTextField display;
    private StringBuilder currentInput;
    private Calculator calculator;

    public CalcGui() {
        currentInput = new StringBuilder();
        calculator = new Calculator();
        go();
    }

    private void go() {
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 24));

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "OFF"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(display, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "=":
                    calculateResult();
                    break;
                case "C":
                    clearInput();
                    break;
                case "OFF":
                    System.exit(0);
                    break;
                default:
                    appendInput(command);
                    break;
            }
            updateDisplay();
        }
    }

    private void appendInput(String input) {
        currentInput.append(input);
    }

    private void clearInput() {
        currentInput.setLength(0);
    }

    private void calculateResult() {
        try {
            ArrayList<String> tokens = tokenize(currentInput.toString());
            double result = evaluateExpression(tokens);
            currentInput.setLength(0);
            currentInput.append(result);
        } catch (Exception ex) {
            currentInput.setLength(0);
            currentInput.append("Error");
        }
    }

    private ArrayList<String> tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();
        
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                currentNumber.append(c);
            } else if ("+-*/".indexOf(c) != -1) {
                if (currentNumber.length() > 0) {
                    tokens.add(currentNumber.toString());
                    currentNumber.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }
        
        if (currentNumber.length() > 0) {
            tokens.add(currentNumber.toString());
        }
        
        return tokens;
    }

    private double evaluateExpression(ArrayList<String> tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("Empty expression");
        }
        
        double result = Double.parseDouble(tokens.get(0));
        for (int i = 1; i < tokens.size(); i += 2) {
            String operator = tokens.get(i);
            double operand = Double.parseDouble(tokens.get(i + 1));
            switch (operator) {
                case "+":
                    result = calculator.add(result, operand);
                    break;
                case "-":
                    result = calculator.subtract(result, operand);
                    break;
                case "*":
                    result = calculator.multiply(result, operand);
                    break;
                case "/":
                    result = calculator.divide(result, operand);
                    break;
            }
        }
        return result;
    }

    private void updateDisplay() {
        display.setText(currentInput.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalcGui::new);
    }
}