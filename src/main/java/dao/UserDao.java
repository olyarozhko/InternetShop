package dao;

import model.User;

import java.util.List;

/**
 * Created by Igor on 10/8/2019.
 */
public interface UserDao {

    void save(User user);

    void isLocked(String username, boolean isLocked);

    User getByName(String username);

    List<User> getAll();

}
