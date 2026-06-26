import java.util.Scanner;

public class RacerDisplay {

    Scanner scnr = new Scanner(System.in);

    public String displayRegistrationPage() {
        System.out.print("Select Race by entering Race ID: ");
        return scnr.nextLine();
    }

    public void displayAllRaces(RaceDatabase raceDb) {
        System.out.printf("| %-10s | %-15s | %-10s | %-13s | %-10s | %-15s | %-25s | %-15s |%n", "Race ID", "Date", "Race Type", "Length (Miles)", "Route", "Is It Official?", "Last Day of Registration", "Participant Limit");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");

        for (Race race : raceDb.raceList.values()) {
            System.out.printf(" %-10s   %-15s   %-10s   %-15d   %-12s   %-17s  %-25s   %d  %n", race.getRaceID(), race.getDate(), race.getType(), race.getMiles(), race.getRoute(), race.requireLicense(), race.getLastDayOfRegistration(), race.getParticipantLimit());
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
        System.out.print("Enter Category Level (Beginner/Intermediate/Advanced): ");
        String categoryLevel = scnr.nextLine();
        return new String[]{licenseId, categoryLevel};
    }

}
