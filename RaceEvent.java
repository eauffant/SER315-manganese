import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RaceEvent {

    String raceID;
    LocalDate date;
    String type;
    int miles;
    String route;
    LocalDate lastDayOfRegistration;
    ArrayList<Race> races = new ArrayList<>(5);
    OfficialityState officialityState;
    RegistrationState registrationState;  

    public RaceEvent(String raceID, LocalDate date, String type, int miles, String route, boolean officialStatus, LocalDate lastDayOfRegistration) {
        this.raceID = raceID;
        this.date = date;
        this.type = type;
        this.miles = miles;
        this.route = route;
        this.lastDayOfRegistration = lastDayOfRegistration;
        for(int i = 0; i < 5; i++) {
            this.races.add(null);
        }
        this.officialityState = officialStatus ? new OfficialState() : new UnofficialState(); // if false, no license required and thus unofficial. vice versa for true.
        this.registrationState = !LocalDate.now().isAfter(lastDayOfRegistration) ? new OpenRegistrationState() : new ClosedRegistrationState();
    }

    public String getRaceID(){
        return this.raceID;
    }

    public void setRaceID(String raceID){
        this.raceID = raceID;
    }

    public String getDate(){
    	return this.date.format(DateTimeFormatter.ofPattern("MMMM d'th'"));
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public int getMiles(){
        return this.miles;
    }

    public void setMiles(int miles){
        this.miles = miles;
    }

    public String getRoute(){
        return this.route;
    }

    public void setRoute(String route){
        this.route = route;
    }

    public String getLastDayOfRegistration(){
        return this.lastDayOfRegistration.format(DateTimeFormatter.ofPattern("MMMM d'th'"));
    }

    public void setLastDayOfRegistration(LocalDate lastDayOfRegistration){
        this.lastDayOfRegistration = lastDayOfRegistration;
    }

    public ArrayList<Race> getRaces() {
        return this.races;
    }

    public void addRace(Race race) {
        this.races.add(race.getCategory().getCategoryLevel(), race);
    }

        // Registration State functions
    public void setRegistrationState(RegistrationState registrationState) {
        this.registrationState = registrationState;
    }

    public void openRegistration() {
        this.registrationState.openRegistration(this);
    }

    public void closeRegistration() {
        this.registrationState.closeRegistration(this);
    }

    public boolean register() {
        return this.registrationState.register();
    }

    // Officiality State Functions
    public void setOfficialityState(OfficialityState officialityState) {
        this.officialityState = officialityState;
    }

    public boolean requireLicense() {
        return this.officialityState.requireLicense();
    }
}
