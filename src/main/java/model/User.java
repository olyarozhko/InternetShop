package model;


import service.UserServiceLogic;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isAdmin;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.isAdmin = new UserServiceLogic().adminRights(username);
    }

    User (String username, String password, String firstName, String lastName, String phoneNumber){
        this.username = username;
        this.password = password;
        this.isAdmin = new UserServiceLogic().adminRights(username);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }

}
