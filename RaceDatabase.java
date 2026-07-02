import java.util.HashMap;

public class RaceDatabase {
    
    HashMap<String, RaceEvent> raceList = new HashMap<>();

    public void addRaceEvent(RaceEvent raceEvent) {
        raceList.put(raceEvent.getRaceID(), raceEvent);
    }

    public RaceEvent getRace(String raceID) {
        return raceList.get(raceID);
    }
    
}
