package dao;

import model.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {

    private static List<User> userdb = new ArrayList<>();

    @Override
    public void readFromCsv(String filename) {
        Path pathToFile = Paths.get(filename);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                User user = createUserForReadingFromCsv(attributes);
                addUser(user);
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User createUserForReadingFromCsv(String[] metadata) {

        String username = metadata[1];
        String password = metadata[2];
        boolean isLocked = Boolean.parseBoolean(metadata[3]);

        return new User(username, password, isLocked);
    }

    @Override
    public void save(List<User> userdb) {
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

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/userdbFile.csv"), StandardCharsets.UTF_8))) {
            for (User user : userdb) {
                String CSV_SEPARATOR = ";";
                String oneLine = (user.getUserId() < 0 ? "" : user.getUserId()) +
                        CSV_SEPARATOR +
                        (user.getUsername().trim().length() == 0 ? "" : user.getUsername()) +
                        CSV_SEPARATOR +
                        (user.getPassword().trim().length() == 0 ? "" : user.getPassword()) +
                        CSV_SEPARATOR +
                        (user.isLocked() ? "Yes" : "No");
                bw.write(oneLine);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error occurred while saving user");
        }
    }

    @Override
    public void addUser(User userNew) {

        userdb.add(userNew);
        save(userdb);
    }

    @Override
    public void lockUser(Integer userId, boolean isLocked) {

        for (User user : userdb) {
            if (user.getUserId().equals(userId)) {
                user.setLocked(isLocked);
                save(userdb);
            }
        }
    }

    @Override
    public User getByName(String username) {
        for (User user : userdb) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return (userdb);
    }

    @Override
    public User getById(Integer userId) {

        for (User user : userdb) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}
