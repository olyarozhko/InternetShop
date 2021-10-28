package dao;

import model.User;

import java.io.*;
import java.util.List;


public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) {
        File userdbFile = new File("resources/userdbFile.csv");

        if (!userdbFile.exists()) {
            try {
                File directory = new File(userdbFile.getParent());
                if (!directory.exists()) {
                    directory.mkdir();
                }
                userdbFile.createNewFile();
            } catch (IOException e){
                System.out.println("Error occurred while creating directory or file");
            }
        }
        try {
            FileWriter userdbWriter;
            userdbWriter = new FileWriter(userdbFile.getAbsoluteFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(userdbWriter);
            bufferedWriter.write(user.toString());
            bufferedWriter.close();

        }catch (IOException e){
            System.out.println("Error occurred while saving user");
        }
    }

    @Override
    public void update(User user) {


    }

    @Override
    public User getByName(String username) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

}
