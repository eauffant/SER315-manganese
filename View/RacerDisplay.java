package View;

import Model.*;
import java.util.Collection;
import java.util.Scanner;

public class RacerDisplay {

    Scanner scnr;

    public RacerDisplay(Scanner scnr) {
        this.scnr = scnr;
    }

    public String displayRegistrationPage() {
        while (true) {
            System.out.println("\n\n");
            System.out.println("Select Race by entering Race ID ");
            System.out.println("OR type logout to exit registration:");

            String selectedRace = scnr.nextLine().trim();

            if (selectedRace.equalsIgnoreCase("logout")) {
                return null;
            }

            System.out.println("Register for race?: " + selectedRace + " (type 1 or 2)");
            System.out.println("1: Yes");
            System.out.println("2: No");

            String confirmSelection = scnr.nextLine().trim();

            if (confirmSelection.equals("1")) {
                return selectedRace;
            } else if (confirmSelection.equals("2")) {
                System.out.println("Registration cancelled. Choose another race.");
            } else {
                System.out.println("Invalid selection");
            }
        }
    }

    public void displayAllRaces(Collection<RaceEvent> races) {
        System.out.printf("| %-10s | %-15s | %-10s | %-13s | %-10s | %-15s | %-25s |%n", "Race ID", "Date", "Race Type", "Length (Miles)", "Route", "Is It Official?", "Last Day of Registration");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");

        for (RaceEvent race : races) {
            System.out.printf(" %-10s   %-15s   %-10s   %-15d   %-12s   %-17s  %-25s %n", race.getRaceID(), race.getDate(), race.getType(), race.getMiles(), race.getRoute(), race.requireLicense(), race.getLastDayOfRegistration());
        }
    }

    public boolean displayLicensePurchasePrompt() {
        System.out.println("This race requires a license. Would you like to purchase one? (yes/no): ");
        String answer = scnr.nextLine().trim();
        if(answer.equalsIgnoreCase("no")) {
            System.out.println("Valid license required");
            System.out.println("Back to main menu or logout? (Type 1 or 2)");
            System.out.println("1: Main menu");
            System.out.println("2: Logout");
            String answer2 = scnr.nextLine();
            if(answer2.equals("1")){return false;}
            if(answer2.equals("2")){
                System.out.println("Goodbye!");
                System.exit(0);
            }
        } else if(answer.equalsIgnoreCase("yes")) {return true;}
        return false;
    }

    public String[] displayLicensePurchasePage(int categoryLevel) {
        System.out.println("------ Purchase License ------");
        System.out.print("Enter License ID: ");
        String licenseId = scnr.nextLine();

        System.out.println("----------------------------");
        System.out.println("Purchase Successful");
        System.out.println("----------------------------");

        System.out.println("Your category level based on podiums is: " + categoryLevel);
        return new String[]{licenseId, String.valueOf(categoryLevel)};
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }



    public void displayRegistrationSuccess(String registrationId) {
        System.out.println("Registration successful! Registration ID: " + registrationId);
    }

    public void displayRegistrationFailure(int errorCode) {
        switch (errorCode) {
            case 1:
                System.out.println("Race not found");
                break;
            case 2:
                System.out.println("Registration is closed");
            case 3:
                System.out.println("You do not meet the category for this race");
            case 4:
                System.out.println("This race is full");
        }
    }

    public int showRacerMenu(){
        System.out.println("\n-- Racer Menu --");
        System.out.println("1. View all races");
        System.out.println("2. Register for a race");
        System.out.println("3. Logout");

        System.out.print("Choose: ");
        int choice = Integer.parseInt(scnr.nextLine());
         if (choice == 3) {
            System.out.println("Goodbye!");
            System.exit(0);
        }
        return choice;
    }

}
