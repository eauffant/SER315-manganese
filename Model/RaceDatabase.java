package Model;
import java.util.Collection;
import java.util.HashMap;

public class RaceDatabase {

    private HashMap<String, RaceEvent> raceList = new HashMap<>();

    public void addRaceEvent(RaceEvent raceEvent) {
        raceList.put(raceEvent.getRaceID(), raceEvent);
    }

    public RaceEvent getRace(String raceID) {
        return raceList.get(raceID);
    }

    public Collection<RaceEvent> getAllRaces() {
        return raceList.values();
    }

}
