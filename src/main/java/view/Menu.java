package view;

import view.impl.LoginMenu;

import java.util.Scanner;

public interface Menu {
    void show();

    default void showItems(String[] items) {
        for (String item : items) {
            System.out.println("--------------");
            System.out.println(item);
        }
    }

    default void logout(){
        new LoginMenu().show();
    }

    default void exit(){
        System.out.println("Are you sure, you want to leave the Shop? \n1. yes \n2. no");
        try (Scanner scanner = new Scanner(System.in)) {
            int answer = scanner.nextInt();
            switch (answer) {
                case 1 -> System.exit(0);
                case 2 -> show();
            }
        }
    }
}
