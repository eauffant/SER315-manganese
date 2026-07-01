import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RaceEvent {

    String raceID;
    LocalDate date;
    String type;
    int miles;
    String route;
    boolean isOffical;
    LocalDate lastDayOfRegistration;
    ArrayList<Race> races = new ArrayList<>(5);

    public RaceEvent(String raceID, LocalDate date, String type, int miles, String route, boolean isOffical, LocalDate lastDayOfRegistration) {
        this.raceID = raceID;
        this.date = date;
        this.type = type;
        this.miles = miles;
        this.route = route;
        this.isOffical = isOffical;
        this.lastDayOfRegistration = lastDayOfRegistration;
        for(int i = 0; i < 5; i++) {
            this.races.add(null);
        }
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

    public boolean getIsOffical(){
        return this.isOffical;
    }

    public void setIsOffical(boolean isOffical){
        this.isOffical = isOffical;
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

    public boolean isRegistrationOpen() {
        return !LocalDate.now().isAfter(this.lastDayOfRegistration);
    }

}
