import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        // take two numbers and perform calculations on them.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter two numbers");

        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        scanner.nextLine();
        String operation = scanner.nextLine();

        int result = 0;

        if (operation.equals("+")) {
            result = add(num1, num2);
        } else if (operation.equals("-")) {
            result = subtract(num1, num2);
        } else if (operation.equals("*")) {
            result = multiply(num1, num2);
        } else if (operation.equals("/")){
            result = divide(num1, num2);
        } else {
            System.out.println("Something went wrong");;
        }

        // int result = add(num1, num2); // Make this a switch statement based on operation selected by user
        System.out.println("Your total is " + result);
        System.out.println("num1: " + num1 + " and num2: " + num2);
        System.out.println("Operation is " + operation);

        scanner.close();
    }

    public static int add(int num1, int num2) {
        return num1 + num2;
    }

    public static int subtract(int num1, int num2) {
        return num1 - num2;
    }

    public static int multiply(int num1, int num2) {
        return num1 * num2;
    }

    public static int divide(int num1, int num2) {
        return num1 / num2;
    }
}
    