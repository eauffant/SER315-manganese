import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Race {

    String raceID;
    LocalDate date;
    String type;
    int miles;
    String route;
    boolean isOffical;
    LocalDate lastDayOfRegistration;
    ArrayList<Category> categories = new ArrayList<>();
    int participantLimit;
    ArrayList<User> racersRegistered = new ArrayList<>();

    public Race(String raceID, LocalDate date, String type, int miles, String route, boolean isOffical, LocalDate lastDayOfRegistration, int participantLimit) {
        this.raceID = raceID;
        this.date = date;
        this.type = type;
        this.miles = miles;
        this.route = route;
        this.isOffical = isOffical;
        this.lastDayOfRegistration = lastDayOfRegistration;
        this.participantLimit = participantLimit;
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

    public ArrayList<Category> getCategories() {
        return this.categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public boolean isRegistrationOpen() {
        return !LocalDate.now().isAfter(this.lastDayOfRegistration);
    }

    public boolean isFull() {
        return this.racersRegistered.size() >= this.participantLimit;
    }

    public int getParticipantLimit(){
        return this.participantLimit;
    }

    public void setParticipantLimit(int participantLimit) {
        this.participantLimit = participantLimit;
    }


}
