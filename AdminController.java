import java.time.LocalDate;

public class AdminController extends UserController {

    AdminDisplay adminDisplay;

    public AdminController(UserDatabase userDatabase, UserDisplay userDisplay, AdminDisplay adminDisplay) {
        super(userDatabase, userDisplay);
        this.adminDisplay = adminDisplay;
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
