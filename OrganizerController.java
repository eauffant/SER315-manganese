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

        Race newRace = new Race(raceId, date, type, miles, route, officialStatus, lastDayOfRegistration, participantLimit);
        raceDatabase.addRace(newRace);
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
        setRegistrationStatus(raceId, true);
    }

    public void closeRegistration(String raceId) {
        setRegistrationStatus(raceId, false);
    }

    public void setRegistrationStatus(String raceId, boolean isOpen) {
        Race race = raceDatabase.getRace(raceId);
        if (race != null) {
            race.setIsOffical(isOpen);
            String status = isOpen ? "opened" : "closed";
            System.out.println("Registration " + status + " for race: " + raceId);
        } else {
            System.out.println("Race not found.");
        }
    }

    public void manageRegistrationLimits(String raceId, int newLimit) {
        Race race = raceDatabase.getRace(raceId);
        if (race != null) {
            race.setParticipantLimit(newLimit);
            System.out.println("Participant limit updated to " + newLimit);
        } else {
            System.out.println("Race not found.");
        }
    }

}
