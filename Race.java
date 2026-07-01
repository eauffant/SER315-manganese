import java.util.ArrayList;

public class Race {

    int participantLimit;
    Category category;
    ArrayList<User> racersRegistered = new ArrayList<>();

    public Race(int participantLimit, Category category) {
        this.participantLimit = participantLimit;
        this.category = category;
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

}