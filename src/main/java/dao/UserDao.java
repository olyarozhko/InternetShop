package dao;

import model.User;

import java.util.List;

/**
 * Created by Igor on 10/8/2019.
 */
public interface UserDao {

    void readFromCsv(String filename);

    void addUser(User user);

    void save(List<User> userdb);

    void lockUser(Integer userId, boolean isLocked);

    User getByName(String username);

    User getById(Integer userId);

    List<User> getAll();

}
