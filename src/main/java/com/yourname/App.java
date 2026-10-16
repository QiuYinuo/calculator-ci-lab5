package com.yourname;

public class App {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println(
                "Usage: <number> <operator> <number>"
            );
            System.exit(1);
        }

        try {
            Calculator calculator = new Calculator();

            double result = calculator.eval(
                args[0], args[1], args[2]
            );

            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
