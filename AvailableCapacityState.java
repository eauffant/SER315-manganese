public class AvailableCapacityState implements CapacityState {
    @Override
    public boolean addRacer(Race race, Racer racer) {
        race.racersRegistered.add(racer);
        if (race.racersRegistered.size() >= race.getParticipantLimit()) {
            race.setCapacityState(new FullCapacityState());
        }
        return true;
    }
}
