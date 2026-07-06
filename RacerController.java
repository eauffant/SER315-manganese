import java.time.LocalDate;

public class RacerController {

    RaceDatabase raceDatabase;
    RacerDisplay racerDisplay;
    RacerRegistrationDatabase registrationDatabase;

    public RacerController(RaceDatabase raceDatabase, RacerDisplay racerDisplay, RacerRegistrationDatabase registrationDatabase) {
        this.raceDatabase = raceDatabase;
        this.racerDisplay = racerDisplay;
        this.registrationDatabase = registrationDatabase;
    }

    public void registerForRace(Racer racer) {
        // Racer selects a race
        String raceId = racerDisplay.displayRegistrationPage();
        RaceEvent raceEvent = raceDatabase.getRace(raceId);

        if (raceEvent == null) {
            racerDisplay.displayMessage("Race not found.");
            return;
        }

        // Delegates to RegistrationState to answer registration open?
        if (!raceEvent.register()) {
            racerDisplay.displayMessage("Registration is currently closed for this race.");
            return;
        }

        // Delegates to OfficialityState  to answer is race official? Only official races require a license.
        if (raceEvent.requireLicense()) {
            // Does racer have a valid license?
            if (racer.license == null || !racer.license.isValid()) {
                // Prompt racer to buy a license
                boolean wantsToBuy = racerDisplay.displayLicensePurchasePrompt();
                if (!wantsToBuy) {
                    racerDisplay.displayMessage("A valid license is required. Registration cancelled.");
                    return;
                }
                // Did racer purchase a license?
                boolean purchased = purchaseLicense(racer);
                if (!purchased) {
                    racerDisplay.displayMessage("License purchase failed. Registration cancelled.");
                    return;
                }
            }
        }

        // Unofficial races require no license, so there is no category to look up from a license.
        // They default any unlicensed racer into the base category (level 1).
        int categoryLevel = racer.getLicense() != null ? racer.getLicense().getCategory().getCategoryLevel() : 1;
        Race targetRace = raceEvent.getRaces().get(categoryLevel);
        if (targetRace == null) {
            racerDisplay.displayMessage("You do not meet the category requirements for this race.");
            return;
        }

        // Delegates to CapacityState to answer is race full?
        if (targetRace.isRegistrationFull()) {
            racerDisplay.displayMessage("This race is full.");
            return;
        }

        // Does racer meet category for race?
        if (racer.getLicense() != null && !meetsCategoryRequirement(racer, targetRace)) {
            racerDisplay.displayMessage("You do not meet the category requirements for this race.");
            return;
        }

        // Racer registers for race — save to database
        if (!targetRace.addRacer(racer)) {
            racerDisplay.displayMessage("This race is full.");
            return;
        }
        String registrationId = "reg-" + racer.getUserID() + "-" + raceId;
        Registration registration = new Registration(registrationId, LocalDate.now(), true);
        registrationDatabase.addRegistration(registration);

        racerDisplay.displayRegistrationSuccess(registrationId);
    }

    public boolean purchaseLicense(Racer racer) {
        String[] fields = racerDisplay.displayLicensePurchasePage();
        String licenseId = fields[0];
        int categoryLevel = Integer.parseInt(fields[1]);

        if (licenseId.isEmpty() || categoryLevel == 0) {
            return false;
        }

        Category category = new Category(categoryLevel, "");
        License newLicense = new License(licenseId, true, LocalDate.now(), LocalDate.now().plusYears(1), category);
        racer.license = newLicense;
        racerDisplay.displayMessage("License purchased successfully!");
        return true;
    }

    public boolean meetsCategoryRequirement(Racer racer, Race race) {

        // Racer must have a license with a matching category
        if (racer.getLicense() == null) {
            return false;
        }

        if (race.getCategory().getCategoryLevel() == (racer.getLicense().getCategory().getCategoryLevel())) {
            return true;
        }
        
        return false;
    }
    
}
