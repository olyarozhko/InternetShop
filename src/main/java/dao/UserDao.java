package dao;

import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 10/8/2019.
 */
public interface UserDao {

    void addUser(String username, String password);

    void save(ArrayList<User> userdb);

    void isLocked(Integer userId, boolean isLocked);

    User getByName(String username);

    User getById(Integer userId);

    ArrayList<User> getAll();

}
