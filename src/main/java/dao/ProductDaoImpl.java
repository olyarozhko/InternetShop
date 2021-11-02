package dao;

import model.Product;
import model.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProductDaoImpl implements ProductDao{
    static ArrayList<Product> productdb = new ArrayList<>();

    @Override
    public void addProduct(String productName, String firm, Integer price, Integer maxLoad, String loadType) {

        productdb.add(new Product(productName, firm, price, maxLoad, loadType));
        save(productdb);
    }

    @Override
    public void save(ArrayList<Product> productdb) {
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
    }

    @Override
    public Product getByName(String productName) {
        for (Product product : productdb) {
            if (product.getProductName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product getById(Integer productId) {
        for (Product product : productdb) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAll() {
        return (productdb);
    }

    @Override
    public void editProduct(String productName, String firm, Integer price, Integer maxLoad, String loadType) {
        for (Product product : productdb) {
            if (product.getProductName().equals(productName)) {
                product.setProductName(productName);
                product.setFirm(firm);
                product.setPrice(price);
                product.setMaxLoad(maxLoad);
                product.setLoadType(loadType);
            }
        }
    }
}
