public class UserController {

    protected UserDatabase userDatabase;
    protected UserDisplay userDisplay;

    protected User currentUser;

    public UserController(UserDatabase userDatabase, UserDisplay userDisplay) {
        this.userDatabase = userDatabase;
        this.userDisplay = userDisplay;
    }

    public void userSignUp() {
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

    public User createUser(String userID, String password, String name, String userType, String email) {
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

    public boolean login() {
        String[] loginFields = userDisplay.displayLoginPage();

        String userID = loginFields[0];
        String password = loginFields[1];

        User curUser = userDatabase.getUser(userID);

        if (curUser != null && curUser.getPassword().equals(password)) {
            currentUser = curUser;
            userDisplay.displayMessage("Login Successful");
            return true;
        } else {
            userDisplay.displayMessage("Login Failed");
            return false;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void updateProfile() {
        
    }

}
