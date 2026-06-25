import java.util.HashMap;

public class RaceResultsDatabase {

    HashMap<String, Result> raceResults = new HashMap<>();

    public void addResult(Result result) {
        raceResults.put(result.getResultId(), result);
    }

    public Result getResult(String resultId) {
        return raceResults.get(resultId);
    }

}
