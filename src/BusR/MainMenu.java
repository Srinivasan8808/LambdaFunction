package BusR;

import java.util.Scanner;

public class MainMenu {
    // Create one scanner for the whole program
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("---- Welcome to Reservation ----");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            // Dummy login
            System.out.print("Enter username: ");
            sc.next();
            System.out.print("Enter password: ");
            sc.next();
            System.out.println("\nLogin Successful!\n");
            sub_menu();
        } else {
            System.out.println("Exiting...");
            // ❌ Do NOT close sc here
        }
    }

    public static void sub_menu() {
        while (true) {
            System.out.println("\n======================================");
            System.out.println("           --- Enjoy yourself ---     ");
            System.out.println("1. Bus Details");
            System.out.println("2. Reserve Tickets");
            System.out.println("3. Cancel Tickets");
            System.out.println("4. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            int subChoice = sc.nextInt();

            switch (subChoice) {
                case 1 -> Bus.displayBusDetails();
                case 2 -> ReservationService.reserveTicket();
                case 3 -> ReservationService.cancelTicket();
                case 4 -> { return; } // Go back to main menu
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
