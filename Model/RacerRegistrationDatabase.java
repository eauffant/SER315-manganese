package Model;
import java.util.HashMap;

public class RacerRegistrationDatabase {

    HashMap<String, Registration> registrations = new HashMap<>();

    public void addRegistration(Registration registration) {
        registrations.put(registration.registrationID, registration);
    }

    public Registration getRegistration(String registrationID) {
        return registrations.get(registrationID);
    }

}
