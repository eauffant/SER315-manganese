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
        adminDisplay.displayUserManagementPage(userDatabase);
    }

    public void manageLicenses() {
        System.out.println("Managing licenses...");
    }

    public void manageSystemSettings() {
        adminDisplay.displaySystemSettings();
    }

    public void sendNotification(Racer racer, String message, String type) {
        Notification notification = new Notification("notif-" + racer.getUserID(), message, type, LocalDate.now(), racer);
        notification.send();
        adminDisplay.displayNotificationPage(notification);
    }

}
