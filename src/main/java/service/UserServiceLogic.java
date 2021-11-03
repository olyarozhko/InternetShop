package service;

import dao.UserDaoImpl;
import model.Response;
import model.User;


public class UserServiceLogic implements UserService {
    @Override
    public Response<User> login(String username, String password) {
        var userDao = new UserDaoImpl();
        if (username == null) {
            return new Response<>(null, false, "User name can't be blank ");
        }
        if (userDao.getByName(username) == null) {
            return new Response<>(null, false, "No such user");
        }
        if (!userDao.getByName(username).getPassword().equals(password)) {
            return new Response<>(null, false, "Wrong user or password");
        }


        return new Response<User>(new User(username, password), true, null);
    }

    public Response<User> register(String username, String password, String firstName, String lastName, String phoneNumber) {
        var userRegi = new UserDaoImpl();
        if (username == null) {
            return new Response<>(null, false, "User name can't be blank");
        }
        if (password == null) {
            return new Response<>(null, false, "Password can't be blank");
        }
        if (userRegi.getByName(username) != null) {
            return new Response<>(null, false, "User name already  exist");
        }

        var user = userRegi.addUser(username, password,firstName,lastName,phoneNumber);

        return new Response<User>(null, true, "User add");
    }

    public Response<User> userLock(int Iduser) {
        var userSucces = new UserDaoImpl();
        if (userSucces.getByName(username) == null) {
            return new Response<>(null, false, "User locked");
        }
        return new Response<User>(null, true, "User unlocked");
    }


}
/**
 * Used to login a user
 *
 * @param username user name
 * @param password user password
 * @return outcome of login - success or not
 */


