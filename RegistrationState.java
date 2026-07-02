public interface RegistrationState {
    void openRegistration(RaceEvent race);
    void closeRegistration(RaceEvent race);
    boolean register();
}
