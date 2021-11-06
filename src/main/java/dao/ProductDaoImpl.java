package dao;

import model.Product;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static List<Product> productdb = new ArrayList<>();


    public static void readFromCsv(String filename) {
        Path pathToFile = Paths.get(filename);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Product product = createProductForReadingFromCsv(attributes);
                addProduct(product);
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Product createProductForReadingFromCsv(String[] metadata) {

        String productName = metadata[1];
        String firm = metadata[2];
        Integer price = Integer.parseInt(metadata[3]);
        Integer maxLoad = Integer.parseInt(metadata[4]);
        String loadType = metadata[5];


        return new Product(productName, firm, price,maxLoad,loadType);
    }


    public static void addProduct(Product productNew) {

        productdb.add(productNew);
        save(productdb);
    }

    public static void save(List<Product> productdb) {
        File productdbFile = new File("resources/productdbFile.csv");

        if (!productdbFile.exists()) {
            try {
                File directory = new File(productdbFile.getParent());
                if (!directory.exists()) {
                    directory.mkdir();
                }
                productdbFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error occurred while creating directory or file");
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/productdbFile.csv"), StandardCharsets.UTF_8))) {
            for (Product product : productdb) {
                String CSV_SEPARATOR = ";";
                String oneLine = (product.getProductId() < 0 ? "" : product.getProductId()) +
                        CSV_SEPARATOR +
                        (product.getProductName().trim().length() == 0 ? "" : product.getProductName()) +
                        CSV_SEPARATOR +
                        (product.getFirm().trim().length() == 0 ? "" : product.getFirm()) +
                        CSV_SEPARATOR +
                        (product.getPrice() < 0 ? "" : product.getPrice()) +
                        CSV_SEPARATOR +
                        (product.getMaxLoad() < 0 ? "" : product.getMaxLoad()) +
                        CSV_SEPARATOR +
                        (product.getLoadType());
                bw.write(oneLine);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error occurred while saving user");
        }
    }


    public static Product getByName(String productName) {
        for (Product product : productdb) {
            if (product.getProductName().equals(productName)) {
                return product;
            }
        }
        return null;
    }


    public static Product getById(Integer productId) {
        for (Product product : productdb) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }


    public static List<Product> getAll() {
        return (productdb);
    }


    public static void editProduct(Integer productId, Product productNew) {
        for (Product product : productdb) {
            if (product.getProductId().equals(productId)) {
                int index = productdb.indexOf(product);
                productdb.set(index,productNew);
                save(productdb);
            }
        }
    }
}
