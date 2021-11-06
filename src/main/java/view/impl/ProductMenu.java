package view.impl;

import dao.ProductDao;
import dao.ProductDaoImpl;
import model.Product;
import service.ProductServiceLogic;
import view.Menu;

import java.util.Scanner;

public class ProductMenu implements Menu {
    private final String[] items = {"1.View all Products", "2.Edit existing Product", "3.Add a new Product",
            "4.Logout", "0.Leave the Shop"};
    private final String[] itemsProductList = {"1.Edit existing Product", "2.Add a new Product", "3.Back to the ProductMenu",
            "4.Logout", "0.Leave the Shop"};


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
                case 2 -> editProduct();
                case 3 -> addNewProduct();
                case 4 -> logout();
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
                case 1 -> editProduct();
                case 2 -> addNewProduct();
                case 3 -> new ProductMenu().show();
                case 4 -> logout();
                case 0 -> exit();
            }
        }
    }

    public void editProduct() {
        System.out.println("\n------ Edit Product-----\n");
        System.out.println();
        System.out.println("--Enter Product information--\n");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter an ID of a Product you want to edit: ");
            Integer productToUpdateId = scanner.nextInt();
            System.out.println("Enter New Product name* (should be unique):");
            scanner.nextLine();
            String name = scanner.nextLine();
            System.out.println("Enter New Product firm: ");
            String firm = scanner.nextLine();
            System.out.println("Enter New Product price: ");
            Integer price = scanner.nextInt();
            System.out.println("Enter New maximum loading laundry Value: ");
            Integer maxLoad = scanner.nextInt();
            System.out.println("Enter New load Type (frontal / vertical): ");
            scanner.nextLine();
            String loadType = scanner.nextLine();
            var productResponse = new ProductServiceLogic().editProduct(productToUpdateId, name, firm, price,
                    maxLoad, loadType);
            Product updatedProduct = null;
            if (!productResponse.isSuccess()) {
                System.out.println(productResponse.getErrorMsg());
                System.out.println("Please, try again\n");
                show();
            } else {
                updatedProduct = productResponse.getResult();
            }
            System.out.printf("\nProduct: \n'%s'\n is successfully updated\n", updatedProduct);
            show();

        }
    }

    public void addNewProduct() {
        System.out.println("\n----- Add a New Product----\n");
        System.out.println();
        System.out.println("Specify Product information:\n");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Product name* (should be unique):");
            String name = scanner.nextLine();
            System.out.println("Enter Product firm: ");
            String firm = scanner.nextLine();
            System.out.println("Enter Product price: ");
            Integer price = scanner.nextInt();
            System.out.println("Enter maximum loading laundry: ");
            Integer maxLoad = scanner.nextInt();
            System.out.println("Enter load Type (frontal / vertical): ");
            scanner.nextLine();
            String loadType = scanner.nextLine();
            var productResponse = new ProductServiceLogic().addProduct(name, firm, price,
                    maxLoad, loadType);
            Product newProduct = null;
            if (!productResponse.isSuccess()) {
                System.out.println(productResponse.getErrorMsg());
                System.out.println("Please, try again\n");
                show();
            } else {
                newProduct = productResponse.getResult();
            }
            System.out.printf("\nNew Product: \n'%s'\n is successfully added\n", newProduct);
            show();
        }
    }
}
