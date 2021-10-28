package view.impl;

import view.Menu;

import java.util.Scanner;

public class AdminMenu implements Menu {

    private final String[] items = {"1.Users menu", "2.Order menu", "3.Products menu", "4.Logout", "0.Leave the Shop"};


    @Override
    public void show() {
        System.out.println("\n----- Hello, username----\n");
        System.out.println("Select one of the Options:\n");
        showItems(items);
        System.out.println("\nSelected option:\n");
        try (Scanner scanner = new Scanner(System.in)) {
            int answer = scanner.nextInt();
            switch (answer) {
                case 1 -> new UserListMenu().show();
                case 2 -> new OrderMenu().show();
                case 3 -> new ProductMenu().show();
                case 4 -> logout();
                case 0 -> exit();
            }
        }
    }

    @Override
    public void showItems(String[] items) {
        Menu.super.showItems(items);
    }
}
