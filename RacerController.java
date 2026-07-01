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
        Race race = raceDatabase.getRace(raceId);

        if (race == null) {
            System.out.println("Race not found.");
            return;
        }

        // Delegates to RegistrationState to answer registration open?
        if (!race.register()) {
            System.out.println("Registration is currently closed for this race.");
            return;
        }

        // Delegates to OfficialityState  to answer is race official? Only official races require a license.
        if (race.requireLicense()) {
            // Does racer have a valid license?
            if (racer.license == null || !racer.license.isValid()) {
                // Prompt racer to buy a license
                boolean wantsToBuy = racerDisplay.displayLicensePurchasePrompt();
                if (!wantsToBuy) {
                    System.out.println("A valid license is required. Registration cancelled.");
                    return;
                }
                // Did racer purchase a license?
                boolean purchased = purchaseLicense(racer);
                if (!purchased) {
                    System.out.println("License purchase failed. Registration cancelled.");
                    return;
                }
            }
        }

        // Delegates to CapacityState to answer is race full?
        if (!race.addRacer(racer)) {
            System.out.println("This race is full.");
            return;
        }

        // Does racer meet category for race?
        if (!meetsCategoryRequirement(racer, race)) {
            System.out.println("You do not meet the category requirements for this race.");
            return;
        }

        // Racer registers for race — save to database
        String registrationId = "reg-" + racer.getUserID() + "-" + raceId;
        Registration registration = new Registration(registrationId, LocalDate.now(), true);
        registrationDatabase.addRegistration(registration);
        race.racersRegistered.add(racer);

        System.out.println("Registration successful! Registration ID: " + registrationId);
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
        System.out.println("License purchased successfully!");
        return true;
    }

    public boolean meetsCategoryRequirement(Racer racer, Race race) {
        // If race has no category restrictions, anyone can enter
        if (race.getCategories().isEmpty()) {
            return true;
        }

        // Racer must have a license with a matching category
        if (racer.license == null) {
            return false;
        }

        for (Category category : race.getCategories()) {
            if (category.getCategoryLevel() == (racer.license.getCategory().getCategoryLevel())) {
                return true;
            }
        }
        return false;
    }

}
