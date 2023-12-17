package model;

public class UserDataSingleton {
    private static UserDataSingleton instance;
    private User currentUser;

    public static synchronized UserDataSingleton getInstance() {
        if (instance == null) {
            instance = new UserDataSingleton();
        }
        return instance;
    }

    public void saveUserData(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
