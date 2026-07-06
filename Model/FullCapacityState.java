package Model;
public class FullCapacityState implements CapacityState {
    @Override
    public boolean addRacer(Race race, Racer racer) {
        return false;
    }
}
