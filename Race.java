import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Race {

    int participantLimit;
    Category category;
    CapacityState capacityState;
    ArrayList<User> racersRegistered = new ArrayList<>();

    public Race(int participantLimit, Category category) {
        this.participantLimit = participantLimit;
        this.category = category;
        this.capacityState = new AvailableCapacityState(); // starts new race as available capacity
    }
    
    public int getParticipantLimit(){
        return this.participantLimit;
    }

    public void setParticipantLimit(int participantLimit) {
        this.participantLimit = participantLimit;
    }

    public Category getCategory() {
        return this.category;
    }

    public boolean isFull() {
        return this.racersRegistered.size() >= this.participantLimit;
    }

        // Capacity State Functions
    public void setCapacityState(CapacityState capacityState) {
        this.capacityState = capacityState;
    }

    public boolean addRacer(Racer racer) {
        return this.capacityState.addRacer(this, racer);
    }

    public boolean isRegistrationFull() {
        return this.capacityState instanceof FullCapacityState;
    }


}
