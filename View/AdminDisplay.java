package View;

import Model.*;
import java.util.Collection;
import java.util.Scanner;

public class AdminDisplay {

    static Scanner scnr;

    public void displayUserManagementPage(Collection<User> users) {
        System.out.println("------ User Management ------");
        System.out.printf("| %-20s | %-20s | %-15s | %-30s |%n", "Name", "User ID", "User Type", "Email");
        System.out.println("--------------------------------------------------------------------------------------------------");

        for (User user : users) {
            System.out.printf(" %-22s  %-21s  %-16s  %-31s%n",
                user.getName(), user.getUserID(), user.getUserType(), user.getEmail());
        }
    }

    public void displaySystemSettings() {
        System.out.println("------ System Settings ------");
        System.out.println("No settings to display.");
    }

    public void displayNotificationPage(Notification notification) {
        System.out.println("------ Notification Details ------");
        System.out.println("ID: " + notification.getNotificationId());
        System.out.println("Type: " + notification.getType());
        System.out.println("Message: " + notification.getMessage());
        System.out.println("Sent To: " + notification.getSentTo().getName());
        System.out.println("Date Sent: " + notification.getDateSent());
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayRacerNotFound() {
        System.out.println("Racer not found.");
    }

    public void displayInvalid() {
        System.out.println("Invalid choice");
    }

    public void displayObserverManagementMenu() {
        System.out.println("------ Manage Racer Notifications ------");
        System.out.println("1. Add racer as observer");
        System.out.println("2. Remove racer as observer");
        System.out.print("Choose: ");
    }

    public static int displayAdminMenu(){
        System.out.println("\n-- Admin Menu --");
        System.out.println("1. View all users");
        System.out.println("2. Send notification to racer1");
        System.out.println("3. System settings");
        System.out.println("4. Manage racer notification subscription");
        System.out.println("5. Logout");
        System.out.print("Choose: ");

        int choice = Integer.parseInt(scnr.nextLine());
        if (choice == 5) {
            System.out.println("Goodbye!");
            System.exit(0);
        }
        return choice;
    }

    public static String displayObserverChoice(){
        System.out.println("Enter Choice:");
        return scnr.nextLine();
    }

    public String displayRacerIDPrompt() {
        System.out.print("Enter racer User ID: ");
        return scnr.nextLine();
    }
}
