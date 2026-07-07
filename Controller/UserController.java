package Controller;

import Model.*;
import View.*;

public class UserController {

    protected static RacerController racerController;
    protected static RacerDisplay racerDisplay;
    protected static RaceDatabase raceDatabase;

    protected static OrganizerController organizerController;
    protected static OrganizerDisplay organizerDisplay;

    protected static AdminController adminController;
    protected static AdminDisplay adminDisplay;

    protected static UserDatabase userDatabase;
    protected static UserDisplay userDisplay;

    protected static User currentUser;

    public UserController(
            UserDatabase userDatabase,
            UserDisplay userDisplay,
            RacerController racerController,
            RacerDisplay racerDisplay,
            RaceDatabase raceDatabase,
            OrganizerController organizerController,
            OrganizerDisplay organizerDisplay,
            AdminController adminController,
            AdminDisplay adminDisplay
    ) {
        this.userDatabase = userDatabase;
        this.userDisplay = userDisplay;

        this.racerController = racerController;
        this.racerDisplay = racerDisplay;
        this.raceDatabase = raceDatabase;

        this.organizerController = organizerController;
        this.organizerDisplay = organizerDisplay;

        this.adminController = adminController;
        this.adminDisplay = adminDisplay;
    }

    public static void startSession(){
        // --- Start screen: login or sign up ---
        boolean loggedIn = false;
        while (!loggedIn) {
            int startChoice = userDisplay.displayLogin();

            if (startChoice == 1) {
                loggedIn = login();
            } else if (startChoice == 2) {
                userSignUp();
            } else {
                UserDisplay.displayMessage("Invalid Choice");
            }
        }
    }

    public static void userSignUp() {
        String[] signUpFields = userDisplay.displaySignUpPage();

        String userID = signUpFields[0];
        String password = signUpFields[1];
        String name = signUpFields[2];
        String userType = signUpFields[3];
        String email = signUpFields[4];

        User newUser = createUser(userID, password, name, userType, email);
        if (newUser != null) {
            userDatabase.addUser(newUser);
        }
    }

    public static User createUser(String userID, String password, String name, String userType, String email) {
        if (userType.equals("Racer")) {
            return new Racer(userID, password, name, userType, email);
        } else if (userType.equals("Organizer")) {
            return new Organizer(userID, password, name, userType, email);
        } else if (userType.equals("Administrator")) {
            return new Administrator(userID, password, name, userType, email);
        } else {
            userDisplay.displayMessage("Invalid user type. Must be Racer, Organizer, or Administrator.");
            return null;
        }
    }

    public static boolean login() {
        String[] loginFields = userDisplay.displayLoginPage();

        String userID = loginFields[0];
        String password = loginFields[1];

        User curUser = userDatabase.getUser(userID);

        if (curUser != null && curUser.getPassword().equals(password)) {
            currentUser = curUser;
            userDisplay.displayMessage("Login Successful");

            if (curUser.getUserType().equals("Administrator")) {
                adminMenu(adminController, adminDisplay);
            }
            else if (curUser.getUserType().equals("Racer")) {
                racerMenu(racerController, racerDisplay, raceDatabase, (Racer) curUser);
            }
            else if (curUser.getUserType().equals("Organizer")) {
                organizerMenu(organizerController, organizerDisplay, (Organizer) curUser);
            }

            return true;
        } else {
            userDisplay.displayMessage("Login Failed");
            return false;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void updateProfile() {}

    static void racerMenu(RacerController racerController, RacerDisplay racerDisplay, RaceDatabase raceDatabase, Racer currentRacer) {
        boolean inRacerMenu = true;
        while (inRacerMenu) {
            int choice = racerDisplay.showRacerMenu();
            if (choice == 1) {
                racerDisplay.displayAllRaces(raceDatabase.getAllRaces());
            } else if (choice == 2) {
                racerDisplay.displayAllRaces(raceDatabase.getAllRaces());
                racerController.registerForRace(currentRacer);
            } else if (choice == 3) {
                inRacerMenu = false;
            } else{
                userDisplay.displayMessage("Invalid choice");
            }
        }
    }

    static void organizerMenu(OrganizerController organizerController, OrganizerDisplay organizerDisplay, Organizer currentOrganizer) {
        boolean inOrganizerrMenu = true;
        while (inOrganizerrMenu) {
            int choice = OrganizerDisplay.displayOrganizerMenu();
            if (choice == 1) {
                organizerController.createRace();
            } else if (choice == 2) {
                organizerController.manageRace();
            } else if (choice == 3) {
                organizerController.enterResults();
            } else if (choice == 4) {
                inOrganizerrMenu = false;
            } else {
                userDisplay.displayMessage("Invalid choice");
            }
        }
    }

    static void adminMenu(AdminController adminController, AdminDisplay adminDisplay) {
        boolean inAdminMenu = true;

        while (inAdminMenu) {
            int choice = AdminDisplay.displayAdminMenu();

            if (choice == 1) {
                adminController.manageUsers();
            } else if (choice == 2) {
                adminController.sendNotification("Your registration is confirmed!", "Info");
            } else if (choice == 3) {
                adminController.manageSystemSettings();
            } else if (choice == 4) {
                String racerUserId = adminDisplay.displayRacerIDPrompt();
                adminController.displayObserverManagementMenu();
                String observerChoice = AdminDisplay.displayObserverChoice();
                adminController.manageObserver(racerUserId, observerChoice);
            } else if (choice == 5) {
                inAdminMenu = false;
            } else  {
                userDisplay.displayMessage("Invalid choice");
            }
        }
    }

}
