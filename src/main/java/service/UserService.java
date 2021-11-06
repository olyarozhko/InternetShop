package service;


import model.Response;
import model.User;

public interface UserService {

    Response<User> login(String username, String password);

    Response<User> register(String username, String password, String firstName, String lastName, String phoneNumber,
                            String email);

    Response<User> userLock(Integer userId);

    Response<User> userUnlock(Integer userId);

    Response<User> userIdChecking(Integer userId);
}
