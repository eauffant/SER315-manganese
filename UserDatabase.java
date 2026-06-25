import java.util.HashMap;


public class UserDatabase {

    HashMap<String, User> userList = new HashMap<>();

    public void addUser(User user) {
        userList.put(user.getUserID(), user);
    }

    public User getUser(String userID) {
        return userList.get(userID);
    }

}
