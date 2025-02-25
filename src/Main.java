package src;

public class Main {
    public static void main(String[] args) {
        // Display a message on the console
        System.out.println("Hello, World!");

        // Display some additional information
        System.out.println("This is a simple Java program");
        System.out.println("Current time in milliseconds: " + System.currentTimeMillis());

        // Display a formatted message
        String name = "Java U6r";
        int year = 2025;
        System.out.printf("Welcome, %s! It's now %d.\n", name, year);
    }
}