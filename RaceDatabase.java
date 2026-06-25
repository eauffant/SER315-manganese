import java.util.HashMap;

public class RaceDatabase {
    
    HashMap<String, Race> raceList = new HashMap<>();

    public void addRace(Race race) {
        raceList.put(race.getRaceID(), race);
    }

    public Race getRace(String raceID) {
        return raceList.get(raceID);
    }
    
}
