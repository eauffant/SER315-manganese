import java.time.LocalDate;

public class OrganizerController extends UserController {

    RaceDatabase raceDatabase;
    RacerRegistrationDatabase registrationDatabase;
    RaceResultsDatabase resultsDatabase;
    OrganizerDisplay organizerDisplay;

    public OrganizerController(UserDatabase userDatabase, UserDisplay userDisplay, RaceDatabase raceDatabase, RacerRegistrationDatabase registrationDatabase, RaceResultsDatabase resultsDatabase, OrganizerDisplay organizerDisplay) {
        super(userDatabase, userDisplay);
        this.raceDatabase = raceDatabase;
        this.registrationDatabase = registrationDatabase;
        this.resultsDatabase = resultsDatabase;
        this.organizerDisplay = organizerDisplay;
    }

    public void createRace() {
        String[] raceFields = organizerDisplay.displayRaceCreationPage();

        String raceId = raceFields[0];
        LocalDate date = LocalDate.parse(raceFields[1]);
        String type = raceFields[2];
        int miles = Integer.parseInt(raceFields[3]);
        String route = raceFields[4];
        boolean officialStatus = Boolean.parseBoolean(raceFields[5]);
        LocalDate lastDayOfRegistration = LocalDate.parse(raceFields[6]);
        int participantLimit = Integer.parseInt(raceFields[7]);
        RaceEvent newRace = new RaceEvent(raceId, date, type, miles, route, officialStatus, lastDayOfRegistration);
        raceDatabase.addRaceEvent(newRace);
        System.out.println("Race created successfully!");
    }

    public void manageRace() {
        organizerDisplay.displayRaceManagementPage(raceDatabase);
    }

    public void enterResults() {
        String[] resultFields = organizerDisplay.displayResultsEntryPage();

        String resultId = resultFields[0];
        int placement = Integer.parseInt(resultFields[1]);

        Result newResult = new Result(resultId, placement);
        resultsDatabase.addResult(newResult);
        System.out.println("Result entered successfully!");
    }

    public void openRegistration(String raceId) {
        RaceEvent race = raceDatabase.getRace(raceId);
        if (race != null) {
            race.openRegistration();
            System.out.println("Registration is now open.");
        } else {
            System.out.println("Race not found.");
        }
    }

    public void closeRegistration(String raceId) {
        RaceEvent race = raceDatabase.getRace(raceId);
        if (race != null) {
            race.closeRegistration();
            System.out.println("Registration is now closed.");
        } else {
            System.out.println("Race not found.");
        }
    }


    public void manageRegistrationLimits(String raceId, int categoryLevel, int newLimit) {
        Race race = raceDatabase.getRace(raceId).getRaces().get(categoryLevel);
        if (race != null) {
            race.setParticipantLimit(newLimit);
            System.out.println("Participant limit updated to " + newLimit);
        } else {
            System.out.println("Race not found.");
        }
    }

}
