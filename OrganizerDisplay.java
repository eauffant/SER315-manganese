import java.util.Collection;
import java.util.Scanner;

public class OrganizerDisplay {

    Scanner scnr;

    public OrganizerDisplay(Scanner scnr) {
        this.scnr = scnr;
    }

    public String[] displayRaceCreationPage() {
        System.out.println("------ Create Race ------");

        System.out.print("Enter Race ID: ");
        String raceId = scnr.nextLine();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scnr.nextLine();

        System.out.print("Enter Race Type: ");
        String type = scnr.nextLine();

        System.out.print("Enter Miles: ");
        String miles = scnr.nextLine();

        System.out.print("Enter Route: ");
        String route = scnr.nextLine();

        System.out.print("Is it official? (true/false): ");
        String officialStatus = scnr.nextLine();

        System.out.print("Enter Last Day of Registration (YYYY-MM-DD): ");
        String lastDayOfRegistration = scnr.nextLine();

        System.out.print("Enter Participant Limit: ");
        String participantLimit = scnr.nextLine();

        return new String[]{raceId, date, type, miles, route, officialStatus, lastDayOfRegistration, participantLimit};
    }

    public String[] displayResultsEntryPage() {
        System.out.println("------ Enter Results ------");

        System.out.print("Enter Result ID: ");
        String resultId = scnr.nextLine();

        System.out.print("Enter Placement: ");
        String placement = scnr.nextLine();

        return new String[]{resultId, placement};
    }

    public void displayRaceManagementPage(Collection<RaceEvent> races) {
        System.out.println("------ Race Management ------");
        System.out.printf("| %-10s | %-15s | %-10s | %-10s |%n", "Race ID", "Date", "Type", "Miles");
        System.out.println("---------------------------------------------------------");

        for (RaceEvent race : races) {
            System.out.printf(" %-12s   %-16s   %-11s   %-11d  %n",
                race.getRaceID(), race.getDate(), race.getType(), race.getMiles());
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

}
