package dao;

import model.Order;
import model.Product;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDaoImpl {

    private static List<Order> orderdb = new ArrayList<>();


    public static void readFromCsv(String filename) {
        Path pathToFile = Paths.get(filename);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Order order = createOrderForReadingFromCsv(attributes);
                addOrder(order);
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Order createOrderForReadingFromCsv(String[] metadata) {

        String orderOwner = metadata[1];
        Integer productId = Integer.parseInt(metadata[2]);

        return new Order(orderOwner, productId);
    }


    public static void addOrder(Order orderNew) {
        orderdb.add(orderNew);
        save(orderdb);
    }


    public static void save(List<Order> orderdb) {
        File orderdbFile = new File("resources/orderdbFile.csv");

        if (!orderdbFile.exists()) {
            try {
                File directory = new File(orderdbFile.getParent());
                if (!directory.exists()) {
                    directory.mkdir();
                }
                orderdbFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error occurred while creating directory or file");
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/orderdbFile.csv"), StandardCharsets.UTF_8))) {
            for (Order order : orderdb) {
                String CSV_SEPARATOR = ";";
                String oneLine = (order.getId() < 0 ? "" : order.getId()) +
                        CSV_SEPARATOR +
                        (order.getOrderOwner().trim().length() == 0 ? "" : order.getOrderOwner()) +
                        CSV_SEPARATOR +
                        (order.getState());
                bw.write(oneLine);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error occurred while saving user");
        }
    }


    public static List<Product> getByOrderOwner(String orderOwner) {

        List<Order> allOrderedProducts;
        allOrderedProducts = orderdb.stream().filter(order -> order.getOrderOwner().equals(orderOwner))
                .collect(Collectors.toList());
        List<Integer> id = allOrderedProducts.stream().map(Order::getProductId).collect(Collectors.toList());
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {

            productList.add(ProductDaoImpl.getById(i));
        }return productList;
    }


    public static Order getById(Integer id) {
        for (Order order : orderdb) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }


    public static List<Order> getAll() {
        return (orderdb);
    }


    public static void changeOrderStatus(Integer id, Order.Status state) {
        for (Order order : orderdb) {
            if (order.getId().equals(id)) {
                order.setState(state);
                save(orderdb);
            }
        }
    }
}