public interface RegistrationState {
    void openRegistration(Race race);
    void closeRegistration(Race race);
    boolean register();
}
