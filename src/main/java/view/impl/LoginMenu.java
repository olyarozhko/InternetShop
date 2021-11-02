package view.impl;


import model.Product;
import service.UserService;
import view.Menu;

import java.util.Scanner;

public class LoginMenu implements Menu {

    private UserService userService;
    private String[] items = {"1.Login", "2.Register"};
    private Scanner scanner;

    @Override
    public void show() {
        showItems(items);
        /*  System.out.println("0. Exit"); */

        scanner = new Scanner(System.in);

        while (true) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loginSubMenu(scanner);
                    break;
                case 2:
                    registerSubMenu(scanner);
                    break;
                case 0:
                    exit();
                    break;
            }
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    private void loginSubMenu(Scanner scanner) {
        scanner = new Scanner(System.in);
        System.out.println("input login:");
        String login = scanner.nextLine();

        System.out.println("input password:");
        String password = scanner.nextLine();
        System.out.println("Are u sure?: \n");
        System.out.println("login: " + login + "\npassword: " + password + "\n");
        System.out.println("1.Yes, continue.\n2.No, back.\n");
        int yesNo = scanner.nextInt();
        switch (yesNo) {
            case 1:
                if (userService.login(login, password)) {
                    new ProductMenu().show();
                } else {
                    System.out.println("Wrong username/password");
                    show();
                }
                break;
            case 2:
                new LoginMenu().show();
                break;
        }
    }

    private void registerSubMenu(Scanner scanner) {
        scanner = new Scanner(System.in);
        System.out.println("input login:");
        String login = scanner.nextLine();

        System.out.println("input password:");
        String password = scanner.nextLine();

        System.out.println("Are u sure?: \n");
        System.out.println("login: " + login + "\npassword: " + password + "\n");
        System.out.println("1.Yes, continue.\n2.No, back.\n");
        int yesNo = scanner.nextInt();
        switch (yesNo) {
            case 1:
                if (userService.login(login, password)) {
                    System.out.println("Account already exists!");
                    new LoginMenu().show();
                } else {
                    System.out.println("Account created!");
                    new LoginMenu().show();
                }
                break;
            case 2:
                System.out.println("Account not created!");
                new LoginMenu().show();
                break;


            // show(); //todo add impl
        }
    }
}
// Exit, mistakes