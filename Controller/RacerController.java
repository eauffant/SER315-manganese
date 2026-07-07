package Controller;

import Model.*;
import View.*;
import java.time.LocalDate;

public class RacerController {

    RaceDatabase raceDatabase;
    RacerDisplay racerDisplay;
    RacerRegistrationDatabase registrationDatabase;

    // ERROR CODES
    int notFound = 1;
    int registrationClosed = 2;
    int invalidCategory = 3;
    int raceFull = 4;

    public RacerController(RaceDatabase raceDatabase, RacerDisplay racerDisplay, RacerRegistrationDatabase registrationDatabase) {
        this.raceDatabase = raceDatabase;
        this.racerDisplay = racerDisplay;
        this.registrationDatabase = registrationDatabase;
    }

    public void registerForRace(Racer racer) {
        boolean tryAgain = true;
        while (tryAgain) {
            // Racer selects a race
            String raceId = racerDisplay.displayRegistrationPage();
            RaceEvent raceEvent = raceDatabase.getRace(raceId);

            if (raceEvent == null) {
                racerDisplay.displayRegistrationFailure(notFound);
                return;
            }

            // Delegates to RegistrationState to answer registration open?
            if (!raceEvent.register()) {
                racerDisplay.displayRegistrationFailure(registrationClosed);
                return;
            }

            // Delegates to OfficialityState  to answer is race official? Only official races require a license.
            if (raceEvent.requireLicense()) {
                // Does racer have a valid license?
                if (racer.getLicense() == null || !racer.getLicense().isValid()) {
                    // Prompt racer to buy a license
                    boolean wantToBuy = racerDisplay.displayLicensePurchasePrompt();
                    if (!wantToBuy) {
                        return;
                    } else {
                        purchaseLicense(racer);
                    }
                }
            }


            if (racer.getLicense() != null) {
                int updatedCategoryLevel = racer.determineCategoryLevel();
                racer.getLicense().setCategory(new Category(updatedCategoryLevel, ""));
            }

            // Unofficial races require no license, so there is no category to look up from a license.
            // They default any unlicensed racer into the base category (level 1).
            int categoryLevel = racer.getLicense() != null ? racer.getLicense().getCategory().getCategoryLevel() : 1;
            Race targetRace = raceEvent.getRaces().get(categoryLevel);
            if (targetRace == null) {
                racerDisplay.displayRegistrationFailure(invalidCategory);
                return;
            }

            // Delegates to CapacityState to answer is race full?
            if (targetRace.isRegistrationFull()) {
                racerDisplay.displayRegistrationFailure(raceFull);
                return;
            }

            // Does racer meet category for race?
            if (racer.getLicense() != null && !meetsCategoryRequirement(racer, targetRace)) {
                racerDisplay.displayRegistrationFailure(invalidCategory);
                return;
            }

            // Racer registers for race — save to database
            if (!targetRace.addRacer(racer)) {
                racerDisplay.displayRegistrationFailure(raceFull);
                return;
            }


            String registrationId = "reg-" + racer.getUserID() + "-" + raceId;
            Registration registration = new Registration(registrationId, LocalDate.now(), true);
            registrationDatabase.addRegistration(registration);

            racerDisplay.displayRegistrationSuccess(registrationId);
            tryAgain = false;
        }
    }

    public boolean purchaseLicense(Racer racer) {
        int categoryLevel = racer.determineCategoryLevel();
        String[] fields = racerDisplay.displayLicensePurchasePage(categoryLevel);
        String licenseId = fields[0];

        if (licenseId.isEmpty()) {
            return false;
        }

        Category category = new Category(categoryLevel, "");
        License newLicense = new License(
                licenseId,
                true,
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                category
        );

        racer.setLicense(newLicense);
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
