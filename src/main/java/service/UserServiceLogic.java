package service;

import dao.UserDao;
import dao.UserDaoImpl;

import java.util.List;

public class UserServiceLogic implements UserService{
    /**
     * Used to login a user
     *
     * @param username user name
     * @param password user password
     * @return outcome of login - success or not
     */
    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean adminRights(String username) {
        return false;
    }
}
