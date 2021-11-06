package view.impl;

import dao.ProductDaoImpl;
import model.Order;
import model.Product;
import service.OrderServiceLogic;
import service.ProductServiceLogic;
import view.Menu;

import java.util.List;
import java.util.Scanner;

public class ProductMenuUser implements Menu {
    private final String[] items = {"1.View all Products", "2.Add Products to an Order", "3.Search a Product",
            "4.Order checkout", "5.Back to the User Menu", "6.Logout", "0.Leave the Shop"};
    private final String[] itemsProductList = {"1.Add Products to an Order", "2.Search a Product", "3.Order checkout",
            "4.Back to the Product Menu", "5.Back to the User Menu", "6.Logout", "0.Leave the Shop"};
    private final String[] itemsOrderCheckout = {"1.Create an Order", "2.Add Products to the Order", "3.Clear all Products",
            "4.Back to the Product Menu", "5.Back to the User Menu", "6.Logout", "0.Leave the Shop"};
    private final String[] itemsSearch = {"1.Enter another Search String", "2.Add Products to the Order",
            "3.Back to the Product Menu", "4.Back to the User Menu", "5.Logout", "0.Leave the Shop"};

    private Order order;
    private List<Product> productsSelected;

    @Override
    public void show() {
        System.out.println("\n----- Product Menu ----\n");
        System.out.println("Select one of the Options:\n");
        showItems(items);
        System.out.println("\nSelected option:\n");
        try (Scanner scanner = new Scanner(System.in)) {
            int answer = scanner.nextInt();
            switch (answer) {
                case 1 -> showProducts();
                case 2 -> addProductToOrder();
                case 3 -> searchProduct();
                case 4 -> orderCheckout();
                case 5 -> new UserMainMenu().show();
                case 6 -> logout();
                case 0 -> exit();
            }
        }
    }

    public void showProducts() {
        System.out.println("\n----- Product list----\n");
        System.out.println(new ProductDaoImpl().getAll());
        System.out.println();
        System.out.println("Select one of the Options:\n");
        showItems(itemsProductList);
        try (Scanner scanner = new Scanner(System.in)) {
            int answer = scanner.nextInt();
            switch (answer) {
                case 1 -> addProductToOrder();
                case 2 -> searchProduct();
                case 3 -> orderCheckout();
                case 4 -> show();
                case 5 -> new UserMainMenu().show();
                case 6 -> logout();
                case 0 -> exit();
            }
        }
    }

    public void addProductToOrder() {
        System.out.println("\n----- Add Products to an Order ----\n");
        System.out.println();
        System.out.println("Specify Ids of the Product you want to bye as a comma-separated list:\n");
        try (Scanner scanner = new Scanner(System.in)) {
            String productIdStr = scanner.nextLine();
            var productResponse = new ProductServiceLogic().addProductToOrder(productIdStr);
            if (!productResponse.isSuccess()) {
                System.out.println(productResponse.getErrorMsg());
                System.out.println("Please, try again\n");
                show();
            } else {
                productsSelected = productResponse.getResult();
            }
            System.out.println("Products are successfully added\n");
            show();
        }
    }


    public void orderCheckout() {
        System.out.println("\n----- Order Checkout ----\n");
        System.out.println();
        System.out.println("Selected Products:\n");
        if(productsSelected == null){
            System.out.println("-No Products are selected-\n");
        } else {
            System.out.println(productsSelected);
        }
        showItems(itemsOrderCheckout);
        System.out.println("\nSelected option:\n");
        try (Scanner scanner = new Scanner(System.in)) {
            int answer = scanner.nextInt();
            switch (answer) {
                case 1 -> createOrder();
                case 2 -> addProductToOrder();
                case 3 -> {
                    productsSelected = null;
                    System.out.println("-No Products are selected-\n");
                }
                case 4 -> show();
                case 5 -> new UserMainMenu().show();
                case 6 -> logout();
                case 0 -> exit();
            }
        }
    }

    public void createOrder() {
        var orderResponse = new OrderServiceLogic().createOrder("us1", productsSelected);
        if (!orderResponse.isSuccess()) {
            System.out.println(orderResponse.getErrorMsg());
        } else {
            System.out.println("Order is successfully created");
            System.out.println(orderResponse.getResult());
            productsSelected = null;
        }
        orderCheckout();
    }

    public void searchProduct() {
        System.out.println("\n----- Search Product ----\n");
        System.out.println();
        System.out.println("Specify search string:\n");
        try (Scanner scanner = new Scanner(System.in)) {
            String searchSting = scanner.nextLine();
            System.out.println("\n--Products:--\n");
            var productResponse = new ProductServiceLogic().searchProduct(searchSting);
            if (!productResponse.isSuccess()) {
                System.out.println(productResponse.getErrorMsg());
            } else {
                System.out.println(productResponse.getResult());
            }

            System.out.println("\n-------------\n");
            showItems(itemsSearch);
            System.out.println("\nSelected option:\n");
            int answer = scanner.nextInt();
            switch (answer) {
                case 1 -> searchProduct();
                case 2 -> addProductToOrder();
                case 3 -> show();
                case 4 -> new UserMainMenu().show();
                case 5 -> logout();
                case 0 -> exit();
            }
        }
    }

}
