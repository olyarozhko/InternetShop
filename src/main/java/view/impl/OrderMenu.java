package view.impl;

import dao.OrderDaoImpl;
import model.Order;
import service.OrderServiceLogic;
import view.Menu;

import java.util.Scanner;

public class OrderMenu implements Menu {
    private final String[] items = {"1.View all Orders", "2.Orders Confirmation / Cancellation", "3.Back to the Admin Menu",
            "4.Logout", "0.Leave the Shop"};
    private final String[] itemsOrderList = {"1.Orders Confirmation / Cancellation", "2.Back to the Order Menu",
            "3.Back to the Admin Menu", "4.Logout", "0.Leave the Shop"};
    private final String[] cancelConfirm = {"1.Confirm", "2.Cancel"};

    @Override
    public void show() {
        System.out.println("\n----- Order Menu ----\n");
        System.out.println("Select one of the Options:\n");
        showItems(items);
        System.out.println("\nSelected option:\n");
        try (Scanner scanner = new Scanner(System.in)) {
            int answer = scanner.nextInt();
            switch (answer) {
                case 1 -> showOrders();
                case 2 -> confirmCancelOrder();
                case 3 -> new AdminMenu().show();
                case 4 -> logout();
                case 0 -> exit();
            }
        }
    }

    public void showOrders() {
        System.out.println("\n----- Order list----\n");
        System.out.println(new OrderDaoImpl().getAllOrders());
        System.out.println();
        System.out.println("Select one of the Options:\n");
        showItems(itemsOrderList);
        try (Scanner scanner = new Scanner(System.in)) {
            int answer = scanner.nextInt();
            switch (answer) {
                case 1 -> confirmCancelOrder();
                case 2 -> show();
                case 3 -> new AdminMenu().show();
                case 4 -> logout();
                case 0 -> exit();
            }
        }
    }

    public void confirmCancelOrder() {
        System.out.println("\n----- Orders Confirmation / Cancellation ----\n");
        System.out.println();
        System.out.println("Enter Order ID: \n");
        try (Scanner scanner = new Scanner(System.in)) {
            Integer orderId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter number of the Operation");
            showItems(cancelConfirm);
            int answer = scanner.nextInt();
            switch (answer) {
                case 1 -> confirmOrder(orderId);
                case 2 -> cancelOrder(orderId);
            }
        }
    }

    public void confirmOrder(Integer orderId) {
        var orderResponse = new OrderServiceLogic().confirmOrder(orderId);
        Order orderUpdated = null;
        if (!orderResponse.isSuccess()) {
            System.out.println(orderResponse.getErrorMsg());
            System.out.println("Please, try again\n");
            show();
        } else {
            orderUpdated = orderResponse.getResult();
        }
        System.out.printf("\nOrder: \n'%s'\n is successfully confirmed\n", orderUpdated);
        show();
    }

    public void cancelOrder(Integer orderId) {
        var orderResponse = new OrderServiceLogic().cancelOrder(orderId);
        Order orderUpdated = null;
        if (!orderResponse.isSuccess()) {
            System.out.println(orderResponse.getErrorMsg());
            System.out.println("Please, try again\n");
            show();
        } else {
            orderUpdated = orderResponse.getResult();
        }
        System.out.printf("\nOrder: \n'%s'\n is successfully cancelled\n", orderUpdated);
        show();
    }

}
