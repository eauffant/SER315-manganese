package Controller;

import Model.*;
import View.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminController extends UserController implements Subject {

    AdminDisplay adminDisplay;
    private ArrayList<Observer> subscribers = new ArrayList<>();

    public AdminController(UserDatabase userDatabase, UserDisplay userDisplay, AdminDisplay adminDisplay) {
        super(userDatabase, userDisplay);
        this.adminDisplay = adminDisplay;
    }

    @Override
    public void addObserver(Observer o) {
        subscribers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        subscribers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer o : subscribers) {
            o.update(message);
        }
    }

    public void sendNotification(String message, String type) {
        notifyObservers(message);
    }

    public void manageUsers() {
        adminDisplay.displayUserManagementPage(userDatabase.getAllUsers());
    }

    public void manageLicenses() {
        adminDisplay.displayMessage("Managing licenses...");
    }

    public void manageSystemSettings() {
        adminDisplay.displaySystemSettings();
    }

    public void sendNotification(Racer racer, String message, String type) {
        Notification notification = new Notification("notif-" + racer.getUserID(), message, type, LocalDate.now(), racer);
        notification.send();
        adminDisplay.displayNotificationPage(notification);
    }

    public void displayObserverManagementMenu() {
        adminDisplay.displayObserverManagementMenu();
    }

    public void manageObserver(String racerUserId, String choice) {
        User user = userDatabase.getUser(racerUserId);
        if (!(user instanceof Racer)) {
            adminDisplay.displayMessage("Racer not found.");
            return;
        }
        Racer racer = (Racer) user;

        if (choice.equals("1")) {
            addObserver(racer);
            adminDisplay.displayMessage(racer.getName() + " added as observer.");
        } else if (choice.equals("2")) {
            removeObserver(racer);
            adminDisplay.displayMessage(racer.getName() + " removed as observer.");
        } else {
            adminDisplay.displayMessage("Invalid choice.");
        }
    }

}
