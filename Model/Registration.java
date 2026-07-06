package Model;
import java.time.LocalDate;;

public class Registration {
    String registrationID;
    LocalDate registrationDate;
    boolean registationStatus;

    public Registration(String registrationID, LocalDate registrationDate, boolean registationStatus) {
        this.registrationID = registrationID; 
        this.registrationDate = registrationDate;
        this.registationStatus = registationStatus;
    }


}
