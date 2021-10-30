package service;

import model.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OrderService implements UserService {
    User user = new User();


   /* @Override
    public boolean login(String username, String password) {
        for (String key : user)
            if (key.equals(username)
                    && user(key)
                    .equals(password))
                return true;

        return false;
    }*/

    @Override
    public boolean blockedUser(String username, boolean blocked) {
        return false;
    }


}


