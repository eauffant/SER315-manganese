public class Racer extends User implements Observer {
    
    String[] creditCardInformation;
    int podiumCount = 0;
    License license = null;

    public Racer(String userID, String password, String name, String userType, String email){
        super(userID, password, name, userType, email);
    }

    @Override
    public void update(String message) {
        System.out.println("Notification for " + getName() + ": " + "Category Upgraded!");
    }

}
