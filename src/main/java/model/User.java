package model;


import service.UserServiceLogic;

public class User {
    private boolean isLocked;
    private int idUser;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isAdmin;

    enum Status {
        NEW,
        CONFIRMED
    }


    public User(String username, String password, String firstName, String lastName, String phoneNumber, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }

    public User(String username, String password, String firstName, String lastName, String phoneNumber) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Response<User> getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Response<User> isAdmin) {
        this.isAdmin = isAdmin;
    }
}
