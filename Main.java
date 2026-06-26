import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {

        // --- Set up databases ---
        UserDatabase userDatabase = new UserDatabase();
        RaceDatabase raceDatabase = new RaceDatabase();
        RacerRegistrationDatabase registrationDatabase = new RacerRegistrationDatabase();
        RaceResultsDatabase resultsDatabase = new RaceResultsDatabase();
        RaceReviewDatabase reviewDatabase = new RaceReviewDatabase();

        // --- Set up displays ---
        UserDisplay userDisplay = new UserDisplay();
        RacerDisplay racerDisplay = new RacerDisplay();
        OrganizerDisplay organizerDisplay = new OrganizerDisplay();
        AdminDisplay adminDisplay = new AdminDisplay();

        // --- Set up controllers ---
        UserController userController = new UserController(userDatabase, userDisplay);
        RacerController racerController = new RacerController(raceDatabase, racerDisplay, registrationDatabase);
        OrganizerController organizerController = new OrganizerController(userDatabase, userDisplay, raceDatabase, registrationDatabase, resultsDatabase, organizerDisplay);
        AdminController adminController = new AdminController(userDatabase, userDisplay, adminDisplay);

        // --- Sample data ---
        Racer racer1 = new Racer("lukej", "123456", "Luke James", "Racer", "ljames@email.com");
        Racer racer2 = new Racer("sarahm", "password", "Sarah Miller", "Racer", "smiller@email.com");
        Organizer organizer1 = new Organizer("org1", "orgpass", "Tom Baker", "Organizer", "tbaker@email.com");
        Administrator admin1 = new Administrator("admin1", "adminpass", "Admin User", "Administrator", "admin@email.com");

        adminController.addObserver(racer1);

        userDatabase.addUser(racer1);
        userDatabase.addUser(racer2);
        userDatabase.addUser(organizer1);
        userDatabase.addUser(admin1);

        Category catBeginner = new Category(1, "Open to all skill levels");
        Category catAdvanced = new Category(5, "Competitive racers only");

        // race1: official, requires Beginner license
        Race race1 = new Race("race1", LocalDate.now().plusWeeks(6), "Road", 25, "Around Lake", true, LocalDate.now().plusWeeks(2), 200);
        race1.addCategory(catBeginner);

        // race2: official, requires Advanced license
        Race race2 = new Race("race2", LocalDate.now().plusWeeks(10), "Gravel", 100, "Fire Road", true, LocalDate.now().plusWeeks(3), 50);
        race2.addCategory(catAdvanced);

        // race3: not official, no license required
        Race race3 = new Race("race3", LocalDate.now().plusWeeks(15), "Mountain", 15, "Up Mountain", false, LocalDate.now().plusWeeks(5), 500);

        raceDatabase.addRace(race1);
        raceDatabase.addRace(race2);
        raceDatabase.addRace(race3);

        // racer1 has a Beginner license
        License license1 = new License("lic1", true, LocalDate.now(), LocalDate.now().plusYears(1), catBeginner);
        racer1.license = license1;

        Result result1 = new Result("res1", 1);
        Result result2 = new Result("res2", 5);
        resultsDatabase.addResult(result1);
        resultsDatabase.addResult(result2);

        Review review1 = new Review("rev1", 5, "Great race, well organized!");
        reviewDatabase.addReview(review1);

        // --- Main menu loop ---
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n====== Race Management System ======");
            System.out.println("1. User actions");
            System.out.println("2. Racer actions");
            System.out.println("3. Organizer actions");
            System.out.println("4. Admin actions");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            choice = Integer.parseInt(scnr.nextLine());

            if (choice == 1) {
                showUserMenu(userController, userDisplay, userDatabase);
            } else if (choice == 2) {
                showRacerMenu(racerController, racerDisplay, raceDatabase, racer1);
            } else if (choice == 3) {
                showOrganizerMenu(organizerController);
            } else if (choice == 4) {
                showAdminMenu(adminController, racer1);
            } else if (choice == 5) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scnr.close();
    }

    static void showUserMenu(UserController userController, UserDisplay userDisplay, UserDatabase userDatabase) {
        System.out.println("\n-- User Menu --");
        System.out.println("1. Sign up");
        System.out.println("2. Login");
        System.out.println("3. View all users");
        System.out.print("Choose: ");
        int choice = Integer.parseInt(scnr.nextLine());

        if (choice == 1) {
            userController.userSignUp();
        } else if (choice == 2) {
            userController.login();
        } else if (choice == 3) {
            userDisplay.displayAllUsers(userDatabase);
        }
    }

    static void showRacerMenu(RacerController racerController, RacerDisplay racerDisplay, RaceDatabase raceDatabase, Racer currentRacer) {
        System.out.println("\n-- Racer Menu --");
        System.out.println("1. View all races");
        System.out.println("2. Register for a race");
        System.out.print("Choose: ");
        int choice = Integer.parseInt(scnr.nextLine());

        if (choice == 1) {
            racerDisplay.displayAllRaces(raceDatabase);
        } else if (choice == 2) {
            racerController.registerForRace(currentRacer);
        }
    }

    static void showOrganizerMenu(OrganizerController organizerController) {
        System.out.println("\n-- Organizer Menu --");
        System.out.println("1. Create a race");
        System.out.println("2. View race management");
        System.out.println("3. Enter results");
        System.out.print("Choose: ");
        int choice = Integer.parseInt(scnr.nextLine());

        if (choice == 1) {
            organizerController.createRace();
        } else if (choice == 2) {
            organizerController.manageRace();
        } else if (choice == 3) {
            organizerController.enterResults();
        }
    }

    static void showAdminMenu(AdminController adminController, Racer racer1) {
        System.out.println("\n-- Admin Menu --");
        System.out.println("1. View all users");
        System.out.println("2. Send notification to racer1");
        System.out.println("3. System settings");
        System.out.print("Choose: ");
        int choice = Integer.parseInt(scnr.nextLine());

        if (choice == 1) {
            adminController.manageUsers();
        } else if (choice == 2) {
            adminController.sendNotification("Your registration is confirmed!", "Info");
        } else if (choice == 3) {
            adminController.manageSystemSettings();
        }
    }

}
