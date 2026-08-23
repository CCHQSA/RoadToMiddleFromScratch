package Exceptions.UserRegistrationSystem;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> dataBase;

    public UserRepository() {
        dataBase = new ArrayList<>();
    }

    public void displayDataBase() {
        if (!dataBase.isEmpty()) {
            for (User u : dataBase) {
                System.out.println(u);
            }
        }else {
            throw  new NullPointerException("DataBase is Empty");
        }
    }

    public boolean checkUsername(String username) {
        for (User u : dataBase) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void addUser(User user) {
        dataBase.add(user);
    }
}
