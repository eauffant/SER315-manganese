import Model.*;
import View.*;
import Controller.*;
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
        UserDisplay userDisplay = new UserDisplay(scnr);
        RacerDisplay racerDisplay = new RacerDisplay(scnr);
        OrganizerDisplay organizerDisplay = new OrganizerDisplay(scnr);
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
        adminController.addObserver(racer2);

        userDatabase.addUser(racer1);
        userDatabase.addUser(racer2);
        userDatabase.addUser(organizer1);
        userDatabase.addUser(admin1);

        Category cat1 = new Category(1, "Beginners Only");
        Category cat2 = new Category(2, "Beginner to Intermediate Raacers");
        Category cat3 = new Category(3, "Intermediate Racers");
        Category cat4 = new Category(4, "Semi-Pro Racers");
        Category cat5 = new Category(5, "Professional Competitive Racers");

        //race creation
        Race race1 = new Race(200, cat1);
        Race race2 = new Race(200, cat2);
        Race race3 = new Race(200, cat3);
        Race race4 = new Race(200, cat4);
        Race race5 = new Race(200, cat5);

 // raceEvent1: official, requires Beginner license
        RaceEvent raceEvent1 = new RaceEvent("raceEvent1", LocalDate.now().plusWeeks(6), "Road", 25, "Around Lake", true, LocalDate.now().plusWeeks(2));
        raceEvent1.addRace(race1);
        raceEvent1.addRace(race2);
        raceEvent1.addRace(race3);
        raceEvent1.addRace(race4);
        raceEvent1.addRace(race5);

        // raceEvent2: official, requires Advanced license
        RaceEvent raceEvent2 = new RaceEvent("raceEvent2", LocalDate.now().plusWeeks(10), "Gravel", 100, "Fire Road", true, LocalDate.now().plusWeeks(3));
        raceEvent2.addRace(race1);
        raceEvent2.addRace(race2);
        raceEvent2.addRace(race3);
        raceEvent2.addRace(race4);
        raceEvent2.addRace(race5);

        // raceEvent3: not official, no license required
        RaceEvent raceEvent3 = new RaceEvent("raceEvent3", LocalDate.now().plusWeeks(15), "Mountain", 15, "Up Mountain", false, LocalDate.now().plusWeeks(5));
        raceEvent3.addRace(race1);
        raceEvent3.addRace(race2);
        raceEvent3.addRace(race3);
        raceEvent3.addRace(race4);
        raceEvent3.addRace(race5);

        raceDatabase.addRaceEvent(raceEvent1);
        raceDatabase.addRaceEvent(raceEvent2);
        raceDatabase.addRaceEvent(raceEvent3);

        // racer1 has a Beginner license
        //License license1 = new License("lic1", true, LocalDate.now(), LocalDate.now().plusYears(1), cat1);
        //racer1.license = license1;

        Result result1 = new Result("res1", 1);
        Result result2 = new Result("res2", 5);
        resultsDatabase.addResult(result1);
        resultsDatabase.addResult(result2);

        Review review1 = new Review("rev1", 5, "Great race, well organized!");
        reviewDatabase.addReview(review1);

        // --- Start screen: login or sign up ---
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("\n====== Race Management System ======");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.print("Choose: ");
            int startChoice = Integer.parseInt(scnr.nextLine());

            if (startChoice == 1) {
                loggedIn = userController.login();
            } else if (startChoice == 2) {
                userController.userSignUp();
            } else {
                System.out.println("Invalid choice.");
            }
        }

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
            userDisplay.displayAllUsers(userDatabase.getAllUsers());
        }
    }

    static void showRacerMenu(RacerController racerController, RacerDisplay racerDisplay, RaceDatabase raceDatabase, Racer currentRacer) {
        System.out.println("\n-- Racer Menu --");
        System.out.println("1. View all races");
        System.out.println("2. Register for a race");
        System.out.print("Choose: ");
        int choice = Integer.parseInt(scnr.nextLine());

        if (choice == 1) {
            racerDisplay.displayAllRaces(raceDatabase.getAllRaces());
        } else if (choice == 2) {
            racerDisplay.displayAllRaces(raceDatabase.getAllRaces());
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
        System.out.println("4. Manage racer notification subscription");
        System.out.print("Choose: ");
        int choice = Integer.parseInt(scnr.nextLine());

        if (choice == 1) {
            adminController.manageUsers();
        } else if (choice == 2) {
            adminController.sendNotification("Your registration is confirmed!", "Info");
        } else if (choice == 3) {
            adminController.manageSystemSettings();
        } else if (choice == 4) {
            System.out.print("Enter racer User ID: ");
            String racerUserId = scnr.nextLine();
            adminController.displayObserverManagementMenu();
            String observerChoice = scnr.nextLine();
            adminController.manageObserver(racerUserId, observerChoice);
        }
    }

}
