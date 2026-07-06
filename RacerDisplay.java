import java.util.Collection;
import java.util.Scanner;

public class RacerDisplay {

    Scanner scnr;

    public RacerDisplay(Scanner scnr) {
        this.scnr = scnr;
    }

    public String displayRegistrationPage() {
        System.out.print("Select Race by entering Race ID: ");
        return scnr.nextLine();
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
        String answer = scnr.nextLine();
        return answer.equalsIgnoreCase("yes");
    }

    public String[] displayLicensePurchasePage() {
        System.out.println("------ Purchase License ------");
        System.out.print("Enter License ID: ");
        String licenseId = scnr.nextLine();
        System.out.print("Enter Category Level (1, 2, 3, 4, 5): ");
        String categoryLevel = scnr.nextLine();
        return new String[]{licenseId, categoryLevel};
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayRegistrationSuccess(String registrationId) {
        System.out.println("Registration successful! Registration ID: " + registrationId);
    }

}
