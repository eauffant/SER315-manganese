package View;

import Model.*;
import java.util.Collection;
import java.util.Scanner;

public class UserDisplay {

    static Scanner scnr;

    public UserDisplay(Scanner scnr) {
        this.scnr = scnr;
    }

    public String[] displaySignUpPage() {
        System.out.println("------ Sign Up ------");

        System.out.printf("Enter User ID: ");
        String userID = scnr.nextLine();

        System.out.printf("Enter Password: ");
        String password = scnr.nextLine();

        System.out.printf("Enter Name: ");
        String name = scnr.nextLine();

        System.out.printf("Enter User Type (Racer / Administrator / Organizer): ");
        String userType = scnr.nextLine();

        System.out.printf("Enter Email: ");
        String email = scnr.nextLine();

        return new String[]{userID, password, name, userType, email};
    }
    
    public String[] displayLoginPage() {
        System.out.println("------ Login ------");

        System.out.print("Enter User ID: ");
        String userID = scnr.nextLine();

        System.out.print("Enter password: ");
        String password = scnr.nextLine();

        return new String[]{userID, password};
    }

    public void displayProfilePage(User user) {
        System.out.println("------ Profile Page ------");
        System.out.printf("User ID: %s\n", user.getUserID());
        System.out.printf("Password: %s\n", user.getPassword());
        System.out.printf("Name: %s\n", user.getName());
        System.out.printf("User Type: %s\n", user.getUserType());
        System.out.printf("Email: %s\n", user.getEmail());
    }

    public void displayAllUsers(Collection<User> users) {
        System.out.printf("| %-20s | %-20s | %-15s | %-30s |%n", "Name", "User ID", "User Type", "Email");
        System.out.println("--------------------------------------------------------------------------------------------------");

        for(User user : users) {
            System.out.printf(" %-22s  %-21s  %-16s  %-31s%n", user.getName(), user.getUserID(), user.getUserType(), user.getEmail());

        }
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static int displayLogin(){
        System.out.println("\n====== Race Management System ======");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.print("Choose: ");
        return Integer.parseInt(scnr.nextLine());
    }

}
