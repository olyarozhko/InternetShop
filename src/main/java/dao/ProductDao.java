package dao;

import model.Product;

import java.util.List;

public interface ProductDao {

    void readFromCsv(String filename);

    void addProduct(Product productNew);

    void save(List<Product> productdb);

    Product getByName(String productName);

    Product getById(Integer productId);

    List<Product> getAll();

    void editProduct(Integer productId, Product productNew);
}
