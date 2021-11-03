package service;


import model.Response;
import model.User;

public interface UserService {

    /**
     * Used to login a user
     *
     * @param username user name
     * @param password user password
     * @return outcome of login - success or not
     */
    Response<User> login(String username, String password);


}
