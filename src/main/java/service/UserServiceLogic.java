package service;

import dao.UserDaoImpl;
import model.Response;
import model.User;

public class UserServiceLogic implements UserService {
    UserDaoImpl userDao;

    @Override
    public Response<User> login(String username, String password) {

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

    @Override
    public Response<User> register(String username, String password, String firstName, String lastName,
                                   String phoneNumber, String email) {

        if (username == null) {
            return new Response<>(null, false, "User name can't be blank");
        }
        if (password == null) {
            return new Response<>(null, false, "Password can't be blank");
        }
        if (userDao.getByName(username) != null) {
            return new Response<>(null, false, "User with such username already exist");
        }
        var userNew = new User(username, password, firstName, lastName, phoneNumber, email);

        userDao.addUser(userNew);

        return new Response<User>(userNew, true, null);
    }

    @Override
    public Response<User> userLock(Integer userId) {
        if (!userIdChecking(userId).isSuccess()) {
            return userIdChecking(userId);
        }
        if (userDao.getById(userId).isLocked()) {
            return new Response<>(null, false, "User is already locked");
        }
        userDao.lockUser(userId, true);

        return new Response<>(userDao.getById(userId), true, null);
    }

    @Override
    public Response<User> userUnlock(Integer userId) {
        if (!userIdChecking(userId).isSuccess()) {
            return userIdChecking(userId);
        }
        if (!userDao.getById(userId).isLocked()) {
            return new Response<>(null, false, "User is already unlocked");
        }
        userDao.lockUser(userId, false);

        return new Response<>(userDao.getById(userId), true, null);
    }

    @Override
    public Response<User> userIdChecking(Integer userId) {

        if (userId == null) {
            return new Response<>(null, false, "User ID can't be blank.");
        }

        if (userDao.getById(userId) == null) {
            return new Response<>(null, false, "User with such ID doesn't exist.");
        }

        return new Response<>(null, true, null);
    }

}

