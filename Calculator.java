public class Calculator {
    // In this version we get the numbers to calculate from gui

    public double add(double num1, double num2) {
        return num1 += num2;
    }

    public double subtract(double num1, double num2) {
        return num1 -= num2;
    }

    public double multiply(double num1, double num2) {
        return num1 *= num2;
    }

    public double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Error!!! Division by zero");
        } else {
            return num1 /= num2;
        }
    }
}
    