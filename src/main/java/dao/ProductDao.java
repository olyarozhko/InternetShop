package dao;

import model.Product;

import java.util.ArrayList;

public interface ProductDao {

    void addProduct(String productName, String firm, Integer price, Integer maxLoad, String loadType);

    void save(ArrayList<Product> productdb);

    Product getByName(String productName);

    Product getById(Integer productId);

    ArrayList<Product> getAll();

    void editProduct(String productName, String firm, Integer price, Integer maxLoad, String loadType);
}
