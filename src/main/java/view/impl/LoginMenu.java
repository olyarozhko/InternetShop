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
        System.out.println("0. Exit");

        scanner = new Scanner(System.in);

        while (true) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loginSubMenu(scanner);
                    break;
                case 2:
                    loginSubMenu(scanner);
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
        System.out.println("input login:");
        String login = scanner.nextLine();

        System.out.println("input password:");
        String password = scanner.nextLine();

        if (userService.login(login, password)) {
            new ProductMenu().show();
        } else {
            System.out.println("Wrong username/pasword");
            show();
        }
    }

    private void registerSubMenu(Scanner scanner) {
        show(); //todo add impl
    }
}
