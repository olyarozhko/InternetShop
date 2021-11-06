package dao;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao{
    static List<Product> productList = new ArrayList<>();


    @Override
    public void addProduct(Product product) {
        productList.add(product);

    }

    @Override
    public void update(Integer productId, Product productNew) {

    }

    @Override
    public void save(List<Product> productdb) {

    }

    @Override
    public Product getByName(String productName) {
        return null;
    }

    @Override
    public Product getById(Integer id) {
        for (Product product : productList) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> search(String searchString){
        List<Product> searchProductsList = null;
        for (Product product : productList) {
            if (product.getProductName().contains(searchString)) {
                searchProductsList.add(product);
            }
        }
        return searchProductsList;
    }


    public List<Product> getAll() {
        return productList;
    }
}
