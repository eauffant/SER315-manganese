public class AdminDisplay {

    public void displayUserManagementPage(UserDatabase userDatabase) {
        System.out.println("------ User Management ------");
        System.out.printf("| %-20s | %-20s | %-15s | %-30s |%n", "Name", "User ID", "User Type", "Email");
        System.out.println("--------------------------------------------------------------------------------------------------");

        for (User user : userDatabase.userList.values()) {
            System.out.printf(" %-22s  %-21s  %-16s  %-31s%n",
                user.getName(), user.getUserID(), user.getUserType(), user.getEmail());
        }
    }

    public void displaySystemSettings() {
        System.out.println("------ System Settings ------");
        System.out.println("No settings to display.");
    }

    public void displayNotificationPage(Notification notification) {
        System.out.println("------ Notification Details ------");
        System.out.println("ID: " + notification.getNotificationId());
        System.out.println("Type: " + notification.getType());
        System.out.println("Message: " + notification.getMessage());
        System.out.println("Sent To: " + notification.getSentTo().getName());
        System.out.println("Date Sent: " + notification.getDateSent());
    }

}
