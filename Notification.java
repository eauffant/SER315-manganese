import java.time.LocalDate;

public class Notification {

    String notificationId;
    String message;
    String type;
    LocalDate dateSent;
    Racer sentTo;

    public Notification(String notificationId, String message, String type, LocalDate dateSent, Racer sentTo) {
        this.notificationId = notificationId;
        this.message = message;
        this.type = type;
        this.dateSent = dateSent;
        this.sentTo = sentTo;
    }

    public void send() {
        System.out.println("Notification sent to " + sentTo.getName() + ": " + this.message);
    }

    public String getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateSent() {
        return this.dateSent;
    }

    public void setDateSent(LocalDate dateSent) {
        this.dateSent = dateSent;
    }

    public Racer getSentTo() {
        return this.sentTo;
    }

    public void setSentTo(Racer sentTo) {
        this.sentTo = sentTo;
    }

}
