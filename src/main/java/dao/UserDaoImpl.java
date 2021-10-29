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
            } catch (IOException e) {
                System.out.println("Error occurred while creating directory or file");
            }
        }
        try {
            FileWriter userdbWriter;
            userdbWriter = new FileWriter(userdbFile.getAbsoluteFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(userdbWriter);
            bufferedWriter.write(user.toString());
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Error occurred while saving user");
        }
    }

    @Override
    public void isLocked(String username, boolean isLocked) {
        try {
            BufferedReader userdbFile = new BufferedReader(new FileReader("resources/userdbFile.csv"));
            String line;
            String input = "";
            while ((line = userdbFile.readLine()) != null) input += line + '\n';
            if (Boolean.parseBoolean(String.valueOf(!isLocked))) {
                input = input.replace(username + false, username + true);
            } else if (Boolean.parseBoolean(String.valueOf(isLocked == true))) {
                input = input.replace(username + true, username + false);
            }
            FileOutputStream userdbFile2 = new FileOutputStream("resources/userdbFile.csv");
            userdbFile2.write(input.getBytes());
        } catch (IOException e) {
            System.out.println("Error occurred while reading file");
        }
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
